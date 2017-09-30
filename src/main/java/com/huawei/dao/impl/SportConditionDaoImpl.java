package com.huawei.dao.impl;

import com.huawei.bean.SportCondition;
import com.huawei.dao.SportConditionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SportConditionDaoImpl implements SportConditionDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<SportCondition> findAll() {
        List<SportCondition> list = jdbcTemplate.query("SELECT * from tb_sportCondition t.status=0",new Object[]{},new BeanPropertyRowMapper<>(SportCondition.class));
        if(list!=null){
            return list;
        }
        return null;
    }

    @Override
    public int add(SportCondition sportCondition) {
        int resRow = jdbcTemplate.update("INSERT INTO tb_sportCondition(userId,addDate,url,num) VALUES(?,?,?,?)",new Object[]{sportCondition.getUserId(),sportCondition.getAddDate(),
        sportCondition.getUrl(),sportCondition.getNum()});
        return resRow;
    }

    @Override
    public List<SportCondition> getSportConditionsByUserId(int userId) {
        List<SportCondition> list = jdbcTemplate.query("SELECT * from tb_sportCondition t where t.userId=? AND t.status=0",new Object[]{userId},new BeanPropertyRowMapper<>(SportCondition.class));
        return list;
    }

    @Override
    public int deleteSportConditionById(int id) {
        int count = jdbcTemplate.update("UPDATE tb_sportCondition t set t.status=1 where t.Id=?",new Object[]{id});
        return count;
    }
}
