package cn.axy.xc.xcloginconsumer.service.impl;

import cn.axy.xc.xcloginconsumer.service.WbCodeService;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class WbCodeFallback implements WbCodeService {


    @Override
    public String wwbb() {
        return JSON.toJSONString("服务不在线");
    }


}
