package cn.axy.xc.xcloginprovider.cn.controller;

import cn.axy.xc.xcloginprovider.cn.SMS.IndustrySMS;
import cn.axy.xc.xcloginprovider.cn.pojo.User;
import cn.axy.xc.xcloginprovider.cn.sevice.ForgetPwdService;
import cn.axy.xc.xcloginprovider.cn.sevice.UserService;
import cn.axy.xc.xcloginprovider.cn.util.VertifyPhone;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

@Api(value = "ForgetController",tags = "忘记密码")
@RestController
public class ForgetController {

    @Autowired
    private ForgetPwdService forgetPwdService;


    //判定手机号是否正确
    @ApiOperation(value = "判定手机号是否存在和格式",notes = "判定手机号是否正确")
    @RequestMapping(value = "/verifyPh")
    public String vertifyPhone(@ApiParam(name = "userphone",value = "手机号",required = true)String userphone){
        String s = forgetPwdService.updPwdByPhone(userphone);
      return JSON.toJSONString(s);
    }

    //通过手机号的到短信验证码
    @ApiOperation(value = "传入手机号",notes = "传入手机号，收到验证码")
    @RequestMapping(value = "/smt")
    public String sh(@ApiParam(name = "userphone",value = "手机号",required = true)String userphone){
        //判定手机号
        boolean mobile = VertifyPhone.isMobile(userphone);
        if (mobile ==true){
            String check = IndustrySMS.execute(userphone);
            if (check != null){
                Jedis jd = new Jedis("localhost",6379);
                //把重新得到的验证码和手机号放到redis中,顺便设置验证码的过期时间
                jd.setex("check",60,check);
                jd.set("userphone",userphone);
                return JSON.toJSONString("发送成功");
            }else {
                return JSON.toJSONString("发送失败");
            }
        }else {
            return JSON.toJSONString("手机号输入有误");
        }


    }



    //判定短信验证码
    @ApiOperation(value = "判定验证码是否正确",notes = "判定验证码是否正确")
    @RequestMapping("/verifyCkeck")
    public String verifyCheck(@ApiParam(name = "check",value = "验证码",required = true)String check){
        String s = forgetPwdService.verifyCheck(check);
        return JSON.toJSONString(s);
    }


    //把新密码更新到数据库
    @ApiOperation(value = "把新密码更新到数据库",notes = "把新密码更新到数据库")
    @RequestMapping("/updPa")
    public String updPa(@ApiParam(name = "userphone",value = "手机号",required = true)String userphone,
                        @ApiParam(name = "check",value = "验证码",required = true)String check,
                        @ApiParam(name = "userpassword",value = "密码",required = true)String userpassword){
        String s = forgetPwdService.updPassword(userphone, check, userpassword);
        return JSON.toJSONString(s);
    }



}
