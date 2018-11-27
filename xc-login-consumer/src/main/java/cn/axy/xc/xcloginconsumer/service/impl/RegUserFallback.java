package cn.axy.xc.xcloginconsumer.service.impl;

import cn.axy.xc.xcloginconsumer.service.RegUserService;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

@Component
public class RegUserFallback implements RegUserService {
    @Override
    public String vertifyName(String username) {
        return JSON.toJSONString("服务不在线");
    }

    @Override
    public String vertifyPhone(String userphone) {
        return JSON.toJSONString("服务不在线");
    }

    @Override
    public String show(String userphone) {
        return JSON.toJSONString("服务不在线");
    }

    @Override
    public String pwd(String userpassword) {
        return JSON.toJSONString("服务不在线");
    }

    @Override
    public String reg(String username, String userphone, String checkcode, String userpassword) {
        return JSON.toJSONString("服务不在线");
    }
}
