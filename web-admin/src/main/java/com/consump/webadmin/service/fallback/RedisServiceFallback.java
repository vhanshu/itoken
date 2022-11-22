package com.consump.webadmin.service.fallback;

import com.common.common.hystrix.Fallback;

import com.consump.webadmin.service.RedisService;
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
