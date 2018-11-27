package cn.axy.xc.xcloginconsumer.service.impl;

import cn.axy.xc.xcloginconsumer.service.UpdUserService;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UpdUserFallback implements UpdUserService {
    @Override
    public String vertifyPhone(String userphone) {
        return "服务不在线";
    }
//
//    @Override
//    public String vrifyCode(HttpServletRequest request, HttpServletResponse response) {
//        return "服务不在线";
//    }

    @Override
    public String reg(String userphone, String userpassword) {
        return JSON.toJSONString("服务不在线");
    }

    @Override
    public String pwd(String userpassword2) {
        return JSON.toJSONString("服务不在线");
    }

    @Override
    public String updpwd(String userphone, String vrifyCode, String userpassword, String userpassword2) {
        return JSON.toJSONString("服务不在线");
    }

    @Override
    public void defaultKaptcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("服务不在线");
    }

    @Override
    public String vrifyCode(String vrifyCode) {
        return JSON.toJSONString("服务不在线");
    }
}
