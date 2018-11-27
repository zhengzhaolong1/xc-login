package cn.axy.xc.xcloginprovider.cn.controller;

import cn.axy.xc.xcloginprovider.cn.sevice.WeiboService;
import cn.axy.xc.xcloginprovider.cn.weibo4j.model.WeiboException;
import cn.axy.xc.xcloginprovider.cn.weibo4j.org.json.JSONException;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;

//注意这里一个@RestController，一个@RequestMapping("/weibo")，不一样
//不要把路径写在@RestController后面

@Api(value="WbLoginController",tags = "微博登录")
@RestController
public class WbLoginController {
    @Autowired
    private WeiboService weiboService;


    @ApiOperation(value = "微博退出", notes = "微博退出")
    @RequestMapping(value = "/weibologout")
    public String out(){
        return JSON.toJSONString("退出成功");
    }


}
