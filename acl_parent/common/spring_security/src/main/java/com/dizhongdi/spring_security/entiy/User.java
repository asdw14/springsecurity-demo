package com.dizhongdi.spring_security.entiy;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ClassName:User
 * Package:com.dizhongdi.spring_security.entiy
 * Description:
 *
 * @Date: 2022/7/21 20:50
 * @Author:dizhongdi
 */
@ApiModel(description = "用户实体类")
public class User {
    private String username;
    private String password;
    private String nickName;
    private String salt;
    private String token;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}