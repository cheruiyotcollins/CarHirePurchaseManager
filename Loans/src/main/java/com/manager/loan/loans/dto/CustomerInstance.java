package com.manager.loan.loans.dto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("customer-microservice")
//@Service
//@Component
public interface CustomerInstance {
    @RequestMapping("/customer/exist/{id}")
    Boolean existById(@PathVariable("id") Long id);

    @RequestMapping("/customer/email/{id}")
    StringDto nameNMail(@PathVariable("id") Long id);
}
