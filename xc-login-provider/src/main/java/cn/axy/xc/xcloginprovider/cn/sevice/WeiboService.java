package cn.axy.xc.xcloginprovider.cn.sevice;

import cn.axy.xc.xcloginprovider.cn.pojo.Wbuser;
import org.apache.ibatis.annotations.Param;

public interface WeiboService {

//    public void startWb();

    //看表中有没有微博唯一Id
    Wbuser getWbById( String wbuid);

    //添加微博id进表
    String addUid(String wbuid);


    public String  ins(String uid);
}
