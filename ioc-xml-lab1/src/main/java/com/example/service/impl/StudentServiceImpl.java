package com.example.service.impl;

import com.example.dao.StudentDao;
import com.example.pojo.Student;
import com.example.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao;

    public void setStudentDao(StudentDao studentDao){
        this.studentDao = studentDao;
    }

    @Override
    public List<Student> findAll() {
        return studentDao.queryAll();
    }
}
