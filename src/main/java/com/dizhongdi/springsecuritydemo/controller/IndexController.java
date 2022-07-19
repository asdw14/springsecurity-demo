package com.dizhongdi.springsecuritydemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        return "hello";
    }

    @GetMapping("findAll")
    @ResponseBody
    public String findAll(){
        return "findAll";
    }
}