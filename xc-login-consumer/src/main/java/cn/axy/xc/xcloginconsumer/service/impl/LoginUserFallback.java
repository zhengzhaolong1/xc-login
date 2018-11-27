package cn.axy.xc.xcloginconsumer.service.impl;

import cn.axy.xc.xcloginconsumer.service.LoginUserService;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

@Component
public class LoginUserFallback implements LoginUserService {

    @Override
    public String login(String username, String password) {
        return JSON.toJSONString("服务不在线");
    }

    @Override
    public String ss() {
        return JSON.toJSONString("服务不在线");
    }
}
