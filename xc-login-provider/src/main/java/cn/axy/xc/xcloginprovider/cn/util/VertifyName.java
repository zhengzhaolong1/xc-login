package cn.axy.xc.xcloginprovider.cn.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VertifyName {
    public static Boolean show(String username){
        //判定是纯数字
        String regex = "[0-9]*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(username);
        Boolean b = matcher.matches();
        if (b ){
            return   true;
        }else {
            return false;
        }

    }

    public static void main(String[] args) {
        Boolean show = show("76745g");
        System.out.println(show);
    }
}
