package com.xjh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author xjhxjhxjh
 * @date 2020/2/19 14:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainShiftDTO implements Serializable {
    private String startStation;
    private String startCity;
    private String startTime;
    private String arriveStation;
    private String arriveCity;
    private String arriveTime;
    private BigDecimal price;
    private String seatInfo;
    private String route;
    private String timeCost;
    private String type;
    private Integer startId;
    private Integer endId;
}
