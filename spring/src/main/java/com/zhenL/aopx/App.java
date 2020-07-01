package com.zhenL.aopx;

import com.zhenL.aopx.service.IInfoService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zhenL
 * @description
 */
public class App {

  public static void main(String[] args) {
    ApplicationContext context = new ClassPathXmlApplicationContext("aopx/beans.xml");

    IInfoService infoService = context.getBean(IInfoService.class);

    // infoService.addInfo("hello");
    infoService.updateInfo("hello");
  }

}
