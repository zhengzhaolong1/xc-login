package cn.axy.xc.xcloginprovider.cn.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    @Bean(name = "ss")
    public MyShiroRealm myShiroRealm(){
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        return  myShiroRealm;
    }
    @Bean(name = "dd")
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("ss")MyShiroRealm myShiroRealm){

        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }
    @Bean
    public ShiroFilterFactoryBean shirFilter(@Qualifier("dd") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
//        //设置拦截器.
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
//
        filterChainDefinitionMap.put("/static/**", "anon");
//        配置退出过滤器,其中具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");
        //定义过滤链：从上向下顺序执行，一般将/**放在最下边 这是一个坑呢，一不小心代码就不好使了;
//        authc:所有url都必须认证通过才可以访问;
//        filterChainDefinitionMap.put("/**", "authc");
//
//
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/html/login");
//        // 登录成功后要跳转的链接
//        shiroFilterFactoryBean.setSuccessUrl("/index");
//
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;

    }



}
