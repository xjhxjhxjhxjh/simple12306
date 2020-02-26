package com.xjh.client;

import com.xjh.client.impl.OrderClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xjhxjhxjh
 * @date 2020/2/24 14:55
 */
@FeignClient(value = "simple12306-order", fallback = OrderClientImpl.class)
public interface OrderClient {

    @GetMapping("/order/addOrder")
    Boolean addOrder(@RequestParam("uId") Long uId, @RequestParam("date") String date,
                     @RequestParam("startId") Integer startId, @RequestParam("endId") Integer endId);
}
