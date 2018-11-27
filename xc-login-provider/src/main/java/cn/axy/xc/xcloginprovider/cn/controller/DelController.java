package cn.axy.xc.xcloginprovider.cn.controller;

import cn.axy.xc.xcloginprovider.cn.pojo.User;
import cn.axy.xc.xcloginprovider.cn.sevice.DelService;
import cn.axy.xc.xcloginprovider.cn.sevice.UserService;
import cn.axy.xc.xcloginprovider.cn.util.Encryption;
import com.alibaba.fastjson.JSON;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@Api(value = "DelController",tags = "用户注销")
@RestController
public class DelController {
    @Autowired
    private DelService delService;

    @Autowired
    private UserService userService;



    @ApiOperation(value = "注销", notes = "根据手机号和密码注销")
    @RequestMapping(value = "/del")
    public  String  del(@ApiParam(name = "userphone",value = "手机号",required = true) String userphone,
                        @ApiParam(name = "password",value = "密码",required = true)String password){
        User user = userService.findByPhone(userphone);



        if (user != null){
            if (Encryption.encryptBasedDes(password).equals(user.getPassword())&&
                (password.length()>0)){
                delService.del(userphone,Encryption.encryptBasedDes(password));
                return JSON.toJSONString("200");
            }
        }else {
             return JSON.toJSONString("500");
        }

         return JSON.toJSONString("500");
    }
}
