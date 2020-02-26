package com.xjh.controller;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author xjhxjhxjh
 * @date 2020/2/20 10:09
 */
@SpringBootTest
class SearchControllerTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    void set() {
        List<String> integers = new ArrayList<>();
        System.out.println(redisTemplate.opsForList().range("lisstkey", 0, -1).size());
        System.out.println(redisTemplate.opsForList().range("lisstkey", 0, -1) == null);
    }

    @Test
    void get() {
        System.out.println(redisTemplate.opsForValue().get("name"));
    }

    @Test
    void get1() {
        System.out.println(redisTemplate.opsForValue().get("aa"));
        String aa = redisTemplate.opsForValue().get("aa");
        System.out.println(aa == null);
    }
}