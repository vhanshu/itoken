package com.provide.servicesso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author vhans
 */
@SpringBootApplication(scanBasePackages = {"com.provide", "com.consump", "com.common"})
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan(basePackages = "com.provide.servicesso.dao")
public class ServiceSsoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceSsoApplication.class, args);
    }
}
