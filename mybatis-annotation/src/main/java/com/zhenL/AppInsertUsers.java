package com.zhenL;

import com.google.common.collect.Lists;
import com.google.common.io.Resources;
import com.zhenL.bean.TbUser;
import com.zhenL.mapper.TbUserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author zhenL
 * @description
 */
public class AppInsertUsers {

    public static void main(String[] args) throws IOException {
        InputStream ins = Resources.getResource(MyBatisConst.CONFIG_FILE_NAME).openStream();
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(ins);
        SqlSession session = factory.openSession(true);
        TbUserMapper tbUserMapper = session.getMapper(TbUserMapper.class);

        TbUser u1 = new TbUser("1111", "aaaa");
        TbUser u2 = new TbUser("2222", "bbbb");
        TbUser u3 = new TbUser("3333", "cccc");

        int rows = tbUserMapper.insertUsers(Lists.newArrayList(u1, u2, u3));

        System.out.println("rows = " + rows);

        System.out.println("u1 = " + u1);
        System.out.println("u2 = " + u2);
        System.out.println("u3 = " + u3);

        session.close();
    }
}
