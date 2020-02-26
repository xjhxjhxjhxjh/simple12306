package com.xjh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjh.dto.OrderDTO;
import com.xjh.entity.Order;

import java.util.List;

/**
 * @author xjhxjhxjh
 * @date 2020/2/24 14:58
 */
public interface OrderService extends IService<Order> {
    Boolean addOrder(Long uId, String date, Integer startId, Integer endId);

    List<OrderDTO> findOrder(Long uId);
}
