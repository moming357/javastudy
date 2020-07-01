package com.zhenL.controller;

import com.zhenL.bean.DbConfigInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhenL
 * @description
 */
@Slf4j
@RestController
@RequestMapping("/data")
public class DataController {

    @Resource
    private DbConfigInfo dbConfigInfo;

    @RequestMapping("/d1")
    public DbConfigInfo data3() {
        log.info("data 3 start. dbConfigInfo={}", dbConfigInfo);
        return dbConfigInfo;
    }
}
