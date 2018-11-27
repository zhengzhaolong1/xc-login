package cn.axy.xc.xcloginprovider.cn.sevice.impl;

import cn.axy.xc.xcloginprovider.cn.dao.UpdDao;
import cn.axy.xc.xcloginprovider.cn.sevice.UpdPwdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdPwdImpl implements UpdPwdService {
    @Autowired
    private UpdDao updDao;

    @Override
    public String updPwd(String userphone, String password) {
        int i = updDao.updPwd(userphone, password);
        String s = "";
        if (i > 0){
            s = "200";
        }else{
            s ="500";
        }

        return  s;

    }
}
