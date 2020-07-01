package com.zhenL.bean;

import lombok.*;

/**
 * @author zhenL
 * @description
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TbUser {
    private Long id;
    private String userId;
    private String userName;
    public TbUser(String userId, String userName){
        this.userId = userId;
        this.userName = userName;
    }
}
