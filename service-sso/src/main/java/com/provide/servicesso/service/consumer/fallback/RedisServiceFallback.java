package com.provide.servicesso.service.consumer.fallback;

import com.common.common.hystrix.Fallback;
import com.provide.servicesso.service.consumer.RedisService;
import org.springframework.stereotype.Component;

/**
 * @author vhans
 */

@Component
public class RedisServiceFallback implements RedisService {
    @Override
    public String put(String key, String value, long seconds) {
        return Fallback.badGateway();
    }

    @Override
    public String get(String key) {
        return Fallback.badGateway();
    }
}
