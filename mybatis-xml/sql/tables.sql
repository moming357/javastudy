CREATE DATABASE IF NOT EXISTS mybatis DEFAULT CHARSET utf8mb4;

USE mybatis;

SET NAMES utf8mb4;

-- tb user
CREATE TABLE IF NOT EXISTS `tb_user`
(
    `id`        BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `user_id`   VARCHAR(64)         NOT NULL DEFAULT '' COMMENT 'user id',
    `user_name` VARCHAR(128)        NOT NULL DEFAULT '' COMMENT '用户名称',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户表';

insert into tb_user(`user_id`, `user_name`)
values ('1000001', 'zhangsan1'),
       ('1000002', 'zhangsan2'),
       ('1000003', 'zhangsan3');

-- tb user
CREATE TABLE IF NOT EXISTS `user`
(
    `id`  BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `uid` VARCHAR(64)         NOT NULL DEFAULT '' COMMENT 'user id',
    `una` VARCHAR(128)        NOT NULL DEFAULT '' COMMENT '用户名称',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户表';

insert into user(`uid`, `una`)
values ('1000001', 'zhangsan1'),
       ('1000002', 'zhangsan2'),
       ('1000003', 'zhangsan3');
