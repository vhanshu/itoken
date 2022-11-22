package com.provide.serviceadmin.dao;

import com.provide.commondomain.domain.SysUser;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.MyMapper;

/**
 * user扩展业务实现
 * @author vhans
 */

@Repository
public interface SysUserExtendMapper extends MyMapper<SysUser> {

}
