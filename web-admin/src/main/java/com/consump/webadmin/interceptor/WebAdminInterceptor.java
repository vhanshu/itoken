package com.consump.webadmin.interceptor;

import com.common.common.utils.CookieUtils;
import com.common.common.utils.MapperUtils;
import com.consump.webadmin.service.RedisService;
import com.provide.commondomain.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author vhans
 */
public class WebAdminInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = CookieUtils.getCookieValue(request, "token");
        //无token,一定未登录,去登录中心
        if(StringUtils.isEmpty(token)){
            response.sendRedirect("http://localhost:8303/login.g?url=http://localhost:8501/index.g");
            return false;
        }
        //有token,可能登录
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HttpSession session = request.getSession();
        SysUser sysUser = (SysUser) session.getAttribute("sysUser");
        //确实登录
        if(sysUser != null && modelAndView != null){
            modelAndView.addObject("sysUser", sysUser);
        }
        //未登录或第一次登录无session
        else {
            String token = CookieUtils.getCookieValue(request, "token");
            if(!StringUtils.isEmpty(token)){
                String userName = redisService.get(token);
                if(!StringUtils.isEmpty(userName) && !"not_ok".equals(userName)){
                    String json = redisService.get(userName);
                    //已在sso上完成注册,创建局部会话
                    if(!StringUtils.isEmpty(json) && !"not_ok".equals(json)){
                        sysUser = MapperUtils.json2pojo(json,SysUser.class);
                        if(sysUser != null && modelAndView != null){
                            modelAndView.addObject("sysUser", sysUser);
                        }
                        request.getSession().setAttribute("sysUser", sysUser);
                    }
                    //确实未登录
                    else {
                        response.sendRedirect("http://localhost:8303/login.g?url=http://localhost:8501/index.g");
                    }
                }
            }
        }
    }
}
