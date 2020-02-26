package com.xjh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjh.entity.Station;

import java.util.List;

/**
 * @author xjhxjhxjh
 * @date 2020/2/18 15:47
 */
public interface StationService extends IService<Station> {

    List<String> findStationCityOrStation(String name);
}
