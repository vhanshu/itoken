package com.provide.serviceredis.service;

/**
 * @author vhans
 */
public interface RedisService {

    /**
     * 储存中间数据
     * @param key
     * @param value
     * @param seconds 超时时间
     */
    void put(String key, Object value, long seconds);

    /**
     * 取中间数据
     * @param key
     * @return 数据
     */
    Object get(String key);
}
