package com.provide.common.dao;


import com.provide.common.utils.RedisCache;
import com.provide.commondomain.domain.SysUser;
import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.context.annotation.Primary;
import tk.mybatis.mapper.MyMapper;

/**
 * user通用业务实现
 * @author vhans
 */
@Primary
@CacheNamespace(implementation = RedisCache.class)
public interface SysUserMapper extends MyMapper<SysUser> {
}