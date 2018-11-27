package cn.axy.xc.xcloginprovider.cn.util;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;


public class VertifyPhone {
    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

    /**
     * 校验手机号
     *
     * @param
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobile(String userPhone) {
        return Pattern.matches(REGEX_MOBILE, userPhone);
    }


    //测试
//    public static void main(String[] args) {
//        boolean mobile = isMobile("6846691833");
//        System.out.println(mobile);
//    }


}
