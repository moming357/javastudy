package com.zhenL.ioca.service.impl;

import com.zhenL.ioca.dao.IocaDao;
import com.zhenL.ioca.service.IocaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhenL
 * @description
 */
@Service
public class IocaServiceImpl implements IocaService {

    @Autowired
    private IocaDao iocaDao;

    @Override
    public String iocaFoo(String msg) {
        System.out.println("iocxServiceImpl start");
        return iocaDao.iocaFoo("iocxServiceImpl response");
    }
}
