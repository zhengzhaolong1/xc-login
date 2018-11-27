package cn.axy.xc.xcloginprovider.cn.sevice;

import org.apache.ibatis.annotations.Param;

public interface UpdPwdService {
    String updPwd( String userphone,  String password);
}
