package cn.axy.xc.xcloginconsumer.controller;

import cn.axy.xc.xcloginconsumer.service.UpdUserService;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

@RestController
public class UpdUserController {

    @Autowired
    private UpdUserService updUserService;

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @RequestMapping(value = "/vertifyph")
    public String vertifyPhone( String userphone){
        return updUserService.vertifyPhone(userphone);
    }


    //这个是验证码图片的路径接口，点击验证码图片换图片从这个接口走
    @ApiOperation(value = "生成图片校验码", notes = "生成图片校验码")
    @RequestMapping(value = "/defaultKaptcha")
    public void defaultKaptcha( HttpServletRequest request,  HttpServletResponse response)
            throws Exception {
        //声明一个数组
        byte[] captchaChallengeAsJpeg = null;
        //数组输出流
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生成验证码字符串（text)并保存到session中,下面要往出拿
            String createText = defaultKaptcha.createText();
            //保存到session中 现在是两个项目，session不能跨，所以放在redis中
//            request.getSession().setAttribute("vrifyCode", createText);
            //保存到redis中
            Jedis jd = new Jedis("localhost",6379);
            jd.set("vrifyCode",createText);
            //使用生成的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            //设置以"jpg"的格式
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            //SC_NOT_FOUND=404
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = response.getOutputStream();


        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

    //因为是两个项目跨域了，所以不能用ruquest和response。
    @RequestMapping(value = "/vertifytupian")
    public String vrifyCode( String vrifyCode){
        return updUserService.vrifyCode(vrifyCode);
    }

    //异步判定旧密码
    @RequestMapping(value = "/vertifypwd")
    public String reg( String userphone, String userpassword){
        return updUserService.reg(userphone,userpassword);
    }

    //判定新密码格式
    @RequestMapping(value = "/vertifypwd2")
    public String pwd( String userpassword2){
        return updUserService.pwd(userpassword2);
    }


    //更新
    @RequestMapping(value = "updpwd")
    public String updpwd( String userphone, String vrifyCode,
                         String userpassword, String userpassword2){
        return updUserService.updpwd(userphone,vrifyCode,userpassword,userpassword2);
    }


}
