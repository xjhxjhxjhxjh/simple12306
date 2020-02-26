package com.xjh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author xjhxjhxjh
 * @date 2020/2/18 17:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteDTO implements Serializable {
    private Integer rRouteId;
    private String sStationName;
    private String sCityName;
    private String rName;
    private String rArriveTime;
    private String rLeaveTime;
    private Integer rMileage;
    private Integer rStationId;
    private Integer rTypeId;
    private String tType;
    private BigDecimal tPrice;
    private Integer tCapacity;
}
