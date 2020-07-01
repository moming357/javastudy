package com.zhenL.ioca.dao.impl;

import com.zhenL.ioca.dao.IocaDao;
import org.springframework.stereotype.Repository;

/**
 * @author zhenL
 * @description
 */
@Repository
public class IocaDaoImpl implements IocaDao {
    public String iocaFoo(String msg) {
        System.out.println("IocaDaoImpl start");
        return "IocxDaoImpl response";
    }
}
