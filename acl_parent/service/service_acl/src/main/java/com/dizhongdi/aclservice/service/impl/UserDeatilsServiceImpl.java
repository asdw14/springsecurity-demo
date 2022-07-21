package com.dizhongdi.aclservice.service.impl;

import com.dizhongdi.aclservice.entity.User;
import com.dizhongdi.aclservice.service.PermissionService;
import com.dizhongdi.aclservice.service.UserService;
import com.dizhongdi.spring_security.entiy.SecutityUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName:UserDeatilsServiceImpl
 * Package:com.dizhongdi.aclservice.service.impl
 * Description:
 *
 * @Date: 2022/7/21 22:03
 * @Author:dizhongdi
 */
@Service("userDetailsService")
public class UserDeatilsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    PermissionService permissionService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.selectByUsername(username);
        if (user==null){
            throw new UsernameNotFoundException("用户不存在");
        }

        com.dizhongdi.spring_security.entiy.User curuser = new com.dizhongdi.spring_security.entiy.User();
        BeanUtils.copyProperties(user,curuser);
        SecutityUser secutityUser = new SecutityUser(curuser);
        List<String> permissionValueByUserId = permissionService.selectPermissionValueByUserId(user.getId());
        secutityUser.setPermissionValueList(permissionValueByUserId);
        return secutityUser;
    }
}
