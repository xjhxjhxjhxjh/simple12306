package com.xjh.controller;

import com.xjh.client.BuyClient;
import com.xjh.entity.User;
import com.xjh.service.UserService;
import com.xjh.vo.ResultVO;
import com.xjh.vo.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

/**
 * @author xjhxjhxjh
 * @date 2020/2/21 11:29
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/buy/{startId}/{endId}/{date}/{uId}")
    public ResultVO buy(@PathVariable Integer startId, @PathVariable
            Integer endId, @PathVariable String date, @PathVariable Long uId) {

        return userService.buyTicket(startId, endId, date, uId) ?
                new ResultVO(StatusCode.SUCCESS, "购买成功") :
                new ResultVO(StatusCode.SERVER_ERROR, "购买失败");
    }

    @PostMapping("/register")
    public ResultVO register(User user, String code) {
        try {
            String message = userService.register(user, code);
            if ("注册成功".equals(message)) {
                return new ResultVO(StatusCode.SUCCESS, message);
            }
            return new ResultVO(StatusCode.Register_ERROR, message);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(StatusCode.Register_ERROR, e.getMessage());
        }
    }

    @GetMapping("/query/{input}/{password}")
    public User queryUser(@PathVariable String input, @PathVariable String password) {
        User user = userService.findUserByPwd(input, password);
        if (user != null){
            user.setUSalt("");
        }
        return user;
    }
}
