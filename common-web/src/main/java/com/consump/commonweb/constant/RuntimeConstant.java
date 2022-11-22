package com.consump.commonweb.constant;

import java.util.Arrays;
import java.util.List;

/**
 * 系统运行时的常量
 * @author vhans
 */
public class RuntimeConstant {
    //后台登录拦截器无需拦截的url
    public static List<String> loginExcludePathPatterns = Arrays.asList(
//            "/login",
            "/error",
            "/cpacha/generate_cpacha",
            "/photo/view"
    );
    //权限拦截器无需拦截的url
    public static List<String> authorityExcludePathPatterns = Arrays.asList(
            "login",
            "/index",
            "/no_right",
            "/error",
            "/cpacha/generate_cpacha",
            "/photo/view"
    );

    //前台登录拦截器无需拦截的url
    public static List<String> homeLoginExcludePathPatterns = Arrays.asList(
            "/admin/**",
            "/error",
            "/cpacha/generate_cpacha",

            "/home/css/**",
            "/home/js/**",
            "/home/images/**",
            "/home/system/**",
            "/home/product/**",
            "/home/common/error",
            "/photo/view"
    );
}
