package cn.axy.xc.xcloginconsumer.service;

import cn.axy.xc.xcloginconsumer.service.impl.RegUserFallback;
import cn.axy.xc.xcloginconsumer.service.impl.UpdUserFallback;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@FeignClient(value = "xc-login-provider",fallback = UpdUserFallback.class)
public interface UpdUserService {


    @RequestMapping(value = "/vertifyph")
    public String vertifyPhone(@RequestParam("userphone") String userphone);

    //这个是验证码图片的路径接口，点击验证码图片换图片从这个接口走
    @RequestMapping(value = "/defaultKaptcha")
    public void defaultKaptcha(@RequestParam("request") HttpServletRequest request, @RequestParam("response") HttpServletResponse response)
            throws Exception;



    @RequestMapping(value = "/vertifytupian")
    public String vrifyCode(@RequestParam("vrifyCode") String vrifyCode);




    //异步判定旧密码
    @RequestMapping(value = "/vertifypwd")
    public String reg(@RequestParam("userphone") String userphone,
                      @RequestParam("userpassword") String userpassword);


    //判定新密码格式
    @RequestMapping(value = "/vertifypwd2")
    public String pwd(@RequestParam("userpassword2") String userpassword2);

    //更新
    @RequestMapping(value = "updpwd")
    public String updpwd(@RequestParam("userphone") String userphone,
                         @RequestParam("vrifyCode")String vrifyCode,
                         @RequestParam("userpassword")String userpassword,
                         @RequestParam("userpassword2")String userpassword2);


}
