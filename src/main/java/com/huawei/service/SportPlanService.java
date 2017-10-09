package com.huawei.service;

import com.huawei.bean.SportPlan;

import java.util.List;

public interface SportPlanService {
    int addSportPlan(SportPlan sportPlan);
    int updateSportPlan(SportPlan sportPlan);
    List<SportPlan> getSportPlans(SportPlan sportPlan);
    int deleteSportPlanByUserIdAndDate(SportPlan sportPlan);
    SportPlan getSportPlanById(int id);
    int updateSportPlanWatchTime(SportPlan sportPlan);
}
