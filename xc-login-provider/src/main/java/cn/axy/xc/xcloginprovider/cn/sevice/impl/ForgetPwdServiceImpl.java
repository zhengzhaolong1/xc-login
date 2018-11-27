package cn.axy.xc.xcloginprovider.cn.sevice.impl;

import cn.axy.xc.xcloginprovider.cn.SMS.IndustrySMS;
import cn.axy.xc.xcloginprovider.cn.dao.UpdDao;
import cn.axy.xc.xcloginprovider.cn.pojo.User;
import cn.axy.xc.xcloginprovider.cn.sevice.ForgetPwdService;
import cn.axy.xc.xcloginprovider.cn.sevice.UserService;
import cn.axy.xc.xcloginprovider.cn.util.Encryption;
import cn.axy.xc.xcloginprovider.cn.util.VertifyPhone;
import cn.axy.xc.xcloginprovider.cn.util.VertifyPwd;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

@Service
public class ForgetPwdServiceImpl implements ForgetPwdService {

    @Autowired
    private UserService userService;

    @Autowired
    private UpdDao updDao;

    @Override
    public String updPwdByPhone(String userphone) {
        User byPhone = userService.findByPhone(userphone);
        boolean flag = VertifyPhone.isMobile(userphone);
        if (flag ==true &&byPhone != null){
            return JSON.toJSONString("手机号输入正确");

        }else {
            return JSON.toJSONString("手机号不存在或输入有误");
        }
    }

    @Override
    public String verifyCheck(String check) {
        Jedis jd = new Jedis("localhost",6379);
        String check1 = jd.get("check");
        if (check.equals(check1)){
            return JSON.toJSONString("验证码输入正确");
        }else {
            return JSON.toJSONString("验证码输入错误");
        }
    }


    @Override
    public String updPassword(String userphone, String check, String userpassword) {
        Jedis jd = new Jedis("localhost",6379);
        String check1 = jd.get("check");
        String userphone1 = jd.get("userphone");
        boolean flag = VertifyPhone.isMobile(userphone);
        boolean b = VertifyPwd.validatePhonePass(userpassword);


        if ((flag==true)&&(b==true)&&(userphone.equals(userphone1))&&(check.equals(check1))){
            //把新密码插入
            updDao.updPwd(userphone,Encryption.encryptBasedDes(userpassword));
            return JSON.toJSONString("200");
        }else {
            return JSON.toJSONString("注册失败，请重新注册");
        }

    }
}
