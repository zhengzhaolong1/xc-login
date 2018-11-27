package cn.axy.xc.xcloginconsumer.controller;

import cn.axy.xc.xcloginconsumer.nn.Wb;
import cn.axy.xc.xcloginconsumer.service.WbCodeService;
import cn.axy.xc.xcloginconsumer.weibo4j.Oauth;
import cn.axy.xc.xcloginconsumer.weibo4j.Users;
import cn.axy.xc.xcloginconsumer.weibo4j.http.AccessToken;
import cn.axy.xc.xcloginconsumer.weibo4j.model.Useraa;
import cn.axy.xc.xcloginconsumer.weibo4j.model.WeiboException;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import java.io.IOException;



@RestController
public class WbCodeController {

    @Autowired
    private WbCodeService wbCodeService;

    @Autowired
    private Wb wb;


    //把code传后来解析
    @RequestMapping(value = "wbweibo",produces = "text/json;charset=UTF-8")
    public String weibo(String code) throws IOException {
        if (code != null) {
            Oauth oauth = new Oauth();
            String token1;
            System.out.println("code: " + code);
            try {
                //这个是通过这个code解析出一个token
                AccessToken accessToken = oauth.getAccessTokenByCode(code);
                //把token转为string类型
                token1 = accessToken.toString();
//            AccessToken [accessToken=2.008_B3TH0IOTeB926581208eIHg6AB, expireIn=157679999, refreshToken=,uid=6846691833]
                //把token1中的逗号去掉
                String str[] = token1.split(",");
                String token = str[0].split("=")[1];
                String str1[] = str[3].split("]");

                String uid = str1[0].split("=")[1];

                //下面几行是weibo4j里面定义的方法,尽量 不要改
                //这个是把token放到Users对象中 Users是微博定义的
                Users users = new Users(token);
                //这个是根据解析出的id找到对象
                Useraa useraa = users.showUserById(uid);
                //获取对象的地址（这个getLocation是weibo4j里面定义的）
                String address = useraa.getLocation();
                //获取当前的账号昵称（这个getLocation是weibo4j里面定义的）
                String name = useraa.getScreenName();

                Jedis jd = new Jedis("localhost", 6379);
                jd.set("uid", uid);


                return JSON.toJSONString("200");
            } catch (WeiboException e) {
                if (401 == e.getStatusCode()) {
                    System.out.println("Unable to get the access token.");
                } else {
                    e.printStackTrace();
                }
            }
        }else {
            return JSON.toJSONString( "500");
        }
        return JSON.toJSONString( "取消登录");
    }






    @RequestMapping("/wwbb")
    public String wwbb(){
        return wbCodeService.wwbb();
    }





}
