package cn.axy.xc.xcloginprovider.cn.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface RegDao {
    @Insert("insert into user (username,userphone,password) VALUES (#{username},#{userphone},#{password})")
    int reg(@Param("username")String username,@Param("userphone") String userphone,@Param("password") String password);

    @Insert("insert into user (username,password) values (#{username},#{password})")
    int reg2(@Param("username")String username,@Param("password")String password);
}
