package com.zhenL.aopa.handler;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author zhenL
 * @description
 */
@Aspect
@Component
public class PrintLogHandler {

  /**
   * 切入点
   */
  @Pointcut("execution(* com.zhenL.aopa.service.impl.*.*(..))")
  public void pp() {

  }

  /**
   * 入口log,通知: 前置通知
   */
  @Before("pp()")
  public void preLog() {
    System.out.println("注解版:before log start");
  }

  /**
   * 出口log
   */
  @AfterReturning("pp()")
  public void postLog() {
    System.out.println("注解版:after log start");
  }

  /**
   * 异常通知
   */
  @AfterThrowing("pp()")
  public void errLog() {
    System.out.println("注解版:哈哈 出异常啦");
  }

  /**
   * 最终通知
   */
  @After("pp()")
  public void finalLog() {
    System.out.println("注解版:最终通知");
  }

  /**
   * 环绕通知
   */
  @Around("pp()")
  public Object aroundTest(ProceedingJoinPoint joinPoint) {
    try {
      System.out.println("around before log");

      Object response = joinPoint.proceed();

      System.out.println("around after log");

      return response;
    } catch (Throwable t) {
      System.out.println("around exception log");
    } finally {
      System.out.println("around finally log");
    }

    return null;
  }

}
