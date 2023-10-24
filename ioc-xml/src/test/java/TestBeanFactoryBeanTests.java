import com.example.factorybean.TestBean;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TestBeanFactoryBeanTests {
    @Test
    public void factoryBeanTest(){
        //1.创建ioc容器
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("factorybean.xml");

        //2.读取组件
        TestBean testBean = applicationContext.getBean("testBean", TestBean.class);
        System.out.println(testBean);

        //3.结束ioc容器
        applicationContext.close();
    }
}
