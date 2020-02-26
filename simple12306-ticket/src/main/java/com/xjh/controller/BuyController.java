package com.xjh.controller;

import com.xjh.service.TrainShiftService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * @author xjhxjhxjh
 * @date 2020/2/21 10:22
 */
@RestController
@RequestMapping("/buy")
public class BuyController {

    @Autowired
    private TrainShiftService trainShiftService;

    @GetMapping("")
    public Boolean buyTicket(Integer startId, Integer endId, String date){
        return trainShiftService.buyTicket(startId, endId, date);
    }
}
