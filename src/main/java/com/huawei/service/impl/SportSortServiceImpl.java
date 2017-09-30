package com.huawei.service.impl;

import com.huawei.bean.SportSort;
import com.huawei.dao.SportSortDao;
import com.huawei.service.SportSortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SportSortServiceImpl implements SportSortService {

    @Autowired
    private SportSortDao sportSortDao;

    @Override
    public List<SportSort> findAll() {
        return sportSortDao.findAll();
    }

    @Override
    public SportSort getSportSortBySportName(String sportName) {
        return sportSortDao.getSportSortBySportName(sportName);
    }

    @Override
    public SportSort getSportSortBySportId(int sportId) {
        return sportSortDao.getSportSortBySportId(sportId);
    }
}
