package com.zhenL;

import com.zhenL.bean.TbUser;
import com.zhenL.mapper.TbUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author zhenL
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserMapper {

    @Autowired
    private TbUserMapper tbUserMapper;

    @Test
    public void test(){
        List<TbUser> users = tbUserMapper.selectByUserId("1000001");
        for (TbUser user : users) {
            System.out.println("user = " + user);
        }
    }
}
