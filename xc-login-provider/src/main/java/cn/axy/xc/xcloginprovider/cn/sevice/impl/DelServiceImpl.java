package cn.axy.xc.xcloginprovider.cn.sevice.impl;

import cn.axy.xc.xcloginprovider.cn.dao.DelDao;
import cn.axy.xc.xcloginprovider.cn.sevice.DelService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DelServiceImpl implements DelService {
    @Autowired
    private DelDao delDao;

    @Override
    public String del(String userphone, String password) {
        int del = delDao.del(userphone, password);
        if (del >0){
            return JSON.toJSONString("200");
        }else {
            return JSON.toJSONString("500");
        }

    }
}
