package com.huawei.web;

import com.huawei.bean.SportCondition;
import com.huawei.bean.SportPlan;
import com.huawei.bean.SportSort;
import com.huawei.service.SportConditionService;
import com.huawei.service.SportPlanService;
import com.huawei.service.SportSortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/sportController")
public class SportController {

    @Autowired
    private SportSortService sportSortService;

    @Autowired
    private SportConditionService sportConditionService;

    @Autowired
    private SportPlanService sportPlanService;

    @GetMapping(value = "/findAll")
    public String SportCondition(HttpServletRequest request){
        String parameter = request.getParameter("userId");
        int userId=0;
        if(parameter==null){
            userId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        }else if(parameter!=null){
            userId = Integer.valueOf(parameter);
        }
//        List<SportCondition> sportConditions = sportConditionService.getSportConditionsByUserId(userId);

        int pageno = Integer.parseInt(request.getParameter("pageno")==null || request.getParameter("pageno").equals("")?"1":request.getParameter("pageno"));
        int pageSize = 5;
        if(request.getParameter("pageSize")!=null){
            pageSize = Integer.parseInt(request.getParameter("pageSize"));
        }else if(request.getSession().getAttribute("p")!=null && request.getParameter("pageSize")==null){
            pageSize = (int) request.getSession().getAttribute("p");
        }
        List<SportCondition> bigList = sportConditionService.getSportConditionsByUserId(userId);
        if(bigList!=null){

            List<SportCondition> sportConditions = new ArrayList<SportCondition>();
            int count=bigList.size();
            request.setAttribute("count",count);
            //计算开始标识 = 页数大小 * （页码-1）
            int beginIndex = pageSize*(pageno - 1);
            //结束标识
            int endIndex = pageSize*pageno>bigList.size()?bigList.size():pageSize*pageno;
            for(int i=beginIndex;i<endIndex;i++){
                sportConditions.add(bigList.get(i));
            }
            String scale=null;
            for(SportCondition sportCondition:sportConditions){
                scale="0";
                if(!sportCondition.getFinish_status().equals("0")){
                    scale = sportCondition.getFinish_status()+"/"+sportCondition.getNum();
                    if(sportCondition.getFinish_status().equals(String.valueOf(sportCondition.getNum()))){
                        scale = "1";
                    }
                }
                sportCondition.setScale(scale);
            }
            request.getSession().setAttribute("list",sportConditions);
            int totalPage=0;
            double j = (double)count/pageSize;//计算共有多少页
            if(Math.round(j)!=j ){
                totalPage=(int)j+1;
            }else{
                totalPage = (int) j;
            }
            int currentPage=pageno;
            request.setAttribute("totalPage",totalPage);
            request.setAttribute("sportConditions",sportConditions);
            request.setAttribute("currentPage",currentPage);
            request.setAttribute("pageSize",pageSize);
            request.getSession().setAttribute("p", pageSize);
        }



        List<SportSort> list = sportSortService.findAll();
        request.setAttribute("sportSorts",list);
//        request.setAttribute("sportConditions",sportConditions);
        request.getSession().setAttribute("userId",userId);
        return "sportCondition";
    }
    @PostMapping(value = "/addSportCondition")
    @ResponseBody
    public String testPost(@RequestBody String[] arr,HttpServletRequest request){
        String result="false";
        int userId =Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));//得到添加的视频拥有者
        SportSort sportSort = null;
        int projectNum=arr.length-1;//项目数量
        String time = arr[0];//获得运动计划的添加时间
        SportPlan sportPlan = null;
        for(int i=1;i<arr.length;i++){
            String sportName = arr[i];
            sportSort = sportSortService.getSportSortBySportName(sportName);
            int sportId = sportSort.getSportId();//得到所要添加的运动视频唯一标识
            sportPlan = new SportPlan();
            sportPlan.setUserId(userId);
            sportPlan.setSportSortId(sportId);
            sportPlan.setPlanDate(time);
            if(sportPlanService.addSportPlan(sportPlan)>0){
                result = "true";
            }else{
                result="false";
            }

        }
        if(result.equals("true")){
            //运动状况的添加
            SportCondition sportCondition = new SportCondition();
            sportCondition.setUserId(userId);
            sportCondition.setAddDate(time);
            String url="http://localhost:8080/sportPlanController/getSportPlans?userId="+userId+"&date="+time;//本地
//        String url=""//服务器
            sportCondition.setUrl(url);
            sportCondition.setNum(projectNum);
            int count = sportConditionService.add(sportCondition);
            if(count>0){
                result = "true"+url;
            }else{
                result = "false"+url;
            }
        }
        return result;
    }

    @PostMapping(value = "/delSportConditionById")
    @ResponseBody
    public String delSportConditionById(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        String date = request.getParameter("date");
        int userId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SportPlan sportPlan = new SportPlan();
        sportPlan.setPlanDate(date);
        sportPlan.setUserId(userId);
        int count = sportConditionService.deleteSportConditionById(id);
        //删除此运动状态下下包含的运动项目
        int num = sportPlanService.deleteSportPlanByUserIdAndDate(sportPlan);
        if(count>0 && num>0){
            return "true";
        }
        return "false";
    }

}
