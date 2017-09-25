package com.huawei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Controller
@RequestMapping(value="/huawei")
public class HuaweimanageApplication {
    @RequestMapping(value = "/demo")
    public String demo(){
        return "index";
    }
    public static void main(String[] args) {
        SpringApplication.run(HuaweimanageApplication.class, args);
    }
}
