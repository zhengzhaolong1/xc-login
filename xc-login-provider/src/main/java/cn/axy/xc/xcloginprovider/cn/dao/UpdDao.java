package cn.axy.xc.xcloginprovider.cn.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UpdDao {

    @Update("update user set password  = #{password} where  userphone = #{userphone}")
    int updPwd(@Param("userphone") String userphone,@Param("password") String password);
}

