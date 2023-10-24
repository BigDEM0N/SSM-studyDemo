package com.example.dao.impl;

import com.example.dao.StudentDao;
import com.example.pojo.Student;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class StudentDaoImpl implements StudentDao {
//    @Override
//    public List<Student> queryAll() {
//        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("jdbc-template-bean.xml");
//        JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);
//        List<Student> result = jdbcTemplate.query("select * from students", (rs, rowNum) -> {
//            Student student = new Student();
//            student.setName(rs.getString("name"));
//            student.setAge(rs.getInt("age"));
//            student.setGender(rs.getString("gender"));
//            student.setClasses(rs.getString("class"));
//            student.setId(rs.getInt("id"));
//            return student;
//        });
//        return result;
//    }
    //注入jdbcTemplate
    private JdbcTemplate jdbcTemplate;
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Student> queryAll() {
        List<Student> students = jdbcTemplate.query("select id,name,gender,age,class as classes from students",new BeanPropertyRowMapper<>(Student.class));
        return students;
    }
}
