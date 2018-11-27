package cn.axy.xc.xcloginprovider.cn.dao;

import cn.axy.xc.xcloginprovider.cn.pojo.Wbuser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface WbDao {

    @Select("select  wbuid from wbuser where  wbuid = #{wbuid}  ")
    Wbuser getWbById(@Param("wbuid") String wbuid);

    @Insert("insert into wbuser (wbuid) values #{wbuid}")
    int addUid(@Param("wbuid") String wbuid);
}
