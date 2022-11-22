package com.provide.common.utils;

import com.provide.common.context.ApplicationContextHelder;
import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Redis缓存数据操作
 * @author vhans
 */
public class RedisCache implements Cache {
    private static final Logger logger = LoggerFactory.getLogger(RedisCache.class);

    private final String id;

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    
    private RedisTemplate redisTemplate;
    
    private static final long EXPIRE_TIME_IN_MINUTES = 30; //redis过期时间

    public RedisCache(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("缓存实例需要一个ID");
        }
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        try{
            RedisTemplate redisTemplate = getRedisTemplate();
            ValueOperations opsForValue = redisTemplate.opsForValue();
            opsForValue.set(key, value, EXPIRE_TIME_IN_MINUTES, TimeUnit.MINUTES);
            logger.debug("将查询结果上传到redis");
        } catch (Throwable t){
            logger.error("上传redis数据失败",t);
        }
    }

    @Override
    public Object getObject(Object key) {
        try{
            RedisTemplate redisTemplate = getRedisTemplate();
            ValueOperations opsForValue = redisTemplate.opsForValue();
            logger.debug("从redis上获取数据");
            return opsForValue.get(key);
        } catch (Throwable t){
            logger.error("获取redis数据失败",t);
            return null;
        }
    }

    @Override
    public Object removeObject(Object key) {
        try{
            RedisTemplate redisTemplate = getRedisTemplate();
            redisTemplate.delete(key);
            logger.debug("从redis上删除数据");
        } catch (Throwable t){
            logger.error("删除redis数据失败",t);
        }
        return null;
    }

    @Override
    public void clear() {
        RedisTemplate redisTemplate = getRedisTemplate();
        redisTemplate.execute((RedisCallback) connection -> {
            connection.flushDb();
            return null;
        });
        logger.debug("清除从redis获得的所有缓存查询结果");
    }

    @Override
    public int getSize() {
        return 0;
    }


    @Override
    public ReadWriteLock getReadWriteLock() {
        return this.readWriteLock;
    }

    private RedisTemplate getRedisTemplate(){
        if(redisTemplate == null){
            redisTemplate = ApplicationContextHelder.getBean("redisTemplate");
        }
        return redisTemplate;
    }
}
