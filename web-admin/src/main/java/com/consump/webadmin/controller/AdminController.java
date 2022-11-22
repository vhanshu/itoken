package com.consump.webadmin.controller;

import com.consump.webadmin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author vhans
 */

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = {"", "/index.g"}, method = RequestMethod.GET)
    public String index(){
        return "index";
    }
}
