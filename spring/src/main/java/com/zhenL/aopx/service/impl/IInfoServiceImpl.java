package com.zhenL.aopx.service.impl;

import com.zhenL.aopx.service.IInfoService;

/**
 * @author zhenL
 * @description
 */
public class IInfoServiceImpl implements IInfoService {
    @Override
    public String addInfo(String info) {

        System.out.println("InfoServiceImpl.addInfo");

        return "InfoServiceImpl.addInfo";
    }

    @Override
    public String updateInfo(String info) {

        System.out.println("InfoServiceImpl.updateInfo");

        return "InfoServiceImpl.updateInfo";
    }

    @Override
    public String delInfo(String info) {

        System.out.println("InfoServiceImpl.delInfo");

        return "InfoServiceImpl.delInfo";
    }

    @Override
    public String queryInfo(String info) {

        System.out.println("InfoServiceImpl.queryInfo");

        return "InfoServiceImpl.queryInfo";
    }
}
