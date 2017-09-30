package com.huawei.web;

import com.huawei.service.SportSortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/sportSortController")
public class SportSortController {

    @Autowired
    private SportSortService sportSortService;

}
