package com.huawei.dao;

import com.huawei.bean.SportCondition;

import java.util.List;

public interface SportConditionDao {

    List<SportCondition> findAll();
    int add(SportCondition sportCondition);

    List<SportCondition> getSportConditionsByUserId(int userId);

    int deleteSportConditionById(int id);

    int updateFinishStatus(SportCondition sportCondition);

    SportCondition getSportCondition(SportCondition sportCondition);

}
