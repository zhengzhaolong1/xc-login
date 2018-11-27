package cn.axy.xc.xcloginprovider.cn.sevice.impl;

import cn.axy.xc.xcloginprovider.cn.dao.UserDao;
import cn.axy.xc.xcloginprovider.cn.pojo.User;
import cn.axy.xc.xcloginprovider.cn.sevice.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
   @Autowired
    private UserDao userDao;



    @Override
    public User findByPhone(String userphone) {
        return userDao.findByPhone(userphone);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User fingBy(User user) {
        return userDao.fingBy(user);
    }



}
