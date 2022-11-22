package com.provide.servicesso.service.consumer;

import com.provide.servicesso.service.consumer.fallback.RedisServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author vhans
 */

@FeignClient(value = "service-redis", fallback = RedisServiceFallback.class)
public interface RedisService {

    @RequestMapping(value = "put", method = RequestMethod.POST)
    String put(@RequestParam(value = "key") String key,
               @RequestParam(value = "value") String value,
               @RequestParam(value = "seconds") long seconds);

    @RequestMapping(value = "get", method = RequestMethod.GET)
    String get(@RequestParam(value = "key") String key);
}
