//package com.provide.common.context;
//
//import org.apache.commons.lang3.Validate;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.DisposableBean;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.stereotype.Component;
//
///**
// * 在spring环境中获取非spring容器管理的bean
// * @author vhans
// */
//@Component
//public class ApplicationContextHelder implements ApplicationContextAware, DisposableBean {
//    private static final Logger logger = LoggerFactory.getLogger(ApplicationContextHelder.class);
//
//    private static ApplicationContext applicationContext;
//
//    /**
//     * 获取存储在静态变量中的 ApplicationContext
//     * @return
//     */
//    public static ApplicationContext getApplicationContext(){
//        assertContextInjected();
//        return ApplicationContextHelder.applicationContext;
//    }
//
//    /**
//     * 注入Context到静态变量中
//     * @param context
//     * @throws BeansException
//     */
//    @Override
//    public void setApplicationContext(ApplicationContext context) throws BeansException {
//        ApplicationContextHelder.applicationContext = context;
//    }
//
//    @Override
//    public void destroy() throws Exception {
//        logger.debug("清除 SpringContext 中的 ApplicationContext: {}", applicationContext);
//        applicationContext = null;
//    }
//
//    /**
//     * 从静态变量ApplicationContext中获取bean，转型成所赋值对象的类型
//     */
//    public static <T> T getBean(String name) {
//        assertContextInjected();
//        return (T)applicationContext.getBean(name);
//    }
//    public static <T> T getBean(Class<T> clazz) {
//        assertContextInjected();
//        return applicationContext.getBean(clazz);
//    }
//
//    public static <T> T getBean(String name, Class<T> clazz) {
//        assertContextInjected();
//        return applicationContext.getBean(name, clazz);
//    }
//
//    private static void assertContextInjected(){
//        Validate.validState(applicationContext != null, "applicationContext 属性未注入，请在 spring-context.xml中定义 ApplicationContextHolder");
//    }
//
//
//}