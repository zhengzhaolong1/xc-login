package cn.axy.xc.xcloginconsumer.service.impl;

import cn.axy.xc.xcloginconsumer.service.ForgetService;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

@Component
public class ForgetFallback implements ForgetService {


    @Override
    public String vertifyPhone(String userphone) {
        return JSON.toJSONString("服务不在线");
    }

    @Override
    public String verifyCheck(String check) {
        return JSON.toJSONString("服务不在线");
    }

    @Override
    public String updPa(String userphone, String check, String userpassword) {
        return JSON.toJSONString("服务不在线");
    }

    @Override
    public String sh(String userphone) {
        return JSON.toJSONString("服务不在线");
    }
}
