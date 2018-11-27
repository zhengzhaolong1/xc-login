package cn.axy.xc.xcloginprovider.cn.util;

public class VertifyPwd {
    //正则表达式判断注册密码 密码必须是字母加数字

    public static boolean validatePhonePass(String password) {
        boolean isDigit = false;//定义一个boolean值，用来表示是否包含数字
        boolean isLetter = false;//定义一个boolean值，用来表示是否包含字母
        for (int i = 0; i < password.length(); i++) {
            //用char包装类中的判断数字的方法判断每一个字符
            if (Character.isDigit(password.charAt(i))) {
                isDigit = true;
                //用char包装类中的判断字母的方法判断每一个字符
            } else if (Character.isLetter(password.charAt(i))) {
                isLetter = true;
            }
        }
        String regex = "^[a-zA-Z0-9]{6,20}$";
        boolean isRight = isDigit && isLetter && password.matches(regex);
        return isRight;

    }
//    //测试
//    public static void main(String[] args) {
//        boolean b1 = VertifyPwd.validatePhonePass("rfsdfsdafst");
//        boolean b2 = VertifyPwd.validatePhonePass("r7989889t");
//        boolean b3 = VertifyPwd.validatePhonePass("78788789");
//        System.out.println(b1+"\tb1");
//        System.out.println(b2+"\tb2");
//        System.out.println(b3+"\tb3");
//
//    }



}
