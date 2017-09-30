package com.huawei.dao;

import com.huawei.bean.SportCondition;
import com.huawei.bean.SportPlan;

import java.util.List;

public interface SportPlanDao {
    int addSportPlan(SportPlan sportPlan);
    int updateSportPlan(SportPlan sportPlan);
    List<SportPlan> getSportPlans(SportPlan sportPlan);
    int deleteSportPlanByUserIdAndDate(SportPlan sportPlan);
    SportPlan getSportPlanById(int id);
}
