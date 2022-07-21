package com.dizhongdi.spring_security.filter;

import com.dizhongdi.spring_security.entiy.SecutityUser;
import com.dizhongdi.spring_security.entiy.User;
import com.dizhongdi.spring_security.security.TokenManager;
import com.dizhongdi.servicebase.utils.utils.R;
import com.dizhongdi.servicebase.utils.utils.ResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * ClassName:TokenLoginFilter
 * Package:com.dizhongdi.spring_security.filter
 * Description:
 *      认证的 filter
 * @Date: 2022/7/21 20:59
 * @Author:dizhongdi
 */
public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private TokenManager tokenManager;
    private RedisTemplate redisTemplate;

    public TokenLoginFilter(AuthenticationManager authenticationManager, TokenManager tokenManager, RedisTemplate redisTemplate) {
        this.authenticationManager = authenticationManager;
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
        this.setPostOnly(false);
        this.setRequiresAuthenticationRequestMatcher(new
                AntPathRequestMatcher("/admin/acl/login","POST"));
    }

    //获取用户登录用户名密码
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            return authenticationManager.authenticate(new
                    UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), new
                    ArrayList<>()));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    //登录成功调用的
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        SecutityUser secutityUser = (SecutityUser) authResult.getPrincipal();
        String token = tokenManager.createToken(secutityUser.getUsername());
        redisTemplate.opsForValue().set(secutityUser.getUsername(),secutityUser.getAuthorities());
        ResponseUtil.out(response, R.ok().data("token",token));
    }

    //登录失败调用的
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        ResponseUtil.out(response, R.error());
    }
}
