package com.example;

import com.example.controller.StudentController;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Lab2Test {
    @Test
    public void test1(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("Lab2.xml");
        StudentController studentController = applicationContext.getBean(StudentController.class);
        System.out.println(studentController.findAll());
    }
}
