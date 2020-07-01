package com.zhenL.mapper;

import com.zhenL.bean.TbUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhenL
 * @description
 */
@Repository
@Mapper
public interface TbUserMapper {
    @Select("select * from `tb_user` where `user_id` = #{userId}")
    List<TbUser> selectByUserId(String userId);
}
