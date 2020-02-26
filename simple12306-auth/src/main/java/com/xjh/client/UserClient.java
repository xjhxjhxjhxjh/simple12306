package com.xjh.client;

import com.xjh.client.impl.UserClientImpl;
import com.xjh.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author xjhxjhxjh
 * @date 2020/2/23 15:21
 */
@FeignClient(value = "simple12306-user", fallback = UserClientImpl.class)
public interface UserClient {

    @GetMapping("/user/query/{input}/{password}")
    User queryUser(@PathVariable("input") String input, @PathVariable("password") String password);
}
