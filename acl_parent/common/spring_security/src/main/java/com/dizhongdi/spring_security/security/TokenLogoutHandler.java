package com.dizhongdi.spring_security.security;

import com.dizhongdi.servicebase.utils.utils.R;
import com.dizhongdi.servicebase.utils.utils.ResponseUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName:TokenLogoutHandler
 * Package:com.dizhongdi.spring_security.security
 * Description:
 *      退出实现
 * @Date: 2022/7/21 20:41
 * @Author:dizhongdi
 */
@Component
public class TokenLogoutHandler implements LogoutHandler {
    private RedisTemplate redisTemplate;
    private TokenManager tokenManager;

    public TokenLogoutHandler(RedisTemplate redisTemplate, TokenManager tokenManager) {
        this.redisTemplate = redisTemplate;
        this.tokenManager = tokenManager;
    }


    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String token = request.getHeader("token");
        if (token!=null){
            tokenManager.removeToken(token);
            //清空当前用户缓存中的权限数据
            String userName = tokenManager.getUserFromToken(token);
            redisTemplate.delete(userName);
        }
        ResponseUtil.out(response, R.ok());
    }
}
