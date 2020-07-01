#### MyBatis是什么
* MyBatis 是一款优秀的持久层框架，它支持自定义 SQL、存储过程以及高级映射。
* MyBatis 免除了几乎所有的 JDBC 代码以及设置参数和获取结果集的工作
* MyBatis 可以通过简单的 XML 或注解来配置和映射原始类型、接口和 Java POJO（Plain Old Java Objects，普通老式 Java 对象）为数据库中的记录。
* 以往听到的 SSM，其中的 M就是指的MyBatis,差不多是当前互联网公司中数据库持久层框架的首选

#### Hello World(Xml版)
* 测试SQL
```sql
CREATE DATABASE IF NOT EXISTS course DEFAULT CHARSET utf8mb4;

USE course;

SET NAMES utf8mb4;

-- tag信息表
CREATE TABLE IF NOT EXISTS `tb_user`
(
    `id`        BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `user_id`   VARCHAR(64)         NOT NULL DEFAULT '' COMMENT 'user id',
    `user_name` VARCHAR(128)        NOT NULL DEFAULT '' COMMENT '用户名称',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='用户表';

-- 测试数据
insert into tb_user(`user_id`,`user_name`) values ('1000001','zhangsan1'),
                                                  ('1000002','zhangsan2'),
                                                  ('1000003','zhangsan3');
```

* 引入依赖
```xml
 <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.5</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.14</version>
        </dependency>
```

* 添加mybatis总配置
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
<!--这个setting配置需要添加，否则对象返回null，设置驼峰自动转换-->
    <settings>
        <setting name="mapUnderscoreToCamelCase" value="true"/>
    </settings>

    <typeAliases>
        <package name="com.zhenL.bean"/>
    </typeAliases>

    <environments default="development">
        <!-- 可以有多个环境的配置，每个配置用id区分，使用时可以在此修改，或者代码中来指定 -->
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://127.0.0.1/mybatis?useUnicode=true&amp;characterEncoding=utf-8&amp;useSSL=false&amp;serverTimezone=UTC"/>
                <property name="username" value="root"/>
                <property name="password" value="123456"/>
            </dataSource>
        </environment>

    </environments>

    <mappers>
        <!-- 一般配置文件使用的方式 -->
        <mapper resource="mappers/TbUserMapper.xml"/>
  
    </mappers>
</configuration>
```

* 实体bean
```java
public class TbUser {
    private Long id;
    private String userId;
    private String userName;

    // setter & getter
}
```

* Mapper接口代码编写
```java
public interface TbUserMapper {

    List<TbUser> selectByUserId(String userId);
}
```

* 在resources目录下添加mappers/Mapper.xml
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--namespace 对应mapper类的全路径-->
<mapper namespace="com.zhenL.mapper.TbUserMapper">

    <select id="selectByUserId" resultType="com.zhenL.bean.TbUser">
        select * from `tb_user` where `user_id` = #{userId}
    </select>


</mapper>
```

* 测试类
```java
public class AppSelectByUserId {
    public static void main(String[] args) throws IOException {
        InputStream ins = Resources.getResource(MyBatisConst.CONFIG_FILE_NAME).openStream();
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(ins);
        SqlSession session = factory.openSession(true);
        TbUserMapper tbUserMapper = session.getMapper(TbUserMapper.class);
        List<TbUser> users = tbUserMapper.selectByUserId("1000003");
        users.forEach(u -> System.out.println("u = " + u ));

        session.close();
    }
}
```

#### Hello World之批量插入xml配置
```xml
 <!-- 批量插入 -->
    <insert id="insertUsers" parameterType="TbUser" useGeneratedKeys="true" keyProperty="id">
        insert into `tb_user`(`user_id`,`user_name`) values
        <foreach collection="list" separator="," item="u">
            (#{u.userId},#{u.userName})
        </foreach>
    </insert>
```

#### 总配置详解
* settings
```xml
<!--这个setting配置需要添加，否则对象返回null，设置驼峰自动转换-->
    <settings>
        <setting name="mapUnderscoreToCamelCase" value="true"/>
    </settings>
```
* typeAliases
```xml
 <typeAliases>
        <package name="com.zhenL.bean"/>
    </typeAliases>
```
* environments
```xml
<environments default="development">
        <!-- 可以有多个环境的配置，每个配置用id区分，使用时可以在此修改，或者代码中来指定 -->
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://127.0.0.1/mybatis?useUnicode=true&amp;characterEncoding=utf-8&amp;useSSL=false&amp;serverTimezone=UTC"/>
                <property name="username" value="root"/>
                <property name="password" value="123456"/>
            </dataSource>
        </environment>
        <environment id="test">
            <transactionManager type="JDBC"/>
            <dataSource type="course.mybatis1.datasource.LocalDataSourceFactory">
                <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://127.0.0.1/mybatis?useUnicode=true&amp;characterEncoding=utf-8&amp;useSSL=false&amp;serverTimezone=UTC"/>
                <property name="username" value="root"/>
                <property name="password" value="123456"/>
                <property name="maxActive" value="20"/>
            </dataSource>
        </environment>
    </environments>
```
* mappers
```xml
<mappers>
        <!-- 一般配置文件使用的方式 -->
        <mapper resource="mappers/TbUserMapper.xml"/>
        <!--        <package name="course.mybatis1.mapper"/>-->
    </mappers>
```
* properties
```xml
<!-- 放到配置的最前面，在后面引用时用${url}-->
<properties>
        <property name="url"
                  value="jdbc:mysql://49.233.204.164/mybatis?useUnicode=true&amp;characterEncoding=utf-8&amp;useSSL=false&amp;serverTimezone=UTC"/>
    </properties>
```

#### Mapper配置文件配置详解
* select
* insert
* update
* delete
* parameterType="xxx" 
* parameterMap="xxx" 
* resultType="xxx"
* resultMap="xxx"
* 参考: https://mybatis.org/mybatis-3/zh/sqlmap-xml.html

#### 动态SQL
* if结构, xml版 userId+userName
```xml
<select id="selectUsers" resultType="User">
    select * from `tb_user` where 1=1
    <if test="userId != null">
        AND `user_id` = #{userId}
    </if>

    <if test="userName != null">
        AND `user_name` = #{userName}
    </if>
</select>
```
