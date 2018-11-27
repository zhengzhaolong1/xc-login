package cn.axy.xc.xcloginprovider.cn.controller;

import cn.axy.xc.xcloginprovider.cn.SMS.IndustrySMS;
import cn.axy.xc.xcloginprovider.cn.pojo.User;
import cn.axy.xc.xcloginprovider.cn.sevice.RegService;
import cn.axy.xc.xcloginprovider.cn.sevice.UserService;
import cn.axy.xc.xcloginprovider.cn.util.Encryption;
import cn.axy.xc.xcloginprovider.cn.util.VertifyName;
import cn.axy.xc.xcloginprovider.cn.util.VertifyPhone;
import cn.axy.xc.xcloginprovider.cn.util.VertifyPwd;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonAnyFormatVisitor;
import com.netflix.discovery.converters.Auto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;

@Api(value = "RegController",tags = "用户注册")
@RestController
public class RegController {

    @Autowired
    private RegService regService;
    @Autowired
    private UserService userService;



    //判定用户名是否正确
    @ApiOperation(value = "判定用户名",notes = "判定用户名是否正确")
    @RequestMapping(value = "/vertifyname")
    public String vertifyName(@ApiParam(name = "username",value = "用户名",required = true) String username){
        User byUsername = userService.findByUsername(username);
        boolean flag = VertifyName.show(username);
        if (flag == false && byUsername == null){
            return JSON.toJSONString("");
        }else {
            return JSON.toJSONString("用户名输入有误或已注册");
        }
    }


    //判定手机号是否正确
    @ApiOperation(value = "判定手机号",notes = "判定手机号是否正确")
    @RequestMapping(value = "/vertifyPhone")
    public String vertifyPhone(@ApiParam(name = "userphone",value = "手机号",required = true)String userphone){
        User user = userService.findByPhone(userphone);
        boolean flag = VertifyPhone.isMobile(userphone);
        if (flag == true&&user == null)
            return JSON.toJSONString("");
        else
            return JSON.toJSONString("手机号输入有误或已注册");

    }

    //通过手机号的到短信验证码
    @ApiOperation(value = "传入手机号",notes = "传入手机号，收到验证码")
    @RequestMapping(value = "/sms")
    public String show(@ApiParam(name = "userphone",value = "手机号",required = true)String userphone){
        //判定手机号
        boolean mobile = VertifyPhone.isMobile(userphone);
        if (mobile ==true){
            String checkcode = IndustrySMS.execute(userphone);
            if (checkcode != null){
                Jedis jd = new Jedis("localhost",6379);
                jd.flushAll();
                //把重新得到的验证码和手机号放到redis中,顺便设置验证码的过期时间
                jd.setex("checkcode",3*60,checkcode);
                jd.set("userphone",userphone);
                return JSON.toJSONString("发送成功");
            }else {
                return JSON.toJSONString("发送失败");
            }
        }else {
            return JSON.toJSONString("手机号输入有误");
        }


    }



    //判定密码格式是否正确
    @ApiOperation(value = "判定密码",notes = "判定密码格式是否设置正确")
    @RequestMapping(value = "/VertifyPassword")
    public String pwd(@ApiParam(name = "userpassword",value = "密码",required = true)String userpassword){
        boolean b = VertifyPwd.validatePhonePass(userpassword);
        if (b == true){
            return JSON.toJSONString("");
        } else {
            return JSON.toJSONString("密码设置有误");
        }
    }

    //注册成功，账号密码传后来保存到数据库
    @ApiOperation(value = "注册",notes = "把账号，手机号，密码，验证码传后来注册")
    @RequestMapping(value = "/reg")
    public String reg(@ApiParam(name = "username",value = "用户名",required = true)String username,
                      @ApiParam(name = "userphone",value = "手机号",required = true)String userphone,
                      @ApiParam(name = "checkcode",value = "验证码",required = true)String checkcode,
                      @ApiParam(name = "userpassword",value = "用户密码",required = true)String userpassword){
        Boolean show = VertifyName.show(username);
        boolean flag = VertifyPhone.isMobile(userphone);
        boolean b = VertifyPwd.validatePhonePass(userpassword);

        Jedis jd = new Jedis("localhost",6379);
        String checkcode1 = jd.get("checkcode");
        String userphone1 = jd.get("userphone");

//        System.out.println(checkcode1+"checkcode1");
//        System.out.println(userphone1+"userphone1");
//        System.out.println(username+"账户名");
//        System.out.println(userphone+"手机号");
//        System.out.println(userpassword+"密码");
//        System.out.println(show+"判定是不是纯数字");
//        System.out.println(flag+"判定手机号正确不正确");
//        System.out.println(b+"判定密码格式");
        if (flag == true && b == true && show ==false &&
                userphone.equals(userphone1)&&checkcode.equals(checkcode1)){
            //把账号，密码，手机号插入
            //把加密后的密码插入
            regService.reg(username,userphone, Encryption.encryptBasedDes(userpassword) );
            return JSON.toJSONString("200");
        }else {
            return JSON.toJSONString("500");
        }

    }
}
