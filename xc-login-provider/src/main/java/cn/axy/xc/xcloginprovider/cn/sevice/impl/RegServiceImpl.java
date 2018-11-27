package cn.axy.xc.xcloginprovider.cn.sevice.impl;

import cn.axy.xc.xcloginprovider.cn.dao.RegDao;
import cn.axy.xc.xcloginprovider.cn.sevice.RegService;
import cn.axy.xc.xcloginprovider.cn.util.VertifyPhone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegServiceImpl implements RegService {
    @Autowired
    private RegDao regDao;




    //插入手机号和密码
    @Override
    public String reg(String username,String userphone, String password) {
        int reg = regDao.reg(username,userphone, password);
        String s = "";
        if (reg > 0)
            s = "注册成功";
        else
            s ="注册失败";
        return s;
    }

    @Override
    public String reg2(String username, String password) {
        int i = regDao.reg2(username, password);
        String s = "";
        if (i> 0)
            s = "注册成功";
        else
            s ="注册失败";
        return s;

    }
}
