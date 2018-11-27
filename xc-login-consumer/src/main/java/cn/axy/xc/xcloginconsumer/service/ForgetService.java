package cn.axy.xc.xcloginconsumer.service;

import cn.axy.xc.xcloginconsumer.service.impl.ForgetFallback;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "xc-login-provider",fallback = ForgetFallback.class)
public interface ForgetService {

    //判定手机号是否正确
    @RequestMapping(value = "/verifyPh")
    public String vertifyPhone(@RequestParam("userphone") String userphone);


    //判定验证码是否正确
    @RequestMapping("/verifyCkeck")
    public String verifyCheck(@RequestParam("check") String check);


    //把新密码更新到数据库
    @RequestMapping("/updPa")
    public String updPa(@RequestParam("userphone")String userphone,
                        @RequestParam("check")String check,
                        @RequestParam("userpassword")String userpassword);



   //传入手机号，收到验证码")
    @RequestMapping(value = "/smt")
    public String sh(@RequestParam("userphone")String userphone);
}
