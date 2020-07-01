package com.zhenL.iocx.controller;

import com.zhenL.iocx.service.IocxService;

/**
 * @author zhenL
 * @description
 */
public class IocxController {

    private IocxService iocxService;

    public String iocxFoo(String msg){
        System.out.println("controller start");
        return iocxService.iocxFoo(msg);
    }
    public void setIocxService(IocxService iocxService) {
        this.iocxService = iocxService;
    }
}
