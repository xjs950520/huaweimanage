package com.huawei.service.impl;

import com.huawei.bean.User;
import com.huawei.dao.UserDao;
import com.huawei.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public int add(User user) {
        return userDao.add(user);
    }

    @Override
    public int deleteByUserId(int userId) {
        return userDao.deleteByUserId(userId);
    }

    @Override
    public int updateByUserId(User user) {
        return userDao.updateByUserId(user);
    }
}
