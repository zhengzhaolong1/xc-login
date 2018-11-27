package cn.axy.xc.xcloginconsumer.service;

import cn.axy.xc.xcloginconsumer.service.impl.LoginUserFallback;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "xc-login-provider",fallback = LoginUserFallback.class)
public interface LoginUserService {

   //根据User对象创建用户
    @RequestMapping(value = "/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password")String password);


    //用户退出
    @RequestMapping(value = "/logout")
    public String ss();












}
