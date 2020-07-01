package com.zhenL.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author zhenL
 * @description
 */
@Component
@PropertySource("classpath:dbconfig.properties")
@ConfigurationProperties(prefix = "db")
@Getter
@Setter
@ToString
public class DbConfigInfo {
    private String url;
    private String userName;
    private String passwd;
    private String driverClassName;
}
