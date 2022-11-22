package com.provide.serviceadmin.controller;

import com.common.common.dto.BaseResult;
import com.github.pagehelper.PageInfo;
import com.provide.commondomain.domain.SysUser;
import com.provide.serviceadmin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author vhans
 */

@RestController
@RequestMapping("v1/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "page/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public BaseResult page(@PathVariable(required = true) int pageNum,
                           @PathVariable(required = true) int pageSize,
                           @RequestParam(required = false) SysUser sysUser){
        PageInfo pageInfo = adminService.page(pageNum, pageSize, sysUser);
        List list = pageInfo.getList();
        BaseResult.Cursor cursor = new BaseResult.Cursor();
        cursor.setTotal(new Long(pageInfo.getTotal()).intValue());
        cursor.setOffset(pageInfo.getPageNum());
        cursor.setLimit(pageInfo.getPageSize());
        return BaseResult.ok(list, cursor);
    }
}
