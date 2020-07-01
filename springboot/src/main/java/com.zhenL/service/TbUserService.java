package com.zhenL.service;

import com.zhenL.bean.TbUser;

import java.util.List;

/**
 * @author zhenL
 * @description
 */
public interface TbUserService {
    List<TbUser> selectByUserId(String userId);
}
