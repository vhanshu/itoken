package com.provide.servicesso.controller;

import com.alibaba.druid.util.StringUtils;
import com.common.common.utils.CookieUtils;
import com.common.common.utils.MapperUtils;
import com.provide.commondomain.domain.SysUser;
import com.provide.servicesso.service.LoginService;
import com.provide.servicesso.service.consumer.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.UUID;

/**
 * @author vhans
 */

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private RedisService redisService;

    /**
     * 跳转登陆页面
     * @return 登陆页面或已登录状态的跳转
     */
    @RequestMapping(value = "login.g", method = RequestMethod.GET)
    public String login(HttpServletRequest request,
                        @RequestParam(required = false) String url,
                        Model model){
        model.addAttribute("year", Calendar.getInstance().get(Calendar.YEAR));
        String token = CookieUtils.getCookieValue(request, "token");
        //token有值
        if(!StringUtils.isEmpty(token)){
            String userName = redisService.get(token);
            if(!StringUtils.isEmpty(userName) && !"not_ok".equals(userName)){
                String json = redisService.get(userName);
                if(!StringUtils.isEmpty(json)){
                    try {
                        SysUser sysUser = MapperUtils.json2pojo(json, SysUser.class);
                        //已登录
                        if(sysUser != null){
                            if(!StringUtils.isEmpty(url)){
                                model.addAttribute("url", url);
                                return "redirect:"+url;
                            }else {
                                model.addAttribute("sysUser", sysUser);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        if(!StringUtils.isEmpty(url)){
            model.addAttribute("url", url);
        }
        return "login";
    }

    /**
     * 登录业务
     * @param request
     * @param response
     * @param url
     * @param userName
     * @param password
     * @param redirectAttributes
     * @return 首页或重定向login
     */
    @RequestMapping(value = "login.p", method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpServletResponse response,
                        @RequestParam(required = false) String url,
                        String userName, String password,
                        RedirectAttributes redirectAttributes){
        SysUser sysUser = loginService.login(userName, password);
        //登录成功
        if(sysUser != null){
            String token = UUID.randomUUID().toString();
            String result = redisService.put(token, userName, 60 * 60 * 24);
            //put成功
            if("ok".equals(result)){
                CookieUtils.setCookie(request, response, "token", token, 60*60*24);
                if(!StringUtils.isEmpty(url)){
                    return "redirect:"+url;
                }
            }
            //失败（熔断）
            else {
                redirectAttributes.addFlashAttribute("message", "发生网络错误");
            }
        }
        //登录失败
        else {
            redirectAttributes.addFlashAttribute("message", "用户名或密码错误，请重试");
        }
        return "redirect:/login.g";
    }
}
