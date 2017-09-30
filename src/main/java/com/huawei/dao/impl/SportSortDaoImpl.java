package com.huawei.dao.impl;

import com.huawei.bean.SportSort;
import com.huawei.dao.SportSortDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SportSortDaoImpl implements SportSortDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<SportSort> findAll() {
        List<SportSort> list = jdbcTemplate.query("SELECT * from tb_sportSort",new Object[]{},new BeanPropertyRowMapper<>(SportSort.class));
        if(list!=null){
            return list;
        }
        return null;
    }

    @Override
    public SportSort getSportSortBySportName(String sportName) {
        List<SportSort> list = jdbcTemplate.query("SELECT * from tb_sportSort t where t.sportName = ?",new Object[]{sportName},new BeanPropertyRowMapper<>(SportSort.class));
        if(list != null && list.size()>0){
            return list.get(0);
        }else{
            return null;
        }
    }

    @Override
    public SportSort getSportSortBySportId(int sportId) {
        List<SportSort> list = jdbcTemplate.query("SELECT * from tb_sportSort t where t.sportId = ?",new Object[]{sportId},new BeanPropertyRowMapper<>(SportSort.class));
        if(list != null && list.size()>0){
            return list.get(0);
        }else{
            return null;
        }
    }
}
