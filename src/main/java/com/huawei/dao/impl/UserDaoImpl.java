package com.huawei.dao.impl;

import com.huawei.bean.User;
import com.huawei.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> findAll() {
        List<User> list = jdbcTemplate.query("SELECT * from tb_user t where t.status=0 order by t.ct_date desc",new Object[]{},new BeanPropertyRowMapper<>(User.class));
        if(list !=null){
            return list;
        }
        return null;
    }

    @Override
    public int add(User user) {
        int resRow = jdbcTemplate.update("INSERT INTO tb_user(userName,sex,age,phone,ct_date) VALUES(?,?,?,?,?)",new Object[]{user.getUserName(),
        user.getSex(),user.getAge(),user.getPhone(),user.getCt_date()});
        return resRow;
    }

    @Override
    public int deleteByUserId(int userId) {
        int count = jdbcTemplate.update("UPDATE tb_user t set t.status=1 where t.userId=?",new Object[]{userId});
        return count;
    }

    @Override
    public int updateByUserId(User user) {
        int count = jdbcTemplate.update("UPDATE tb_user t set t.userName=?,t.sex=?,t.age=?,t.phone=? where t.userId=?",new Object[]{user.getUserName(),
        user.getSex(),user.getAge(),user.getPhone(),user.getUserId()});
        return count;
    }

}
