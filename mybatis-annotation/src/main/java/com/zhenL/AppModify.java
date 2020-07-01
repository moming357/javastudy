package com.zhenL;

import com.google.common.io.Resources;
import com.zhenL.bean.TbUser;
import com.zhenL.mapper.TbUserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author zhenL
 * @description
 */
public class AppModify {

    private TbUserMapper userMapper;

    @Before
    public void init() throws IOException {
        InputStream ins = Resources.getResource(MyBatisConst.CONFIG_FILE_NAME).openStream();
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(ins);
        SqlSession session = factory.openSession(true);
        this.userMapper = session.getMapper(TbUserMapper.class);
    }

    @Test
    public void testInsert() {
        TbUser tbUser = new TbUser("2000006", "haha2");
        System.out.println("before insert tbUser = " + tbUser);
        int effectRows = userMapper.insertUser(tbUser);
        System.out.println("after insert tbUser = " + tbUser);
        System.out.println("effectRows = " + effectRows);
    }

    @Test
    public void testInsert1() {
        boolean isInsertOk = userMapper.insertUser2(new TbUser("2000002", "lisi2"));
        System.out.println("isInsertOk = " + isInsertOk);
    }

    @Test
    public void testUpdate() {
        int effectRows = userMapper.updateUser(new TbUser(5L, "2000003", "wangwu"));
        System.out.println("effectRows = " + effectRows);
        Assert.assertEquals(1, effectRows);
    }

    @Test
    public void testDelete() {
        int rows = userMapper.deleteByUserId("2000002");
        Assert.assertEquals(1, rows);
    }
}
