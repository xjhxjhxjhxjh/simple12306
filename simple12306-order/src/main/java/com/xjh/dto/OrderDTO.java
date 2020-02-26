package com.xjh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author xjhxjhxjh
 * @date 2020/2/24 13:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO implements Serializable {
    private Long orderId;
    private Long userId;
    private String userName;
    private BigDecimal price;
    private String type;
    private String startCity;
    private String startStation;
    private LocalTime startTime;
    private String arriveCity;
    private String arriveStation;
    private LocalTime arriveTime;
    private LocalDateTime payTime;
    private LocalDate trainDate;
}