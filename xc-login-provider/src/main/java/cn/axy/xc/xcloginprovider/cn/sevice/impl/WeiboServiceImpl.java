package cn.axy.xc.xcloginprovider.cn.sevice.impl;

import cn.axy.xc.xcloginprovider.cn.dao.WbDao;
import cn.axy.xc.xcloginprovider.cn.pojo.User;
import cn.axy.xc.xcloginprovider.cn.pojo.Wbuser;
import cn.axy.xc.xcloginprovider.cn.sevice.RegService;
import cn.axy.xc.xcloginprovider.cn.sevice.UserService;
import cn.axy.xc.xcloginprovider.cn.sevice.WeiboService;
import cn.axy.xc.xcloginprovider.cn.util.Encryption;
import cn.axy.xc.xcloginprovider.cn.util.PassWordCreate;
import cn.axy.xc.xcloginprovider.cn.weibo4j.Oauth;
import cn.axy.xc.xcloginprovider.cn.weibo4j.model.WeiboException;
import cn.axy.xc.xcloginprovider.cn.weibo4j.util.BareBonesBrowserLaunch;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class WeiboServiceImpl implements WeiboService {

    @Autowired
    private WbDao wbDao;

    @Autowired
    private UserService userService;

    @Autowired
    private RegService regService;

    @Override
    public String addUid(String wbuid) {
        int i = wbDao.addUid(wbuid);
        String s = "";
        if (i >0){
            s = "成功";
        }else {
            s ="失败";
        }
        return s;
    }

    @Override
    public String  ins(String uid) {
        if (userService.findByUsername(uid) == null) {
            User user = new User();
            user.setUsername(uid);
            System.out.println(user.getUsername());
            PassWordCreate p = new PassWordCreate();
            //生成一个随机密码
            String wbpassWord = p.createPassWord(10);
            //一起把weibo的唯一id和随机密码查到user表中
            regService.reg2(uid,Encryption.encryptBasedDes(wbpassWord));
        }

        User byUsername = userService.findByUsername(uid);
            Integer uid1 = byUsername.getUid();
            String s = String.valueOf(uid1);
            System.out.println(s);
            Map<String,String> map = new HashMap<>();
            map.put("id",s);
        return JSON.toJSONString(map);
    }

    //通过微博唯一Id查找有没有绑定手机号
    @Override
    public Wbuser getWbById(String wbuid) {

        return wbDao.getWbById(wbuid);
    }




}
