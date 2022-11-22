package com.provide.servicesso.service;

import com.provide.commondomain.domain.SysUser;

/**
 * 后台单点登录业务
 * @author vhans
 */
public interface LoginService {

    public SysUser login(String userName, String plantPassword);
}
