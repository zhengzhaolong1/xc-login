package cn.axy.xc.xcloginconsumer.service.impl;

import cn.axy.xc.xcloginconsumer.service.WeiBoLoginService;
import cn.axy.xc.xcloginconsumer.weibo4j.model.WeiboException;
import cn.axy.xc.xcloginconsumer.weibo4j.org.json.JSONException;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class WeiBoLoginFallback implements WeiBoLoginService {


    @Override
    public void OAuth4Code() throws JSONException, WeiboException, IOException {

    }

    @Override
    public String out() {

        return JSON.toJSONString("服务不在线");
    }
}
