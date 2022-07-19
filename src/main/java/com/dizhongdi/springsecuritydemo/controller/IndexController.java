package com.dizhongdi.springsecuritydemo.controller;

import com.dizhongdi.springsecuritydemo.enity.Users;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:IndexController
 * Package:com.dizhongdi.springsecuritydemo.controller
 * Description:
 *
 * @Date: 2022/7/19 22:18
 * @Author:dizhongdi
 */
@Controller
    public class IndexController {
//    @GetMapping("index")
//    @ResponseBody
//    public String index(){
//        return "success";
//    }
    @GetMapping("index")
    public String index(){
        return "login";
    }

    @PostMapping("/success")
    @ResponseBody
    public String success(){
        return "登录成功";
    }

    // 测试注解：
    @RequestMapping("testSecured")
    @ResponseBody
    @Secured({"ROLE_normal","ROLE_admin"})
    public String helloUser() {
        return "hello,user";
    }

    //在方法执行前进行权限验证
    @RequestMapping("/preAuthorize")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_role1')")
    //@PreAuthorize("hasAnyAuthority('admin')")
    public String preAuthorize(){
        System.out.println("preAuthorize");
        return "preAuthorize";
    }

    //在方法执行后再进行权限验证
    @RequestMapping("/testPostAuthorize")
    @ResponseBody
    @PostAuthorize("hasAnyAuthority('admin1')")
    public String preAuthorize2(){
        System.out.println("test--PostAuthorize");
        return "PostAuthorize";
    }

    //权限验证之后对数据进行过滤
    @RequestMapping("getAll")
    @PreAuthorize("hasRole('ROLE_admin')")
    @PostFilter("filterObject.username == 'admin1'")
    @ResponseBody
    public List<Users> getAllUser(){
        ArrayList<Users> list = new ArrayList<>();
        list.add(new Users(1,"admin1","6666"));
        list.add(new Users(2,"admin2","888"));
        return list;
    }

    //@PreFilter: 进入控制器之前对数据进行过滤
    @RequestMapping("getTestPreFilter")
    @PreAuthorize("hasRole('ROLE_admin')")
    @PreFilter(value = "filterObject.id%2==0")
    @ResponseBody
    public List<Users> getTestPreFilter(@RequestBody List<Users>
                                                   list){
        list.forEach(t-> {
            System.out.println(t.getId()+"\t"+t.getUsername());
        });
        return list;
    }

    @GetMapping("findAll")
    @ResponseBody
    public String findAll(){
        return "findAll";
    }

    @GetMapping("/unauth")
    @ResponseBody
    public String accessDenyPage(){
        return "无权访问"; }

    @GetMapping("/find")
    @ResponseBody
    public String find(){
        return "find"; }
}