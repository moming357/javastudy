package com.zhenL.controller;

import com.zhenL.bean.TbUser;
import com.zhenL.service.TbUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhenL
 * @description
 * @date 2020/6/29
 */
@Slf4j
@RestController
@RequestMapping("user")
public class TbUserController {

    @Autowired
    private TbUserService tbUserService;

    @RequestMapping("/getUser1")
    public List<TbUser> selectTbUser1(String userId){
        log.info(userId);
        return tbUserService.selectByUserId(userId);
    }

    /**
     * @RequestParam(value = "userId", required = true)
     * 设置参数必填相当于
     * if (Strings.isNullOrEmpty(userId)) {
     *     throw new IllegalArgumentException("userId should not be null");
     *        }
     */
    @RequestMapping("/getUser2")
    public List<TbUser> selectTbUser(@RequestParam(value = "userId", required = true)String userId){
        log.info(userId);
        return tbUserService.selectByUserId(userId);
    }

    /**
     *@RequestParam(value = "userId", required = false,defaultValue = "1000002")
     * 允许为空，若传空，默认值为 "1000002"
     */
    @RequestMapping("/getUser3")
    public List<TbUser> selectUser(@RequestParam(value = "userId", required = false,defaultValue = "1000002")String userId){
        log.info(userId);
        return tbUserService.selectByUserId(userId);
    }

    /**
     * 注意： @PathVariable("userId") 要与 @RequestMapping("getUser4/{userId}")的名称一致
     * 相当于在url里拼接参数
     * 访问地址：http://localhost:8081/user/getUser4/1000002
     */
    @RequestMapping("getUser4/{userId}")
    public List<TbUser> selectUser1(@PathVariable("userId")String userId){
        log.info(userId);
        return tbUserService.selectByUserId(userId);
    }

    // @ResponseBody  使用@RestController标识后，此注解可省略
    @RequestMapping(value = "/test", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String tbUser(@RequestBody TbUser user) {
        log.info("test start. userInfo={}", user);
        return "user success";
    }
}
