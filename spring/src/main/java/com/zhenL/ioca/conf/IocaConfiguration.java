package com.zhenL.ioca.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * @author zhenL
 * @description
 */
// 相当于beans.xml
@Configuration

// <context:component-scan base-package="com.zhenL.ioca"/>
@ComponentScan(basePackages = "com.zhenL.ioca")

// 相当于我们自己实例化了一个Properties对象, 并将相关数据弄进来
@PropertySource("ioca/db.properties")
public class IocaConfiguration {

    @Value("${url}")
    private String url;

    @Value("${username}")
    private String userName;

    @Value("${passwd}")
    private String passwd;

    @Value("${driver}")
    private String driver;

    @Bean
    public DataSource createDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        System.out.println("config datasources: " + driver + "*" + url
                + "*" + userName + "*" + passwd);

        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(passwd);

        return dataSource;
    }
}
