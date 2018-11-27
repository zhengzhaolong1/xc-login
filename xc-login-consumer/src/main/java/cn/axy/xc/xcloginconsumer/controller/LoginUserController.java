package cn.axy.xc.xcloginconsumer.controller;

import cn.axy.xc.xcloginconsumer.service.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginUserController {

    @Autowired
    private LoginUserService loginUserService;


    //根据User对象创建用户
    @RequestMapping(value = "/login")
    public String login( String username, String password){
        return loginUserService.login(username,password);
    }



    //用户退出
    @RequestMapping(value = "/logout")
    public String ss(){
        return loginUserService.ss();
    }
}
