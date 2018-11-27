package cn.axy.xc.xcloginprovider.cn.controller;

import cn.axy.xc.xcloginprovider.cn.pojo.User;
import cn.axy.xc.xcloginprovider.cn.sevice.UpdPwdService;
import cn.axy.xc.xcloginprovider.cn.sevice.UserService;
import cn.axy.xc.xcloginprovider.cn.util.Encryption;
import cn.axy.xc.xcloginprovider.cn.util.VertifyPhone;
import cn.axy.xc.xcloginprovider.cn.util.VertifyPwd;
import com.alibaba.fastjson.JSON;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

@Api(value="UpdPwdController",tags = "登陆")
@RestController
public class UpdPwdController {

    @Autowired
    private UpdPwdService updPwdService;

    @Autowired
    private DefaultKaptcha defaultKaptcha;


    @Autowired
    private UserService userService;



    //判定手机号
    @ApiOperation(value = "判定手机号", notes = "判定手机号格式")
    @RequestMapping(value = "/vertifyph")
    public String vertifyPhone(@ApiParam(name = "userphone", value = "用户手机号", required = true) String userphone) {

        User user = userService.findByPhone(userphone);

        boolean flag = VertifyPhone.isMobile(userphone);
        if (flag == true && user != null)
            return JSON.toJSONString("");
        else
            return JSON.toJSONString("手机号输入有误或不存在");

    }


//    //这个是验证码图片的路径接口，点击验证码图片换图片从这个接口走
//    @ApiOperation(value = "生成图片校验码", notes = "生成图片校验码")
//    @RequestMapping(value = "/defaultKaptcha")
//    public void defaultKaptcha( HttpServletRequest request,  HttpServletResponse response)
//            throws Exception {
//        //声明一个数组
//        byte[] captchaChallengeAsJpeg = null;
//        //数组输出流
//        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
//        try {
//            //生成验证码字符串（text)并保存到session中,下面要往出拿
//            String createText = defaultKaptcha.createText();
//            //保存到session中
////            request.getSession().setAttribute("vrifyCode", createText);
//            //保存到redis中
//            Jedis jd = new Jedis("localhost",6379);
//            jd.set("vrifyCode",createText);
//            //使用生成的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
//            BufferedImage challenge = defaultKaptcha.createImage(createText);
//            //设置以"jpg"的格式
//            ImageIO.write(challenge, "jpg", jpegOutputStream);
//        } catch (IllegalArgumentException e) {
//            //SC_NOT_FOUND=404
//            response.sendError(HttpServletResponse.SC_NOT_FOUND);
//            return;
//        }
//
//        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
//        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
//        response.setHeader("Cache-Control", "no-store");
//        response.setHeader("Pragma", "no-cache");
//        response.setDateHeader("Expires", 0);
//        response.setContentType("image/jpeg");
//        ServletOutputStream responseOutputStream = response.getOutputStream();
//
//
//        responseOutputStream.write(captchaChallengeAsJpeg);
//        responseOutputStream.flush();
//        responseOutputStream.close();
//    }


    //判定图片校验码是否正确
    @ApiOperation(value = "判定图片校验码", notes = "判定图片校验码")
    @RequestMapping(value = "/vertifytupian")
    public String vrifyCode(@ApiParam(name = "vrifyCode", value = "图片校验码", required = true) String vrifyCode) {
        //这是放在了session中，因为跨域了，session作用域不够，放在redis中。
//        String serverCode = (String) request.getSession().getAttribute("vrifyCode");
        Jedis jd = new Jedis("localhost",6379);
        String serverCode =jd.get("vrifyCode");
        System.out.println(serverCode+"验证码");
        System.out.println(serverCode+"rrrrrr");
//        String vrifyCode = request.getParameter("vrifyCode");
        if (serverCode.equals(vrifyCode)) {
            //如果正确什么也不写，让它往下走就行
            return JSON.toJSONString("");
        } else {
            return JSON.toJSONString("验证码错误");
        }
    }


    @ApiOperation(value = "异步判定旧密码", notes = "异步判定旧密码")
    @RequestMapping(value = "/vertifypwd")
    public String reg(@ApiParam(name = "userphone", value = "用户手机号", required = true) String userphone,
                      @ApiParam(name = "userpassword", value = "密码", required = true) String userpassword) {
        if (userphone.length()>0) {
            //通过手机号找到密码
            String password = userService.findByPhone(userphone).getPassword();
            //对密码解密
            String s = Encryption.decryptBasedDes(password);

            if (s.equals(userpassword)) {
                return JSON.toJSONString("");
            } else {
                return JSON.toJSONString("密码输入有误");
            }
        }else{
            return JSON.toJSONString("密码输入有误");
        }
    }



    @ApiOperation(value = "判定新密码格式",notes = "判定新密码格式是否设置正确")
    @RequestMapping(value = "/vertifypwd2")
    public String pwd(@ApiParam(name = "userpassword2",value = "密码",required = true)String userpassword2){
        boolean b = VertifyPwd.validatePhonePass(userpassword2);
        if (b == true){
            return JSON.toJSONString("");
        } else {
            return JSON.toJSONString("密码格式设置有误");
        }
    }



    @ApiOperation(value = "修改密码",notes = "修改密码")
    @RequestMapping(value = "updpwd")
    public String updpwd(@ApiParam(name = "userphone",value = "手机号",required = true)String userphone,
                         @ApiParam(name = "vrifyCode",value = "验证码",required = true)String vrifyCode,
                         @ApiParam(name = "userpassword",value = "旧密码",required = true)String userpassword,
                         @ApiParam(name = "userpassword2",value = "新密码",required = true)String userpassword2){
        if(userphone!=null&&userpassword!= null&&userpassword2!=null) {
            Jedis jd = new Jedis("localhost",6379);
            String serverCode =jd.get("vrifyCode");
            String s = Encryption.decryptBasedDes(userService.findByPhone(userphone).getPassword());
            String password = userService.findByPhone(userphone).getPassword();

            if (s.equals(userpassword) && (vrifyCode.equals(serverCode)) && (userpassword2.length() > 0)
                    && (!userpassword.equals(userpassword2))) {
                updPwdService.updPwd(userphone, Encryption.encryptBasedDes(userpassword2));
                return JSON.toJSONString("200");
            } else {
                return JSON.toJSONString("输入有误");
            }
        }else {
            return JSON.toJSONString("输入有误");
        }
    }
}