package com.xjh.filter;

import com.xjh.config.JwtConfig;
import com.xjh.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author xjhxjhxjh
 * @date 2020/2/23 16:42
 */
@Component
public class AuthGatewayFilter implements  GatewayFilter, Ordered {

    @Autowired
    private JwtConfig jwtConfig;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        // 获取所有cookie
        MultiValueMap<String, HttpCookie> cookies = request.getCookies();
        // 如果cookies为空或者不包含指定的token，则相应认证未通过
        if (CollectionUtils.isEmpty(cookies) || !cookies.containsKey(jwtConfig.getCookieName())) {
            // 响应未认证
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            // 结束请求
            return response.setComplete();
        }
        // 获取cookie
        HttpCookie cookie = cookies.getFirst(jwtConfig.getCookieName());
        System.out.println(cookie);
        try {
            // 校验cookie
            JwtUtils.getInfoFromToken(cookie.getValue(), jwtConfig.getPublicKey());
        } catch (Exception e) {
            e.printStackTrace();
            // 校验失败，响应未认证
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
