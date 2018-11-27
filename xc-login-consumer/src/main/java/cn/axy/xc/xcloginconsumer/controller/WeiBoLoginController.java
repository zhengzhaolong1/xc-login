package cn.axy.xc.xcloginconsumer.controller;


import cn.axy.xc.xcloginconsumer.nn.Wb;
import cn.axy.xc.xcloginconsumer.service.WeiBoLoginService;
import cn.axy.xc.xcloginconsumer.weibo4j.model.WeiboException;
import cn.axy.xc.xcloginconsumer.weibo4j.org.json.JSONException;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class WeiBoLoginController {

    @Autowired
    private WeiBoLoginService weiBoLoginService;

    @Autowired
    private Wb wb;

    @RequestMapping(value = "/weiboOAuth4Code")
    public void OAuth4Code()throws JSONException,WeiboException, IOException{

        wb.startWb();
    }

    @RequestMapping(value = "/weibologout")
    public String out(){
        return JSON.toJSONString("200");
    }

}
