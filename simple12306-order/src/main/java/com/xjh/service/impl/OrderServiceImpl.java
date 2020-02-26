package com.xjh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjh.dto.OrderDTO;
import com.xjh.entity.Order;
import com.xjh.mapper.OrderMapper;
import com.xjh.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author xjhxjhxjh
 * @date 2020/2/24 14:59
 */
@Service
@Transactional
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public Boolean addOrder(Long uId, String date, Integer startId, Integer endId) {
        redisTemplate.delete(startId + endId + date + uId);
        return  save(new Order(null, uId, startId, endId, LocalDateTime.now(),
                LocalDate.parse(date)));
    }

    @Override
    public List<OrderDTO> findOrder(Long uId) {
        return baseMapper.findOrder(uId);
    }
}