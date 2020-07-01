package com.zhenL.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhenL
 * @description
 */
@Slf4j
@RestController
public class PingController {

    @RequestMapping("/ping")
    public String ping(){
        log.info("ping start");
        return "pong";
    }
}
