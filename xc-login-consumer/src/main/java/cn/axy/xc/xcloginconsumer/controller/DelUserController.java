package cn.axy.xc.xcloginconsumer.controller;

import cn.axy.xc.xcloginconsumer.service.DelUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DelUserController {

    @Autowired
    private DelUserService delUserService;

    @RequestMapping(value = "/del")
    public  String  del( String userphone, String password){
        return delUserService.del(userphone,password);
    }
}
