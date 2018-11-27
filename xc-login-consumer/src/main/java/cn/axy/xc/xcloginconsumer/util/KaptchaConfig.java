package cn.axy.xc.xcloginconsumer.util;


import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KaptchaConfig {
    //图片校验码
    @Bean
    public DefaultKaptcha getDefaultKaptcha() {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        //属性
        Properties properties = new Properties();
        //这个表示用不用边框
        properties.setProperty("kaptcha.border", "yes");
        //验证码长度
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        //session.key
        properties.setProperty("kaptcha.session.key", "code");
        //这个是设置字体颜色
        properties.setProperty("kaptcha.textproducer.font.color", "red");
        //这个是设置干扰线颜色
        properties.setProperty("kaptcha.noise.color", "green");
        //文字间隔
        properties.setProperty("kaptcha.textproducer.char.space", "4");
        //这个是设置边框的颜色
        //properties.setProperty("kaptcha.border.color", "105,179,90");
        //这个是设置图片的宽度
        //properties.setProperty("kaptcha.image.width", "120");
        //这个是设置图片的高度
        //properties.setProperty("kaptcha.image.height", "45");
        //这个是设置字体的大小
        //properties.setProperty("kaptcha.textproducer.font.size", "30");
        //这个是设置字体的样式
        //properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);

        return defaultKaptcha;
    }
}
