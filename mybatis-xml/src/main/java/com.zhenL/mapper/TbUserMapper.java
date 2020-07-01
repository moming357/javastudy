package com.zhenL.mapper;

import com.zhenL.bean.TbUser;
import org.apache.ibatis.annotations.Param;

import java.io.IOException;
import java.util.List;

/**
 * @author zhenL
 * @description
 */
public interface TbUserMapper {

    List<TbUser> selectByUserId(String userId) throws IOException;

    List<TbUser> selectUser(TbUser user);

    List<TbUser> selectUser2(String userId, String userName);

    List<TbUser> selectUser3(@Param("userId") String userId, @Param("userName") String userName);

    List<TbUser> selectUser4(@Param("userId") String userId, @Param("userName") String userName);

    List<TbUser> selectUserForDynamic(TbUser user);

    int insertUser(TbUser user);

    int insertTbUser(TbUser user);

    int insertUsers(List<TbUser> users);

}
