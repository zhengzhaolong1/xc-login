package cn.axy.xc.xcloginconsumer.service.impl;

import cn.axy.xc.xcloginconsumer.service.DelUserService;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

@Component
public class DelUserFallback  implements DelUserService {
    @Override
    public String del(String userphone, String password) {
        return JSON.toJSONString("服务不在线");
    }
}
