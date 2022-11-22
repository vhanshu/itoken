package com.provide.servicesso.service.impl;

import com.common.common.utils.MapperUtils;
import com.provide.commondomain.domain.SysUser;
import com.provide.servicesso.dao.SysUserMapper;
import com.provide.servicesso.service.LoginService;
import com.provide.servicesso.service.consumer.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;


/**
 * @author vhans
 */
@Service
public class LoginServiceImpl implements LoginService {
    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private RedisService redisService;

    @Override
    public SysUser login(String userName, String plantPassword) {
        SysUser sysUser = null;
        String json = redisService.get(userName);
        //缓存中无数据,从数据库中取
        if(json == null || "not_ok".equals(json)){
            Example example = new Example(SysUser.class);
            example.createCriteria().andEqualTo("userName",userName);
            sysUser = sysUserMapper.selectOneByExample(example);
            String password = DigestUtils.md5DigestAsHex(plantPassword.getBytes());
            //输入信息错误
            if (sysUser !=null && !password.equals(sysUser.getPassword())){
                return null;
            }
            //验证通过后将信息存入redis中
            try {
                redisService.put(userName,MapperUtils.obj2json(sysUser),60*60*24);
            } catch (Exception e) {
                logger.warn("=========缓存连接错误=========");
            }
        }
        //缓存中有数据
        else {
            try {
                sysUser = MapperUtils.json2pojo(json, SysUser.class);
            } catch (Exception e) {
                logger.warn("WARN [触发熔断] {}",e.getMessage());
            }
        }
        return sysUser;
    }
}
