package cn.axy.xc.xcloginprovider.cn.sevice;

import cn.axy.xc.xcloginprovider.cn.pojo.User;
import org.apache.ibatis.annotations.Param;


public interface UserService {


    //通过手机号查东西
    public User findByPhone(String userphone);


    //通过用户名查东西
    public User findByUsername(String username);


    public User fingBy(User user);



}
