package com.xjh.config;

import com.xjh.filter.AuthGatewayFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author xjhxjhxjh
 * @date 2020/2/24 12:29
 */
@Component
public class AuthConfig {

    @Autowired
    private AuthGatewayFilter authGatewayFilter;

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/user/query/**", "/user/buy/**")
                        .uri("lb://simple12306-user")
                        .filters(authGatewayFilter)
                        .id("simple12306-user"))
                .route(r -> r.path("/buy/**")
                        .uri("lb://simple12306-ticket")
                        .filters(authGatewayFilter)
                        .id("simple12306-ticket"))
                .route(r -> r.path("/order/**")
                        .uri("lb://simple12306-order")
                        .filters(authGatewayFilter)
                        .id("simple12306-order"))
                .build();
    }
}
