package cn.axy.xc.xcloginconsumer.controller;

import cn.axy.xc.xcloginconsumer.service.RegUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegUserController {


    @Autowired
    private RegUserService regUserService;

    //判定用户名是否正确
    @RequestMapping(value = "/vertifyname")
    public String vertifyName( String username){
        return regUserService.vertifyName(username);
    }

    @RequestMapping(value = "/vertifyPhone")
    public String vertifyPhone(String userphone){
        return regUserService.vertifyPhone(userphone);
    }

    @RequestMapping(value = "/sms")
    public String show(@RequestParam("userphone") String userphone){
        return regUserService.show(userphone);
    }


    @RequestMapping(value = "/VertifyPassword")
    public String pwd(String userpassword){
        return  regUserService.pwd(userpassword);
    }

    @RequestMapping(value = "/reg")
    public String reg(String username, String userphone, String checkcode, String userpassword){
        return regUserService.reg(username,userphone,checkcode,userpassword);
    }







}
