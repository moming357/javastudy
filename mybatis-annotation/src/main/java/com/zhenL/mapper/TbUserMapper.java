package com.zhenL.mapper;

import com.zhenL.bean.TbUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author zhenL
 * @description
 */
public interface TbUserMapper {

    @Select("select * from `tb_user` where `user_id`=#{userId}")
    List<TbUser> selectByUserId(String userId);

    /**
     * Caused by: org.apache.ibatis.binding.BindingException: Parameter 'userId' not found. Available parameters are [arg1, arg0, param1, param2]
     * //     * @param userId
     * //     * @param userName
     *
     * @return
     */
    @Select("select * from `tb_user` where `user_id` = #{userId} AND `user_name` = #{userName}")
    // List<TbUser> selectUser(String arg0, String agr1); // 此时mybatis拿到的class字节码参数名就是arg0,arg1,根本无法与SQL做替换，因此需要用另外的注解@Param来帮助标识
    List<TbUser> selectUser(@Param("userId") String userId, @Param("userName") String userName);

    // ------ insert start ------

    @Insert("insert into `tb_user`(`user_id`,`user_name`) values(#{userId},#{userName})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertUser(TbUser user);

    @Insert("insert into `tb_user`(`user_id`,`user_name`) values(#{userId},#{userName})")
    boolean insertUser2(TbUser user);

    @Insert("insert into `tb_user`(`user_id`,`user_name`) values(#{userId},#{userName})")
    int insertUser3(String userId, String userName); // 这个跟刚才的问题一样，识别不到参数

    // ------ update start ------
    @Update("update `tb_user` set `user_id`=#{userId},`user_name`=#{userName} where `id`=#{id}")
    int updateUser(TbUser user);

    // ------ delete start ------
    @Delete("delete from `tb_user` where `user_id` = #{userId}")
    int deleteByUserId(String userId);

    // ---- dynamic sql
    @Select({
            "<script>" +
                    "        select * from `tb_user` where 1 = 1" +
                    "        <if test='userId != null'>" +
                    "            AND `user_id` = #{userId}" +
                    "        </if>" +
                    "        <if test='userName != null'>" +
                    "            AND `user_name` = #{userName}" +
                    "        </if>" +
                    "</script>"
    })
    List<TbUser> selectByDynamic(TbUser user);

    // insert batch
    @Insert({
            "<script>" +
                    " insert into `tb_user`(`user_id`,`user_name`) values" +
                    "        <foreach collection='list' separator=',' item='u'>" +
                    "            (#{u.userId},#{u.userName})" +
                    "        </foreach>" +
                    "</script>"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertUsers(List<TbUser> users);
}
