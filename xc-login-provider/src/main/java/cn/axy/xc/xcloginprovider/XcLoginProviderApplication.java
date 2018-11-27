package cn.axy.xc.xcloginprovider;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("cn.axy.xc.xcloginprovider.cn.dao")
public class XcLoginProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(XcLoginProviderApplication.class, args);
    }
}
