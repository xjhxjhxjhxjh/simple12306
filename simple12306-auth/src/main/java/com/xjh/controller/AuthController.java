package com.xjh.controller;

import com.xjh.config.JwtConfig;
import com.xjh.service.AuthService;
import com.xjh.utils.CookieUtils;
import com.xjh.vo.ResultVO;
import com.xjh.vo.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xjhxjhxjh
 * @date 2020/2/23 15:20
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private JwtConfig jwtConfig;

    @GetMapping("/accredit/{input}/{password}")
    public ResultVO accredit(@PathVariable String input, @PathVariable String password,
                             HttpServletRequest request, HttpServletResponse response) {
        try {
            String token = authService.accredit(input, password);
            if (StringUtils.isEmpty(token)){
                return new ResultVO(StatusCode.LOGIN_ERROR, "用户名密码错误");
            }
            CookieUtils.setCookie(request, response, jwtConfig.getCookieName(),
                    token, jwtConfig.getExpire() * 60);
            System.out.println(token);
            return new ResultVO(StatusCode.SUCCESS, "登陆成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultVO(StatusCode.LOGIN_ERROR, "用户名密码错误");
    }
}
