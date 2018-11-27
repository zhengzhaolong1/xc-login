package cn.axy.xc.xcloginprovider.cn.config;

import cn.axy.xc.xcloginprovider.cn.pojo.User;
import cn.axy.xc.xcloginprovider.cn.sevice.UserService;
import cn.axy.xc.xcloginprovider.cn.util.Encryption;
import cn.axy.xc.xcloginprovider.cn.util.VertifyName;
import cn.axy.xc.xcloginprovider.cn.util.VertifyPhone;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class MyShiroRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    ////这个是用来授权的    说白了就是看什么角色有什么权限
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    //主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
//        User user = userService.findByUsername(token.getUsername());
//        User user = userService.findByPhone(token.getUsername());
        //注意这里改了，错了查这里
        User u = new User();
        boolean mobile = VertifyPhone.isMobile(token.getUsername());
        Boolean show = VertifyName.show(token.getUsername());
        //如果不是手机号
        if (show ==true){
                u.setUserPhone(token.getUsername());
        }else {
            u.setUsername(token.getUsername());
        }
        User user = userService.fingBy(u);
        if (user == null){
            throw  new UnknownAccountException(
                    "不对啊，不要瞎写"
            );
        }
        //把解密后的密码放进去
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                "",
                Encryption.decryptBasedDes(user.getPassword()),
                ""
        );

        return authenticationInfo;
    }
}
