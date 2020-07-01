package com.zhenL.ioca.controller;

import com.google.errorprone.annotations.concurrent.LazyInit;
import com.zhenL.ioca.service.IocaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.sql.DataSource;

/**
 * @author zhenL
 * @description
 */
@Controller
public class IocaController {

    @Autowired
    private IocaService iocxService;

    public String iocxFoo(String msg){
        System.out.println("controller start");
        return iocxService.iocaFoo(msg);
    }

    @Autowired
    private DataSource dataSource;

    public void callDatasource() {
        System.out.println(dataSource);
    }
}
