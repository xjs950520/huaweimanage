package com.huawei.service.impl;

import com.huawei.bean.SportPlan;
import com.huawei.dao.SportPlanDao;
import com.huawei.service.SportPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SportPlanServiceImpl implements SportPlanService {

    @Autowired
    private SportPlanDao sportPlanDao;

    @Override
    public int addSportPlan(SportPlan sportPlan) {
        return sportPlanDao.addSportPlan(sportPlan);
    }

    @Override
    public int updateSportPlan(SportPlan sportPlan) {
        return sportPlanDao.updateSportPlan(sportPlan);
    }

    @Override
    public List<SportPlan> getSportPlans(SportPlan sportPlan) {
        return sportPlanDao.getSportPlans(sportPlan);
    }

    @Override
    public int deleteSportPlanByUserIdAndDate(SportPlan sportPlan) {
        return sportPlanDao.deleteSportPlanByUserIdAndDate(sportPlan);
    }

    @Override
    public SportPlan getSportPlanById(int id) {
        return sportPlanDao.getSportPlanById(id);
    }

    @Override
    public int updateSportPlanWatchTime(SportPlan sportPlan) {
        return sportPlanDao.updateSportPlanWatchTime(sportPlan);
    }
}
