package com.zhenL.service.impl;

import com.zhenL.bean.TbUser;
import com.zhenL.mapper.TbUserMapper;
import com.zhenL.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhenL
 * @description
 */
@Service
public class TbUserServiceImpl implements TbUserService {

    @Autowired
    private TbUserMapper tbUserMapper;
    public List<TbUser> selectByUserId(String userId) {
        return tbUserMapper.selectByUserId(userId);
    }
}
