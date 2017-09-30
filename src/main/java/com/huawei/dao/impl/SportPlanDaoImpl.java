package com.huawei.dao.impl;

import com.huawei.bean.SportPlan;
import com.huawei.dao.SportPlanDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SportPlanDaoImpl implements SportPlanDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int addSportPlan(SportPlan sportPlan) {
        int resRow = jdbcTemplate.update("INSERT INTO sportPlan(userId,sportSortId,planDate) VALUES(?,?,?)",new Object[]{sportPlan.getUserId(),sportPlan.getSportSortId(),
        sportPlan.getPlanDate()});
        return resRow;
    }

    @Override
    public int updateSportPlan(SportPlan sportPlan) {
        return 0;
    }

    @Override
    public List<SportPlan> getSportPlans(SportPlan sportPlan) {
        List<SportPlan> list = jdbcTemplate.query("SELECT * from sportplan t where t.planDate=? and t.userId=? and t.status=0",new Object[]{sportPlan.getPlanDate(),sportPlan.getUserId()},new BeanPropertyRowMapper<>(SportPlan.class));
        return list;
    }

    @Override
    public int deleteSportPlanByUserIdAndDate(SportPlan sportPlan) {
        int count = jdbcTemplate.update("UPDATE sportplan t set t.status=1 where t.userId=? and t.planDate=?",new Object[]{sportPlan.getUserId(),sportPlan.getPlanDate()});
        return count;
    }

    @Override
    public SportPlan getSportPlanById(int id) {
        List<SportPlan> list = jdbcTemplate.query("SELECT * from sportplan t where t.id=?",new Object[]{id},new BeanPropertyRowMapper<>(SportPlan.class));
        int size = list.size();
        return list.get(0);
    }
}
