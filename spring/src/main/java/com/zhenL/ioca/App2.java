package com.zhenL.ioca;

import com.zhenL.ioca.conf.IocaConfiguration;
import com.zhenL.ioca.controller.IocaController;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author zhenL
 * @description
 */
public class App2 {
    @Test
    public void test(){
        ApplicationContext context = new AnnotationConfigApplicationContext(IocaConfiguration.class);
        IocaController controller = context.getBean(IocaController.class);
        controller.iocxFoo("hello");
    }

    @Test
    public void test1(){
        ApplicationContext context = new AnnotationConfigApplicationContext(IocaConfiguration.class);
        IocaController controller = context.getBean(IocaController.class);
        controller.callDatasource();
    }
}
