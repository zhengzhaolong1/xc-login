package cn.axy.xc.xcloginprovider.cn.util;


import com.alibaba.druid.filter.config.ConfigTools;
import org.springframework.stereotype.Component;

@Component
public class DruidPassword {
    //对数据库登录密码进行加密
    private String password;

    public void testDruid() {
        password = "rootroot";
        try {
            ConfigTools.main(new String[]{password});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}