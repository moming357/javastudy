package com.zhenL.ioca;

import com.zhenL.ioca.controller.IocaController;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zhenL
 * @description
 */
public class App {
    @Test
    public void Test(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ioca/beans.xml");

        IocaController controller = applicationContext.getBean(IocaController.class);

        final String resp = controller.iocxFoo("hello hahaha");

        System.out.println("resp = " + resp);
    }

}
