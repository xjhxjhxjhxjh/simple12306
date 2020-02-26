package com.xjh.schedule;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xjh.dto.RouteDTO;
import com.xjh.entity.TrainShift;
import com.xjh.service.RouteService;
import com.xjh.service.TrainShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xjhxjhxjh
 * @date 2020/2/18 14:42
 */
@Configuration
@EnableScheduling
public class TrainShiftScheduleTask {

    @Autowired
    private RouteService routeService;

    @Autowired
    private TrainShiftService trainShiftService;

    /**
     * 往火车班次表里插入15天后的数据
     */
    @Scheduled(cron = "0 0 0 * * ?")
    private void addData() {
        List<RouteDTO> allRoute = routeService.getAllRoute();
        ArrayList<TrainShift> list = new ArrayList<>();
        allRoute.forEach(i -> {
            LocalTime parse = LocalTime.parse(i.getRLeaveTime());
            list.add(new TrainShift(1, i.getTCapacity(),
                    LocalDate.now().plusDays(15).atTime(parse.getHour(), parse.getMinute()),
                    i.getRRouteId()));
        });
        trainShiftService.saveBatch(list);
    }

    /**
     * 每分钟删除过期班次
     */
    @Scheduled(cron = "0 * * * * ?")
    private void removeData() {
        QueryWrapper<TrainShift> wrapper = new QueryWrapper<>();
        wrapper.lt("tTime", LocalDateTime.now().plusMinutes(30));
        trainShiftService.remove(wrapper);
    }
}

