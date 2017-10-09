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
import org.springframework.web.bind.annotation.ResponseBody;

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
        if(id != null && !id.equals("")){
            SportPlan sportPlan1 = sportPlanService.getSportPlanById(Integer.valueOf(id));
            int sportSortId1 = sportPlan1.getSportSortId();
            SportSort sportSort1 = sportSortService.getSportSortBySportId(sportSortId1);
            String sportSortName1 = sportSort1.getSportName();
            String url = sportSort1.getSportUrl();
            request.setAttribute("url",url);
            request.setAttribute("sportSortName",sportSortName1);
            request.setAttribute("sportPlanId",id);
            request.setAttribute("watchTime",sportPlan1.getWatchTime());
            request.setAttribute("finishStatus",sportPlan1.getFinishStatus());
        }
        int userId=0;
        if(request.getSession().getAttribute("userId")!=null){
            userId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        }

        //链接访问入口
        if(request.getParameter("userId")!=null){
            userId = Integer.valueOf(request.getParameter("userId"));
            request.getSession().setAttribute("userId",userId);
        }

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
        if(id == null || id.equals("")){
            request.setAttribute("url",sportPlans.get(0).getUrl());
            request.setAttribute("sportSortName",sportPlans.get(0).getSportSortName());
            request.setAttribute("sportPlanId",sportPlans.get(0).getId());
            request.setAttribute("watchTime",sportPlans.get(0).getWatchTime());
            request.setAttribute("finishStatus",sportPlans.get(0).getFinishStatus());
        }
        request.setAttribute("sportPlans",sportPlans);
        return "sportPlans";
    }
    @RequestMapping(value = "/updateSportPlanFinishStatus")
    @ResponseBody
    public String updateSportPlanFinishStatus(HttpServletRequest request){
        int id = Integer.valueOf(request.getParameter("id"));
        String watchTime = request.getParameter("watchTime");
        SportPlan sportPlan = new SportPlan();
        sportPlan.setId(id);
        sportPlan.setWatchTime(watchTime);
        String result="false";
        int count = sportPlanService.updateSportPlan(sportPlan);

        //修改运动状况的完成情况
        sportPlan = sportPlanService.getSportPlanById(id);
        int userId = sportPlan.getUserId();
        String addDate = sportPlan.getPlanDate();
        SportCondition sportCondition = new SportCondition();
        sportCondition.setUserId(userId);
        sportCondition.setAddDate(addDate);
        sportCondition = sportConditionService.getSportCondition(sportCondition);
        int num = sportCondition.getNum();
        String finish_status = sportCondition.getFinish_status();
        int finish_status1 = Integer.valueOf(finish_status);
        if(num>finish_status1){
            finish_status1++;
            finish_status = String.valueOf(finish_status1);
            sportCondition.setFinish_status(finish_status);
            sportConditionService.updateFinishStatus(sportCondition);
        }

        if(count>0){
            result = "true";
        }
        return result;
    }
    @RequestMapping(value = "/updateSportPlanWatchTime")
    @ResponseBody
    public String updateSportPlanWatchTime(HttpServletRequest request){
        int id = Integer.valueOf(request.getParameter("id"));
        String watchTime = request.getParameter("watchTime");
        SportPlan sportPlan = new SportPlan();
        sportPlan.setId(id);
        sportPlan.setWatchTime(watchTime);
        String result="false";
        int count = sportPlanService.updateSportPlanWatchTime(sportPlan);
        if(count>0){
            result = "true";
        }
        return result;
    }

}
