package cn.axy.xc.xcloginprovider.cn.SMS;

public class AccountInfo {
    //这个类就只是获取开发者信息，暂时没多大用
    //实现短信功能的时其他三个类
    private static String operation ="/query/accountInfo";

    private static String accountSid =Config.ACCOUNT_SID;

    //获取开发者账号信息
    public static void excute(){
        String url = Config.BASE_URL+operation;
        String body ="accountSid="+accountSid+HttpUtil.createCommonParam();

        //提交请求
        String result = HttpUtil.post(url, body);

    }



    public static void main(String[] args) {
        //获取开发者账号信息
        excute();


    }





}
