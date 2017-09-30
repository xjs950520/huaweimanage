package com.huawei.service;

import com.huawei.bean.SportCondition;

import java.util.List;

public interface SportConditionService {
    List<SportCondition> findAll();
    int add(SportCondition sportCondition);
    List<SportCondition> getSportConditionsByUserId(int userId);
    int deleteSportConditionById(int id);
}
