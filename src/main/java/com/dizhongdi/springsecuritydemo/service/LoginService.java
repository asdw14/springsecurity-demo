package com.dizhongdi.springsecuritydemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * ClassName:LoginService
 * Package:com.dizhongdi.springsecuritydemo.service
 * Description:
 *
 * @Date: 2022/7/19 22:31
 * @Author:dizhongdi
 */
//@Service
public class LoginService implements UserDetailsService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!"dizhongdi".equals(username)){
            throw new UsernameNotFoundException("用户名不存在");
        }

        // 第三个参数表示权限
        return new User(username, passwordEncoder.encode("dizhongdi"),
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
