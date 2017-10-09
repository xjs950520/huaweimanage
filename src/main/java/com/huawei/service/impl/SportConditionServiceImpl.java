package com.huawei.service.impl;

import com.huawei.bean.SportCondition;
import com.huawei.dao.SportConditionDao;
import com.huawei.service.SportConditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SportConditionServiceImpl implements SportConditionService {

    @Autowired
    private SportConditionDao sportConditionDao;

    @Override
    public List<SportCondition> findAll() {
        return sportConditionDao.findAll();
    }

    @Override
    public int add(SportCondition sportCondition) {
        return sportConditionDao.add(sportCondition);
    }

    @Override
    public List<SportCondition> getSportConditionsByUserId(int userId) {
        return sportConditionDao.getSportConditionsByUserId(userId);
    }

    @Override
    public int deleteSportConditionById(int id) {
        return sportConditionDao.deleteSportConditionById(id);
    }

    @Override
    public int updateFinishStatus(SportCondition sportCondition) {
        return sportConditionDao.updateFinishStatus(sportCondition);
    }

    @Override
    public SportCondition getSportCondition(SportCondition sportCondition) {
        return sportConditionDao.getSportCondition(sportCondition);
    }
}
