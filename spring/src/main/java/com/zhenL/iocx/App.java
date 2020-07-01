package com.zhenL.iocx;

import com.zhenL.iocx.controller.IocxController;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zhenL
 * @description
 */
public class App {
    @Test
    public  void test() {

        ApplicationContext context = new ClassPathXmlApplicationContext("iocx/beans.xml");

        IocxController iocxController = context.getBean("controller",IocxController.class);
        String foo = iocxController.iocxFoo("hello spring");
        System.out.println(foo);
    }

    /**
     * 默认: scope="singleton"
     * 要实现多例: scope="prototype"
     */
    @Test
    public void testScope() {
        ApplicationContext context = new ClassPathXmlApplicationContext("iocx/beans.xml");

        for (int i = 0; i < 5; i++) {
            IocxController controller = context.getBean("controller", IocxController.class);
            System.out.println("controller = " + controller);
        }
    }
}
