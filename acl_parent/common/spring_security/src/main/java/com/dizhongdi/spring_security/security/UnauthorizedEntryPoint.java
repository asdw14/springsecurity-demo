package com.dizhongdi.spring_security.security;

import com.dizhongdi.servicebase.utils.utils.R;
import com.dizhongdi.servicebase.utils.utils.ResponseUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName:UnauthorizedEntryPoint
 * Package:com.dizhongdi.spring_security.security
 * Description:     未授权统一处理
 *
 * @Date: 2022/7/21 20:47
 * @Author:dizhongdi
 */

//未授权统一处理
@Component
public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        ResponseUtil.out(response, R.error());
    }
}
