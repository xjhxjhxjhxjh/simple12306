package com.xjh.service.impl;

import com.xjh.service.RouteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author xjhxjhxjh
 * @date 2020/2/18 16:43
 */
class RouteServiceImplTest {

    @Test
    void getAllRoute() {
        RouteServiceImpl routeService = new RouteServiceImpl();
        System.out.println(routeService.getAllRoute());
    }
}