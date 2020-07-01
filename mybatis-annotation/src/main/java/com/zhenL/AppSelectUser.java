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
public class AppSelectUser {

    public static void main(String[] args) throws IOException {
        InputStream ins = Resources.getResource(MyBatisConst.CONFIG_FILE_NAME).openStream();
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(ins);
        SqlSession session = factory.openSession(true);
        TbUserMapper tbUserMapper = session.getMapper(TbUserMapper.class);
        List<TbUser> users = tbUserMapper.selectUser("1000002","zhangsan2");
        users.forEach(u -> System.out.println("u = " + u));

        session.close();
    }
}
