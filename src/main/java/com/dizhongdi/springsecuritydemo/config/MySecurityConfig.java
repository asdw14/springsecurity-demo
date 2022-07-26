package com.dizhongdi.springsecuritydemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

/**
 * ClassName:SecurityConfig
 * Package:com.dizhongdi.springsecuritydemo.config
 * Description:
 *
 * @Date: 2022/7/19 22:11
 * @Author:dizhongdi
 */
@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    private PersistentTokenRepository tokenRepository;

    // 注入 PasswordEncoder 类到 spring 容器中
    @Bean
    public PasswordEncoder password(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(password());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling().accessDeniedPage("/unauth");
        //退出
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/index").permitAll();
        // 开启记住我功能
        http.rememberMe()
                .tokenRepository(tokenRepository)
                .userDetailsService(userDetailsService)
                .tokenValiditySeconds(600);    //有效期单位秒

        // 配置认证
        http.formLogin()
                .loginPage("/index") // 配置哪个 url 为登录页面
                .loginProcessingUrl("/login") // 设置哪个是登录的 url。
                .successForwardUrl("/success") // 登录成功之后跳转到哪个 url
                .failureForwardUrl("/fail");// 登录失败之后跳转到哪个 url
        http.authorizeRequests()
                .antMatchers("/login.html","/index") //表示配置请求路径
                .permitAll() // 指定 URL 无需保护。
                //.antMatchers("/findAll").hasAuthority("admin")  //需要有admin权限
                //.antMatchers("/find").hasAnyAuthority("role")//需要有role权限
                .antMatchers("/find").hasRole("admin")  //需要主体有admin角色
                .antMatchers("/findAll").hasAnyRole("role","role1")  //需要主体带有role或者role1角色
                .anyRequest() // 其他请求
                .authenticated(); //需要认证
// 关闭 csrf
//        http.csrf().disable();
    }
}
