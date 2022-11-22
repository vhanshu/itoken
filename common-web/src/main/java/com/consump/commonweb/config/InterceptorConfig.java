package com.consump.commonweb.config;

import com.consump.commonweb.constant.RuntimeConstant;
import com.consump.commonweb.interceptor.ConstantsInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置
 * @author vhans
 */

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private ConstantsInterceptor constantsInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(constantsInterceptor).addPathPatterns("/**");
    }
}
