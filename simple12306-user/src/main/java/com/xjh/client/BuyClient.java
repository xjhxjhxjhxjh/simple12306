package com.xjh.client;

import com.xjh.client.impl.BuyClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xjhxjhxjh
 * @date 2020/2/21 11:32
 */
@FeignClient(value = "simple12306-ticket", fallback = BuyClientImpl.class)
public interface BuyClient {

    @GetMapping("/buy")
    Boolean buyTicket(@RequestParam("startId")final Integer startId,
                      @RequestParam("endId")final Integer endId,
                      @RequestParam("date")final String date);
}
