package com.example.jdbc;

import com.alibaba.druid.pool.DruidDataSource;
import com.example.pojo.Student;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcTemplateTest {

    /**
     * 在java中使用jdbcTemplate
     *  --->之后用xml方式使得spring自动实例化
     *
     * jdbcTemplate结合druid
     * */
    @Test
    public void test1(){
        //1.实例化对象

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/studb");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");

        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);

        //2.调用方法
        //jdbcTemplate.update();
    }


    /**
     * 通过ioc容器使用jdbcTemplate
     * */
    @Test
    public void test2(){
        //1.取出组件
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("jdbc-template-bean.xml");
        JdbcTemplate jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);

        //2.调用方法
        List<Student> result = jdbcTemplate.query("select * from students", new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
                Student student = new Student();
                student.setName(rs.getString("name"));
                student.setAge(rs.getInt("age"));
                student.setGender(rs.getString("gender"));
                student.setClasses(rs.getString("class"));
                student.setId(rs.getInt("id"));
                return student;
            }
        });
        System.out.println(result);
    }
}
