package com.xjh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjh.entity.User;

import java.util.concurrent.ExecutionException;

/**
 * @author xjhxjhxjh
 * @date 2020/2/23 9:35
 */
public interface UserService extends IService<User>{
    String register(User user, String code) throws ExecutionException, InterruptedException;
    User findUserByPwd(String input, String password);
    Boolean buyTicket(Integer startId, Integer endId, String date, Long uId);
}
