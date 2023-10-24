package com.example.controller;

import com.example.dao.StudentDao;
import com.example.pojo.Student;
import com.example.service.StudentService;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {
    @GetMapping("/students")
    public List<Student> findAll(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("ioc-xml-lab1.xml");
        StudentService studentService = applicationContext.getBean("studentService", StudentService.class);
        return studentService.findAll();
    }
}
