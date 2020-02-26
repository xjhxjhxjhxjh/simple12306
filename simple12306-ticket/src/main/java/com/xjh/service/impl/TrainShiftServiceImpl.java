package com.xjh.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjh.dto.TrainShiftDTO;
import com.xjh.entity.Route;
import com.xjh.entity.TrainShift;
import com.xjh.mapper.TrainShiftMapper;
import com.xjh.service.RouteService;
import com.xjh.service.StationService;
import com.xjh.service.TrainShiftService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @author xjhxjhxjh
 * @date 2020/2/18 15:56
 */
@Transactional
@Service
public class TrainShiftServiceImpl extends ServiceImpl<TrainShiftMapper, TrainShift> implements TrainShiftService {

    @Autowired
    private StationService stationService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RouteService routeService;

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public List<TrainShiftDTO> findTrainShift(String start, String end, String type,
                                              LocalDateTime time) throws Exception {
        // 处理为选择火车类型
        if ("null".equals(type)) {
            type = null;
        }
        // 异步获取车站信息
        String finalType = type;
        CompletableFuture future = CompletableFuture.supplyAsync(() -> stationService.findStationCityOrStation(start)).
            thenCombine(CompletableFuture.supplyAsync(() -> stationService.findStationCityOrStation(end)), (t, u) -> {
                // 查询所有情况
                List<TrainShiftDTO> trainShifts = new ArrayList<>();
                for (String startStation : t) {
                    for (String endStation : u) {
                        // 先从redis从查询
                        List<String> dataList = redisTemplate.opsForList().
                                range(startStation + endStation, 0, -1);
                        if (dataList.size() > 0) {
                            if (!"-1".equals(dataList.get(0))) {
                                dataList.forEach(data -> {
                                    TrainShiftDTO trainShift = JSON.parseObject(data, TrainShiftDTO.class);
                                    if (time.toLocalDate().isAfter(LocalDate.now()) ||
                                            LocalTime.parse(trainShift.getStartTime()).minusMinutes(30).
                                            compareTo(LocalTime.now()) == 1) {
                                        trainShifts.add(JSON.parseObject(data, TrainShiftDTO.class));
                                    }
                                });
                            }
                            continue;
                        }
                        List<TrainShiftDTO> trainShift = baseMapper.findTrainShift(startStation,
                                endStation, finalType, time.toLocalTime());
                        // 解决redis击穿
                        if (CollectionUtils.isEmpty(trainShift)) {
                            redisTemplate.opsForList().leftPush(startStation + endStation, "-1");
                        }
                        trainShift.forEach(trainShiftDTO -> {
                            Duration between = Duration.between(LocalTime.parse(trainShiftDTO.getStartTime()),
                                    LocalTime.parse(trainShiftDTO.getArriveTime()));
                            trainShiftDTO.setPrice(trainShiftDTO.getPrice().abs());
                            trainShiftDTO.setTimeCost(between.toHoursPart() + ":" + between.toMinutesPart());
                            // 往redis添加
                            redisTemplate.opsForList().rightPushAll(startStation + endStation,
                                    JSON.toJSONString(trainShiftDTO));
                            trainShifts.add(trainShiftDTO);
                        });
                    }
                }
                return trainShifts;
            });
        List<TrainShiftDTO> trainShifts = (List<TrainShiftDTO>) future.get();
        return trainShifts.stream().sorted(Comparator.comparing(TrainShiftDTO::getStartTime)).
                collect(Collectors.toList());
    }

    @Override
    public List<TrainShiftDTO> findSeat(String start, String end, String type, String date)
            throws Exception {
        // 处理时间
        LocalDateTime startTime;
        LocalDateTime endTime;
        LocalDate inputDate = LocalDate.parse(date);
        // 输入之前天的直接返回
        if (LocalDate.now().isAfter(inputDate)) {
            return new ArrayList<>();
        }
        if (LocalDate.now().equals(inputDate)) {
            startTime = LocalDateTime.now().plusMinutes(30);
            endTime = LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.parse("00:00"));
        } else {
            startTime = LocalDateTime.of(inputDate, LocalTime.parse("00:00"));
            endTime = LocalDateTime.of(inputDate.plusDays(1),
                    LocalTime.parse("00:00"));
        }
        List<TrainShiftDTO> trainShifts = findTrainShift(start, end, type, startTime);
        trainShifts.forEach(trainShift -> {
            String seatInfo = redisTemplate.opsForValue().get(trainShift.getStartId() + trainShift.getEndId()
                    + startTime.toLocalDate().toString());
            if (seatInfo == null) {
                Integer seatNum = baseMapper.findSeatNum(trainShift, startTime, endTime);
                if (seatNum == null || seatNum == 0) {
                    seatInfo = "无票";
                } else if (seatNum > 50) {
                    seatInfo = "余票充足";
                } else if (seatNum > 10) {
                    seatInfo = "少量余票";
                } else {
                    seatInfo = seatNum + "";
                }
                trainShift.setSeatInfo(seatInfo);
                redisTemplate.opsForValue().set(trainShift.getStartId() + trainShift.getEndId() +
                        startTime.toLocalDate().toString(), seatInfo);
            }
            trainShift.setSeatInfo(seatInfo);
        });
        return trainShifts;
    }

    @Override
    public Boolean buyTicket(Integer startId, Integer endId, String date) {
        LocalDate localDate = LocalDate.parse(date);
        QueryWrapper<TrainShift> wrapper = new QueryWrapper<>();
        wrapper.ge("tRouteId", startId).
                le("tRouteId", endId).
                gt("tTime", localDate).
                le("tTime", localDate.plusDays(1));

        Route routedName = routeService.findRoutedName(startId);
        RLock lock = redissonClient.getLock(routedName + date);
        lock.lock();
        List<TrainShift> trainShifts = baseMapper.selectList(wrapper);
        ArrayList<Integer> list = new ArrayList<>();
        trainShifts.forEach(trainShift -> {
            int nowNum = trainShift.getTSeatNum() - 1;
            trainShift.setTSeatNum(nowNum);
            list.add(nowNum);
        });
        int min = Integer.MAX_VALUE;
        for (Integer integer : list) {
            min = min < integer ? min : integer;
        }
        if (min < 0){
            lock.unlock();
            return false;
        }
        if (min < 50 && min > 10) {
            redisTemplate.opsForValue().set(startId + endId + localDate.toString(), "少量余票");
        } else if (min < 10) {
            redisTemplate.opsForValue().set(startId + endId + localDate.toString(), min + "");
        }
        lock.unlock();
        return this.updateBatchById(trainShifts);
    }
}
