package com.xjh.controller;

import com.xjh.dto.OrderDTO;
import com.xjh.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xjhxjhxjh
 * @date 2020/2/24 13:16
 */
@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/addOrder")
    public Boolean addOrder(Long uId, String date, Integer startId, Integer endId){
        return orderService.addOrder(uId, date, startId, endId);
    }

    @GetMapping("/findOrder/{uId}")
    public List<OrderDTO> findOrder(@PathVariable Long uId){
        return orderService.findOrder(uId);
    }
}
