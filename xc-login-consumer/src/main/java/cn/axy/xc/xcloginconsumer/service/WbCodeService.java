package cn.axy.xc.xcloginconsumer.service;

import cn.axy.xc.xcloginconsumer.service.impl.WbCodeFallback;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@FeignClient(value = "xc-login-provider",fallback = WbCodeFallback.class)
public interface WbCodeService {

    @RequestMapping("/wwbb")
    public String wwbb();



}
