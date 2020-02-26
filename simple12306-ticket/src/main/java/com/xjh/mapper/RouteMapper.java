package com.xjh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjh.dto.RouteDTO;
import com.xjh.entity.Route;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author xjhxjhxjh
 * @date 2020/2/18 15:42
 */
public interface RouteMapper extends BaseMapper<Route> {

    @Select("select r.rId rRouteId, r.rName, r.rArriveTime, r.rLeaveTime, rStationId, rTypeId, " +
            "r.rMileage, s.sStationName, s.sCityName, t.tType, t.tPrice, t.tCapacity " +
            "from s_route r left join s_station s on r.rStationId = s.sId " +
            "left join s_type t on r.rTypeId = t.tId")
    List<RouteDTO> getAllRoute();

}
