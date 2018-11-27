package cn.axy.xc.xcloginprovider.cn.sevice;

public interface ForgetPwdService {


    public String updPwdByPhone(String userphone);


    public String verifyCheck(String check);

    public String updPassword(String userphone,String check,String userpassword);



}
