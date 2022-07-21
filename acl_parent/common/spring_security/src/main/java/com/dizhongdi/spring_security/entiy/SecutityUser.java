package com.dizhongdi.spring_security.entiy;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * ClassName:SecutityUser
 * Package:com.dizhongdi.spring_security.entiy
 * Description:
 *
 * @Date: 2022/7/21 20:49
 * @Author:dizhongdi
 */
@Data
public class SecutityUser implements UserDetails {
    //当前登录用户
    private transient User currentUserInfo;
    //当前权限
    private List<String> permissionValueList;

    public User getCurrentUserInfo() {
        return currentUserInfo;
    }

    public void setCurrentUserInfo(User currentUserInfo) {
        this.currentUserInfo = currentUserInfo;
    }

    public List<String> getPermissionValueList() {
        return permissionValueList;
    }

    public void setPermissionValueList(List<String> permissionValueList) {
        this.permissionValueList = permissionValueList;
    }

    public SecutityUser() {
    }

    public SecutityUser(User user) {
        if (user!=null)
        this.currentUserInfo = user;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for (String permission : permissionValueList) {
            if (!StringUtils.isEmpty(permission)){
                GrantedAuthority authority = new SimpleGrantedAuthority(permission);
                authorities.add(authority);
            }
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return currentUserInfo.getPassword();
    }

    @Override
    public String getUsername() {
        return currentUserInfo.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
