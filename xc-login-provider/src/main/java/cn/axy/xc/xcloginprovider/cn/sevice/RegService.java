package cn.axy.xc.xcloginprovider.cn.sevice;

import org.apache.ibatis.annotations.Param;

public interface RegService {
    String reg( String username,String userphone,  String password);

    String reg2(String username,String password);
}
