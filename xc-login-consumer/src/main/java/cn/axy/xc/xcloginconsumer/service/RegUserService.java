package cn.axy.xc.xcloginconsumer.service;

import cn.axy.xc.xcloginconsumer.service.impl.RegUserFallback;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "xc-login-provider",fallback = RegUserFallback.class)
public interface RegUserService {

    //判定用户名是否正确
    @RequestMapping(value = "/vertifyname")
    public String vertifyName(@RequestParam("username") String username);


    @RequestMapping(value = "/vertifyPhone")
    public String vertifyPhone(@RequestParam("userphone")String userphone);


    @RequestMapping(value = "/sms")
    public String show(@RequestParam("userphone") String userphone);


    @RequestMapping(value = "/VertifyPassword")
    public String pwd(@RequestParam("userpassword")String userpassword);


    //注册成功，账号密码传后来保存到数据库
    @RequestMapping(value = "/reg")
    public String reg(@RequestParam("username")String username,
                      @RequestParam("userphone")String userphone,
                      @RequestParam("checkcode")String checkcode,
                      @RequestParam("userpassword")String userpassword);







}
