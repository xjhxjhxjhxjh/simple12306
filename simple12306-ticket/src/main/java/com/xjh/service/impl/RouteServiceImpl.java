package com.xjh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjh.dto.RouteDTO;
import com.xjh.entity.Route;
import com.xjh.mapper.RouteMapper;
import com.xjh.service.RouteService;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xjhxjhxjh
 * @date 2020/2/18 15:54
 */
@Service
@Transactional
public class RouteServiceImpl extends ServiceImpl<RouteMapper, Route> implements RouteService {

    @Override
    public List<RouteDTO> getAllRoute() {
        return this.baseMapper.getAllRoute();
    }

    @Override
    public Route findRoutedName(Integer rId) {
        QueryWrapper<Route> wrapper = new QueryWrapper<>();
        return baseMapper.selectOne(wrapper.select("rName").eq("rId", rId));
    }
}
