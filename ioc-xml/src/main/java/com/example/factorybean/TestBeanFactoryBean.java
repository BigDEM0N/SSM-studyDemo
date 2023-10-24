package com.example.factorybean;

import org.springframework.beans.factory.FactoryBean;

public class TestBeanFactoryBean implements FactoryBean<TestBean> {

    @Override
    public TestBean getObject() throws Exception {
        return new TestBean();
    }

    @Override
    public Class<?> getObjectType() {
        return TestBean.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
