package com.xjh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjh.entity.Station;
import com.xjh.mapper.StationMapper;
import com.xjh.service.RouteService;
import com.xjh.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xjhxjhxjh
 * @date 2020/2/18 15:57
 */
@Service
@Transactional
public class StationServiceImpl extends ServiceImpl<StationMapper, Station> implements StationService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public List<String> findStationCityOrStation(String name) {
        List<String> station = new ArrayList<>();
        if (name.endsWith("站")) {
            station.add(name);
        } else {
            // 先从redis从查询
            station = redisTemplate.opsForList().range(name, 0, -1);
            if (station.size() == 0) {
                QueryWrapper<Station> wrapper = new QueryWrapper<>();
                wrapper.select("sStationName");
                wrapper.eq("sCityName", name);
                List<Station> stations = baseMapper.selectList(wrapper);
                for (Station station1 : stations) {
                    station.add(station1.getSStationName());
                }
                // 往redis里添加
                if (!CollectionUtils.isEmpty(station)) {
                    redisTemplate.opsForList().leftPushAll(name, station);
                } else {
                    // 解决redis击穿
                    redisTemplate.opsForList().leftPushAll(name, "-1");
                }
            }
        }
        return station;
    }
}
