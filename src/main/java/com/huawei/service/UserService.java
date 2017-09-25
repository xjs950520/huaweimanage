package com.huawei.service;

import com.huawei.bean.User;

import java.util.List;

public interface UserService {

    List<User> findAll();
    int add(User user);
    int deleteByUserId(int userId);
    int updateByUserId(User user);
}
