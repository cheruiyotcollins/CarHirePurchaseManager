package com.loan.users;


import org.springframework.web.bind.annotation.RequestMapping;

public interface GreetingControllerSDS {
    @RequestMapping("/greeting")
    String greeting();
}
