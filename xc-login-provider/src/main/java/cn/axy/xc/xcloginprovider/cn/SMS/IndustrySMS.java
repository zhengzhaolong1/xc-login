package cn.axy.xc.xcloginprovider.cn.SMS;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.net.URLEncoder;

/**
 * 验证码通知短信接口
 *
 * @ClassName: IndustrySMS
 * @Description: 验证码通知短信接口
 *
 */
public class IndustrySMS {
    //这个好像也时死的，不用改
    private static String operation = "/industrySMS/sendSMS";
    //这个好像也是死的，不用改
    private static String accountSid = Config.ACCOUNT_SID;
    //这个是验证码发送的内容
    // smscontent=【短信签名】+内容，发送内容要与模板匹配
    private static String smsContent = "【小城购物通】登录验证码：{1},如非本人操作，请忽略此短信。";
    //这是申请的短信模板前面的id
    private static String templateid = "899370726";



    //这是获取随机数
    public static String getRandom() {

        return String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
    }


    /**
     * 验证码通知短信
     * String to 是要发送的手机号
     */
    public static String execute(String to) {
//        获取随机数字字符串
        String param = getRandom();
        String tmpSmsContent = null;
        try {
            tmpSmsContent = URLEncoder.encode(templateid, "UTF-8");
        } catch (Exception e) {
        }
        String url = Config.BASE_URL + operation;
        String body = "accountSid=" + accountSid + "&to=" + to + "&templateid="
                + templateid + HttpUtil.createCommonParam() + "&param=" + param;
        // 提交请求
        String result = HttpUtil.post(url, body);
        JSONObject jsonObject = JSON.parseObject(result);
        String respCode = (String) jsonObject.get("respCode");
        if (respCode.equals("00000")) {
            System.out.println(param);
            return param;
        } else {
            System.out.println("0");
            return respCode;
        }

    }



    public static void main(String[] args) {
        //这个是获取0-9之间的随机数
        double v = Math.random() * 9;
        //后面加1是为了在获取到0的时候，0不显示而导致的验证码位数不够
        double v1 = Math.random() * 9 + 1;

        System.out.println(v);
        System.out.println(v1);


        String param = getRandom();
        for (int i = 0; i < 5; i++) {

            System.out.println(param + "这是");
        }
        //获取3组五位的随机数
        for (int j = 0; j < 3; j++) {
            System.out.println((int) ((Math.random() * 9 + 1) * 100000));
        }

        //获取短信
        execute("17835501697");
    }


}

