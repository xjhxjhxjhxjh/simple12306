package com.xjh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjh.dto.TrainShiftDTO;
import com.xjh.entity.TrainShift;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * @author xjhxjhxjh
 * @date 2020/2/18 15:47
 */
public interface TrainShiftService extends IService<TrainShift> {

    List<TrainShiftDTO> findTrainShift(String start, String end, String type, LocalDateTime time) throws Exception;

    List<TrainShiftDTO> findSeat(String start, String end, String type, String date) throws Exception;

    Boolean buyTicket(Integer startId, Integer endId, String date);
}
