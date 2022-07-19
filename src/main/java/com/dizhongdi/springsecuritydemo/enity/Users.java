package com.dizhongdi.springsecuritydemo.enity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ClassName:Users
 * Package:com.dizhongdi.springsecuritydemo.enity
 * Description:
 *
 * @Date: 2022/7/19 22:48
 * @Author:dizhongdi
 */
@Data
@AllArgsConstructor
public class Users {
    private Integer id;
    private String username;
    private String password;
}