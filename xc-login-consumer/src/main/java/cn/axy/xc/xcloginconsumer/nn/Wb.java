package cn.axy.xc.xcloginconsumer.nn;

import cn.axy.xc.xcloginconsumer.weibo4j.Oauth;
import cn.axy.xc.xcloginconsumer.weibo4j.Users;
import cn.axy.xc.xcloginconsumer.weibo4j.http.AccessToken;
import cn.axy.xc.xcloginconsumer.weibo4j.model.Useraa;
import cn.axy.xc.xcloginconsumer.weibo4j.model.WeiboException;
import cn.axy.xc.xcloginconsumer.weibo4j.util.BareBonesBrowserLaunch;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.io.IOException;

@Component
public class Wb {

    //微博登录的方法

    public void startWb() {
        //登录和退出的时候一定要注意一下。如果想实现登录以后，退出了再登录必须再次输入账号和密码
        // 这个功能，必须在oauth.authorize（）这个方法最后加上"&forcelogin=true"。
        try {
            Oauth oauth = new Oauth();
            BareBonesBrowserLaunch.openURL(oauth.authorize("code"));
            System.out.println("oauth.authorize"+oauth.authorize("code"));
            //这个异常是weibo4j里面自己定义的异常
        } catch (WeiboException e) {
            e.printStackTrace();
        }
    }


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
            return JSON.toJSONString("500");
        }else{
            return JSON.toJSONString("500");
        }
    }


}
