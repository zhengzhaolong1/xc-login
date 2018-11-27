package cn.axy.xc.xcloginconsumer.service;

import cn.axy.xc.xcloginconsumer.service.impl.DelUserFallback;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "xc-login-provider",fallback = DelUserFallback.class)
public interface DelUserService {

    @RequestMapping(value = "/del")
    public  String  del(@RequestParam("userphone") String userphone,
                        @RequestParam("password") String password);

}
