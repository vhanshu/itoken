package com.consump.webadmin.service;

import com.consump.webadmin.service.fallback.AdminServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author vhans
 */
@FeignClient(value = "service-admin", fallback  = AdminServiceFallback.class)
public interface AdminService {

}
