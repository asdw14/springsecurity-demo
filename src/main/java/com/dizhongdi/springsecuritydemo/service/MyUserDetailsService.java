package com.dizhongdi.springsecuritydemo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dizhongdi.springsecuritydemo.enity.Users;
import com.dizhongdi.springsecuritydemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName:MyUserDetailsService
 * Package:com.dizhongdi.springsecuritydemo.service
 * Description:
 *
 * @Date: 2022/7/19 22:52
 * @Author:dizhongdi
 */
@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userMapper.selectOne(new QueryWrapper<Users>().eq("username", username));
        if (users==null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        System.out.println(users);
        List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList("admin,role,ROLE_admin,ROLE_role1");
        return new User(username, new BCryptPasswordEncoder().encode(users.getPassword()),auths);
    }
}
