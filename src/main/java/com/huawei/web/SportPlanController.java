package com.huawei.web;

import com.huawei.bean.SportCondition;
import com.huawei.bean.SportPlan;
import com.huawei.bean.SportSort;
import com.huawei.service.SportConditionService;
import com.huawei.service.SportPlanService;
import com.huawei.service.SportSortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/sportPlanController")
public class SportPlanController {

    @Autowired
    private SportPlanService sportPlanService;
    @Autowired
    private SportConditionService sportConditionService;
    @Autowired
    private SportSortService sportSortService;


    @RequestMapping(value = "/getSportPlans")
    public String getSportPlans(HttpServletRequest request){
        String id = request.getParameter("id");
        if(id != null){
            SportPlan sportPlan1 = sportPlanService.getSportPlanById(Integer.valueOf(id));
            int sportSortId1 = sportPlan1.getSportSortId();
            SportSort sportSort1 = sportSortService.getSportSortBySportId(sportSortId1);
            String sportSortName1 = sportSort1.getSportName();
            String url = sportSort1.getSportUrl();
            request.setAttribute("url",url);
            request.setAttribute("sportSortName",sportSortName1);
        }
        int userId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        String date = request.getParameter("date");
        if(date!=null){
            request.getSession().setAttribute("date",date);
        }else{
            date = String.valueOf(request.getSession().getAttribute("date"));
        }

        SportPlan sportPlan = new SportPlan();
        sportPlan.setUserId(userId);
        sportPlan.setPlanDate(date);
        List<SportPlan> sportPlans = sportPlanService.getSportPlans(sportPlan);
        int i=1;
        SportSort sportSort=null;
        for(SportPlan s:sportPlans){
            int sportSortId = s.getSportSortId();
            sportSort = sportSortService.getSportSortBySportId(sportSortId);
            s.setNumber(i);
            s.setSportSortName(sportSort.getSportName());
            s.setUrl(sportSort.getSportUrl());
            i++;
        }
        request.setAttribute("sportPlans",sportPlans);
        return "sportPlans";
    }

}
