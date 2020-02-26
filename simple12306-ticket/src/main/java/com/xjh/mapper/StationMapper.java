package com.xjh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjh.entity.Station;

import java.util.List;

/**
 * @author xjhxjhxjh
 * @date 2020/2/18 15:43
 */
public interface StationMapper extends BaseMapper<Station> {
    List<Station> selectByMap(String sCityName, String cityName);
}