package cn.axy.xc.xcloginprovider.cn.dao;

import cn.axy.xc.xcloginprovider.cn.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;


@Component
public interface UserDao {



    //通过手机号查密码
    public User findByPhone(@Param("userphone")String userphone);



    public User findByUsername(@Param("username")String username);


    //注意注解实体类的使用
    //注意mapper里面动态sql的用法
    public User fingBy(@Param("user") User user);
}
