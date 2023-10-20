package com.itheima;
import com.itheima.controller.DeptController;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class SpringbootWebConfig2ApplicationTests {

    @Autowired
    private ApplicationContext applicationContext; //IOC容器对象

    //bean的作用域
    @Test
    public void testScope(){
        for (int i = 0; i < 10; i++) {
            DeptController deptController = applicationContext.getBean(DeptController.class);
            System.out.println(deptController);
        }
    }

    //第三方bean的管理
    @Test
    public void testThirdBean() throws Exception {
        SAXReader saxReader1 = new SAXReader();

        Document document = saxReader1.read(this.getClass().getClassLoader().getResource("1.xml"));
        Element rootElement = document.getRootElement();
        String name = rootElement.element("name").getText();
        String age = rootElement.element("age").getText();

        System.out.println(name + " : " + age);
    }
    @Autowired
    private SAXReader saxReader;
    @Test
    public void testThirdBean_1() throws Exception {

        Document document = saxReader.read(this.getClass().getClassLoader().getResource("1.xml"));
        Element rootElement = document.getRootElement();
        String name = rootElement.element("name").getText();
        String age = rootElement.element("age").getText();
        System.out.println(name + " : " + age);
    }

    @Test
    public void testGetBean2(){
        //通过@Bean注解的name/value属性指定bean名称, 如果未指定, 默认是方法名
        // new SAXReader();
        Object saxReader1 = applicationContext.getBean("reader");
        System.out.println(saxReader1);
    }


}
