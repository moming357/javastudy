package com.zhenL.aopx.handler;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author zhenL
 * @description
 */
public class PrintLogHandler {

  /**
   * 入口log,通知: 前置通知
   */
  public void preLog() {
    System.out.println("before log start");
  }

  /**
   * 出口log
   */
  public void postLog() {
    System.out.println("after log start");
  }

  /**
   * 异常通知
   */
  public void errLog() {
    System.out.println("哈哈 出异常啦");
  }

  /**
   * 最终通知
   */
  public void finalLog() {
    System.out.println("最终通知");
  }

  /**
   * 环绕通知
   */
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
