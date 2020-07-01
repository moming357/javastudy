package com.zhenL;

import com.google.common.io.Resources;
import com.zhenL.bean.TbUser;
import com.zhenL.mapper.TbUserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author zhenL
 * @description
 */
public class AppInsertUser {
    public static void main(String[] args) throws IOException {
        InputStream ins = Resources.getResource(MyBatisConst.CONFIG_FILE_NAME).openStream();
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(ins);
        SqlSession session = factory.openSession(true);
        TbUserMapper tbUserMapper = session.getMapper(TbUserMapper.class);
        int result= tbUserMapper.insertUser(new TbUser("5","haha"));

        System.out.println("result = " + result);
        session.close();
    }
}
