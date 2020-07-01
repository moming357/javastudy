package com.zhenL.aopa;

import com.zhenL.aopa.conf.AopaConfiguration;
import com.zhenL.aopa.service.IInfoService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author zhenL
 * @description
 */
public class App {

  public static void main(String[] args) {
    ApplicationContext context = new AnnotationConfigApplicationContext(AopaConfiguration.class);
    IInfoService infoService = context.getBean(IInfoService.class);
    infoService.addInfo("hahahaha");
  }
}
