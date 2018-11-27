package cn.axy.xc.xcloginconsumer.controller;

import cn.axy.xc.xcloginconsumer.service.ForgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForgetController {


    @Autowired
    private ForgetService forgetService;


    //判定手机号是否正确
    @RequestMapping(value = "/verifyPh")
    public String vertifyPhone(String userphone){
        return forgetService.vertifyPhone(userphone);
    }


    //传入手机号，收到验证码")
    @RequestMapping(value = "/smt")
    public String sh(@RequestParam("userphone")String userphone){
        return forgetService.sh(userphone);
    }

    //判定验证码是否正确
    @RequestMapping("/verifyCkeck")
    public String verifyCheck( String check){
        return forgetService.verifyCheck(check);
    }


    //把新密码更新到数据库
    @RequestMapping("/updPa")
    public String updPa(String userphone, String check, String userpassword){
        return forgetService.updPa(userphone,check,userpassword);
    }
}
