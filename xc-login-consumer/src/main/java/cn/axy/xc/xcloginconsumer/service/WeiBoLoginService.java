package cn.axy.xc.xcloginconsumer.service;

//import cn.axy.xc.xcloginconsumer.service.impl.WeiBoLoginFallback;
import cn.axy.xc.xcloginconsumer.service.impl.WeiBoLoginFallback;
import cn.axy.xc.xcloginconsumer.weibo4j.model.WeiboException;
import cn.axy.xc.xcloginconsumer.weibo4j.org.json.JSONException;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.io.IOException;

@FeignClient(value = "xc-login-provider",fallback = WeiBoLoginFallback.class)
public interface WeiBoLoginService {



    //微博登录接口
    @RequestMapping(value = "/weiboOAuth4Code")
    public void OAuth4Code()throws JSONException,WeiboException, IOException;


    //微博退出接口
    @RequestMapping(value = "/weibologout")
    public String out();


}
