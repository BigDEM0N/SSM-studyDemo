package com.example;

import com.example.ioc_annotation01.CommonComponent;
import com.example.ioc_annotation01.TestController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

public class IocAnnotation01Test {

    @Test
    public void test01(){
        //1.获取ioc容器
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("ioc_annotation01.xml");
        //2.获取组件
        CommonComponent commonComponent = applicationContext.getBean("commonComponent", CommonComponent.class);
        System.out.println(commonComponent);
        TestController testController = applicationContext.getBean(TestController.class);
        System.out.println(testController);
        //3.close
        applicationContext.close();
    }

    @Test
    public void test02(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("ioc_annotation01.xml");
        TestController testController = applicationContext.getBean(TestController.class);
        testController.value();
        applicationContext.close();
    }

}
