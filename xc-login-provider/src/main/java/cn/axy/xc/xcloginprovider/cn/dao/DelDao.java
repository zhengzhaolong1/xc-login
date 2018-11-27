package cn.axy.xc.xcloginprovider.cn.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface DelDao {
    @Delete("delete from user where userphone = #{userphone} and password = #{password}")
    int del(@Param("userphone") String userphone,@Param("password")String password);
}
