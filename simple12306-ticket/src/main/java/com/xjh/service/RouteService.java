package com.xjh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjh.dto.RouteDTO;
import com.xjh.entity.Route;

import java.util.List;

/**
 * @author xjhxjhxjh
 * @date 2020/2/18 15:47
 */
public interface RouteService extends IService<Route> {
    List<RouteDTO> getAllRoute();
    Route findRoutedName(Integer rId);
}
