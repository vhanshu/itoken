package com.provide.serviceadmin.service.impl;

import com.provide.common.dao.SysUserMapper;
import com.provide.common.service.impl.BaseServiceImpl;
import com.provide.commondomain.domain.SysUser;
import com.provide.serviceadmin.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author vhans
 */
@Service
@Transactional(readOnly = true)
public class AdminServiceImpl extends BaseServiceImpl<SysUser, SysUserMapper> implements AdminService<SysUser> {

}
