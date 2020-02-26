package com.xjh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjh.dto.TrainShiftDTO;
import com.xjh.entity.TrainShift;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * @author xjhxjhxjh
 * @date 2020/2/18 14:55
 */
public interface TrainShiftMapper extends BaseMapper<TrainShift> {


    @Select("<script> select tPrice * (r2.rMileage - r1.rMileage) price, " +
            "s1.sCityName startCity, s1.sStationName startStation, " +
            "s2.sCityName arriveCity, s2.sStationName arriveStation, " +
            "t.tType type, r1.rLeaveTime startTime, " +
            "r2.rArriveTime arriveTime, r1.rName route, " +
            "r1.rId startId, r2.rId endId " +
            "from s_route r1 left join s_route r2 on r1.rName = r2.rName " +
            "left join s_station s1 on r1.rStationId = s1.sId " +
            "left join s_station s2 on r2.rStationId = s2.sId " +
            "left join s_type t on r1.rTypeId = t.tId " +
            "where s1.sStationName = #{startStation} and s2.sStationName = #{arriveStation} " +
            "and r1.rLeaveTime > #{time}" +
            "and r1.rLeaveTime &lt; r2.rArriveTime <if test='type != null'> " +
            "and t.tType = #{type} </if> order by  r1.rLeaveTime</script> ")
    List<TrainShiftDTO> findTrainShift(@Param("startStation") String startStation,
                                       @Param("arriveStation") String arriveStation,
                                       @Param("type") String type,
                                       @Param("time") LocalTime time);

    @Select("select min(tSeatNum) from s_trainshift where tRouteId >= #{trainShift.startId} " +
            "and tRouteId < #{trainShift.endId} " +
            "and tTime >= #{startTime} and tTime < #{endTime}")
    Integer findSeatNum(@Param("trainShift") TrainShiftDTO trainShift,
                        @Param("startTime") LocalDateTime startTime,
                        @Param("endTime") LocalDateTime endTime);

    @Update("update s_trainShift set tSeatNum = tSeatNum - 1 where tRouteId >= #{startId} " +
            "and tRouteId <= #{endId} and tTime < #{date}")
    Boolean buyTicket(@Param("startId") Integer startId,
                      @Param("endId")Integer endId,
                      @Param("date")LocalDate date);
}
