package com.xjh.service.impl;

import com.xjh.client.UserClient;
import com.xjh.config.JwtConfig;
import com.xjh.entity.User;
import com.xjh.service.AuthService;
import com.xjh.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xjhxjhxjh
 * @date 2020/2/23 15:43
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private UserClient userClient;

    @Override
    public String accredit(String input, String password) throws Exception {
        User user = userClient.queryUser(input, password);
        if (user == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("uId", user.getUId());
        map.put("uName", user.getUName());
        return JwtUtils.generateToken(map, jwtConfig.getPrivateKey(), jwtConfig.getExpire());
    }
}
