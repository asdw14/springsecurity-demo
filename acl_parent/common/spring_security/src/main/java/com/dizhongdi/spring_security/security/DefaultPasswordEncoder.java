package com.dizhongdi.spring_security.security;

import com.dizhongdi.servicebase.utils.utils.MD5;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * ClassName:DefaultPasswordEncoder
 * Package:com.dizhongdi.spring_security.security
 * Description:
 *      密码处理的方法
 * @Date: 2022/7/21 20:25
 * @Author:dizhongdi
 */
@Component
public class DefaultPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return MD5.encrypt(charSequence.toString());
    }

    public DefaultPasswordEncoder() {
        this(-1);
    }

    public DefaultPasswordEncoder(int strength) {

    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(MD5.encrypt(charSequence.toString()));
    }
}
