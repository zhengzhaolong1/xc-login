package cn.axy.xc.xcloginprovider;

import cn.axy.xc.xcloginprovider.cn.pojo.User;
import cn.axy.xc.xcloginprovider.cn.sevice.ForgetPwdService;
import cn.axy.xc.xcloginprovider.cn.sevice.RegService;
import cn.axy.xc.xcloginprovider.cn.sevice.UpdPwdService;
import cn.axy.xc.xcloginprovider.cn.sevice.UserService;
import cn.axy.xc.xcloginprovider.cn.util.DruidPassword;
import com.netflix.discovery.converters.Auto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class XcLoginProviderApplicationTests {

    @Autowired
    private ForgetPwdService forgetPwdService;
    @Autowired
    private DruidPassword druidPassword;

    @Test
    public void contextLoads() {
//        String s = forgetPwdService.updPwdByPhone("17635889990");
//        System.out.println(s);

//        String s1 = forgetPwdService.verifyCheck("507907");
//        System.out.println(s1);
//

    }


//    @Test
//    public void password(){
//        druidPassword.testDruid();
//    }

//
//    @Autowired
//    private UserService userService;
//    @Test
//    public void  show(){
//        User user = new User();
//        user.setUsername("aaa");
//        User admin = userService.fingBy(user);
//        System.out.println(admin.getPassword());
//    }

//    @Autowired
//    private UpdPwdService updPwdService;
//    @Test
//    public  void upd(){
//        updPwdService.updPwd("17835501697","789789");
//    }

//
//    @Autowired
//    private UserService userService;
//    @Test
//    public  void ss(){
//        User aaa = userService.findByUsername("aaa");
//        System.out.println(aaa.getPassword());
//    }
//
//    @Autowired
//    private RegService regService;
//    @Test
//    public void hh(){
//        regService.reg2("fasf","sdfsd");
//    }
//



}
