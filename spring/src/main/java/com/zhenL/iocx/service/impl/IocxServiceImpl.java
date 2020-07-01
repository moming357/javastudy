package com.zhenL.iocx.service.impl;

import com.zhenL.iocx.dao.IocxDao;
import com.zhenL.iocx.service.IocxService;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author zhenL
 * @description
 */
public class IocxServiceImpl implements IocxService {

    private Set<String> infos;

    private List<String> accounts;

    private Map<String, String> mapData;

    private IocxDao iocxDao;


    public String iocxFoo(String msg) {
        System.out.println("IocxServiceImpl init -----处理业务逻辑");
        System.out.println("infos = " + infos);
        System.out.println("accounts = " + accounts);
        System.out.println("mapData = " + mapData);
        return iocxDao.iocxFoo("iocxServiceImpl response");
    }

    public void setMapData(Map<String, String> mapData) {
        this.mapData = mapData;
    }

    public void setAccounts(List<String> accounts) {
        this.accounts = accounts;
    }

    public void setInfos(Set<String> infos) {
        this.infos = infos;
    }

    public void setIocxDao(IocxDao iocxDao) {
        this.iocxDao = iocxDao;
    }

}
