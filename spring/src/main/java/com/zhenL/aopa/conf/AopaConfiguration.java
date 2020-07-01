package com.zhenL.aopa.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author zhenL
 * @description
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.zhenL.aopa")
public class AopaConfiguration {

}
