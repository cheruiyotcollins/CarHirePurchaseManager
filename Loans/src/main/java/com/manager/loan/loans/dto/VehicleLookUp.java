package com.manager.loan.loans.dto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("assets-microservice")
public interface VehicleLookUp {

    @RequestMapping("/vehicle/model/exist/{id}")
    Boolean existById(@PathVariable("id") Long id);
}
