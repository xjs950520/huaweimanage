package com.huawei.dao;

import com.huawei.bean.SportSort;

import java.util.List;

public interface SportSortDao {
    List<SportSort> findAll();
    SportSort getSportSortBySportName(String sportName);
    SportSort getSportSortBySportId(int sportId);
}
