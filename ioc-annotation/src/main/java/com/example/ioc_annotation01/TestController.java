package com.example.ioc_annotation01;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

@Controller
public class TestController {
    @Value("${value.test1}")
    private String value;

    public void value(){
        System.out.println(value);
    }
}
