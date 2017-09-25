package com.huawei.dao;

import com.huawei.bean.User;

import java.util.List;

public interface UserDao {

    List<User> findAll();
    int add(User user);
    int deleteByUserId(int userId);
    int updateByUserId(User user);
}
