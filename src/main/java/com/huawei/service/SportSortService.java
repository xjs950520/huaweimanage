package com.huawei.service;

import com.huawei.bean.SportSort;

import java.util.List;

public interface SportSortService {
    List<SportSort> findAll();
    SportSort getSportSortBySportName(String sportName);
    SportSort getSportSortBySportId(int sportId);
}
