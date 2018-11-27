package cn.axy.xc.xcloginprovider.cn.controller;


import cn.axy.xc.xcloginprovider.cn.pojo.User;
import cn.axy.xc.xcloginprovider.cn.sevice.RegService;
import cn.axy.xc.xcloginprovider.cn.sevice.UserService;
import cn.axy.xc.xcloginprovider.cn.sevice.WeiboService;
import cn.axy.xc.xcloginprovider.cn.util.Encryption;
import cn.axy.xc.xcloginprovider.cn.util.PassWordCreate;
import cn.axy.xc.xcloginprovider.cn.weibo4j.Oauth;
import cn.axy.xc.xcloginprovider.cn.weibo4j.Users;
import cn.axy.xc.xcloginprovider.cn.weibo4j.http.AccessToken;
import cn.axy.xc.xcloginprovider.cn.weibo4j.model.Useraa;
import cn.axy.xc.xcloginprovider.cn.weibo4j.model.WeiboException;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Api(value="WeiboController",tags = "微博code接口")
@RestController
public class WeiboController {

    @Autowired
    private WeiboService weiboService;

    @Autowired
    private RegService regService;
    @Autowired
    private UserService userService;


//
//    //把code传后来解析
//    @ApiOperation(value = "根据code解析id", notes = "解析Id")
//    @RequestMapping(value = "wbweibo",produces = "text/json;charset=UTF-8")
//    public String weibo(@ApiParam(name = "code",value = "code",required=true)String code) throws IOException {
//
//        Oauth oauth = new Oauth();
//        String token1;
//        System.out.println("code: " + code);
//        try{
//            //这个是通过这个code解析出一个token
//            AccessToken accessToken = oauth.getAccessTokenByCode(code);
//            //把token转为string类型
//            token1 = accessToken.toString();
////            AccessToken [accessToken=2.008_B3TH0IOTeB926581208eIHg6AB, expireIn=157679999, refreshToken=,uid=6846691833]
//            //把token1中的逗号去掉
//            String str[] = token1.split(",");
//            String token = str[0].split("=")[1];
//            String str1[] = str[3].split("]");
//
//            String uid = str1[0].split("=")[1];
//
//            //下面几行是weibo4j里面定义的方法,尽量 不要改
//            //这个是把token放到Users对象中 Users是微博定义的
//            Users users = new Users(token) ;
//            //这个是根据解析出的id找到对象
//            Useraa useraa = users.showUserById(uid);
//            //获取对象的地址（这个getLocation是weibo4j里面定义的）
//            String address = useraa.getLocation();
//            //获取当前的账号昵称（这个getLocation是weibo4j里面定义的）
//            String name = useraa.getScreenName();
//
//            //如果在user表里面查不到,说明是第一次登陆
//            if (userService.findByUsername(uid) == null){
//                User user = new User();
//                user.setUsername(uid);
//                System.out.println(user.getUsername());
//                PassWordCreate p = new PassWordCreate();
//                //生成一个随机密码
//                String wbpassWord = p.createPassWord(10);
//                //一起把weibo的唯一id和随机密码查到user表中
//                regService.reg2(uid,Encryption.encryptBasedDes(wbpassWord));
//            }
//            //这下面是自己定义的，想要在页面上显示什么都行
//          //这个是放了一个照片路径
//         // p.setPeoUrl("http://tva3.sinaimg.cn/crop.0.0.996.996.50/005ZiivJjw8f7uw5fc2ivj30ro0rp76d.jpg");
//            User byUsername = userService.findByUsername(uid);
//            Integer uid1 = byUsername.getUid();
//            String s = String.valueOf(uid1);
//            System.out.println(s);
//            Map<String,String> map = new HashMap<>();
//            map.put("code","200");
//            map.put("id",s);
//            return JSON.toJSONString(map);
//        } catch (WeiboException e) {
//            if(401 == e.getStatusCode()){
//                System.out.println("Unable to get the access token.");
//            }else{
//                e.printStackTrace();
//            }
//        }
//        return JSON.toJSONString( "500");
//    }

    @RequestMapping("/wwbb")
    public String wwbb(){
        Jedis jd = new Jedis("localhost",6379);
        String uid = jd.get("uid");
        String ins = weiboService.ins(uid);
        return JSON.toJSONString(ins);
    }



}
