package cn.axy.xc.xcloginprovider.cn.controller;

import cn.axy.xc.xcloginprovider.cn.pojo.User;
import cn.axy.xc.xcloginprovider.cn.sevice.UserService;
import cn.axy.xc.xcloginprovider.cn.util.Encryption;
import cn.axy.xc.xcloginprovider.cn.util.VertifyName;
import cn.axy.xc.xcloginprovider.cn.util.VertifyPhone;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Api(value="UserController",tags = "登陆")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    //判定手机号和密码
    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(@ApiParam(name = "username",value = "用户",required=true)String username,
                        @ApiParam(name = "password",value = "密码",required=true)String password) {
        //1.通过工具类获取subject
        Subject subject = SecurityUtils.getSubject();
        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        //3.执行登录方法
        try {
            User u = new User();
            //判断输入了手机号还是账号
            boolean mobile = VertifyPhone.isMobile(username);
            Boolean show = VertifyName.show(username);
            //如果不是手机号
            if (show == true) {
                    u.setUserPhone(username);
            } else {
                u.setUsername(username);
            }
            User user = userService.fingBy(u);
            subject.login(token);
//            && Encryption.decryptBasedDes(user.getPassword()) != password
            if (Encryption.decryptBasedDes(user.getPassword()) != null) {
                Integer uid = user.getUid();
                String s = String.valueOf(uid);
                Map<String,String> map = new HashMap<>();
                map.put("code","200");
                map.put("id",s);
                return JSON.toJSONString(map);
            }
            //账户错误
        } catch (UnknownAccountException e) {
            return JSON.toJSONString("400");
            //密码错误
        } catch (IncorrectCredentialsException e) {
            return JSON.toJSONString("500");
        }catch (Exception e){
            return JSON.toJSONString("100");
        }

        return JSON.toJSONString("500");
    }




    //退出
    @ApiOperation(value = "用户退出", notes = "用户退出")
    @RequestMapping(value = "/logout")
    public String ss(){
        return JSON.toJSONString("200");
    }









}
