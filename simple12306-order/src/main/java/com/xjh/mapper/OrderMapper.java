package com.xjh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjh.dto.OrderDTO;
import com.xjh.entity.Order;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author xjhxjhxjh
 * @date 2020/2/24 14:57
 */
public interface OrderMapper extends BaseMapper<Order> {



    @Select("select tPrice * (r2.rMileage - r1.rMileage) price, " +
            "o.oUserId userId, u.uName userName, t.tType type, " +
            "s1.sCityName startCity, s1.sStationName startStation, " +
            "s2.sCityName arriveCity, s2.sStationName arriveStation, " +
            "r2.rArriveTime arriveTime, r1.rName route, o.oId orderId, " +
            "r1.rLeaveTime startTime, o.oPayTime payTime, o.oDate trainDate " +
            "from s_order o left join s_route r1 on o.oStartRouteId = r1.rId " +
            "left join s_route r2 on o.oEndRouteId = r2.rId " +
            "left join s_type t on t.tId = r1.rTypeId " +
            "left join s_station s1 on s1.sId = r1.rStationId " +
            "left join s_station s2 on s2.sId = r2.rStationId " +
            "left join s_user u on o.oUserId = u.uId " +
            "where o.oUserId = #{uId}")
    List<OrderDTO> findOrder(@Param("uId") Long uId);
}
