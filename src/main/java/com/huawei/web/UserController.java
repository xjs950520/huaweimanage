package com.huawei.web;

import com.huawei.bean.User;
import com.huawei.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/userController")
public class UserController {

    @Autowired
    private UserService userService;

    private final SimpleDateFormat sdf=new SimpleDateFormat("yyyy.MM.dd HH:mm");

    @GetMapping(value = "/findAll")
    public String findAll(HttpServletRequest request){
        int pageno = Integer.parseInt(request.getParameter("pageno")==null || request.getParameter("pageno").equals("")?"1":request.getParameter("pageno"));
        int pageSize = 5;
        if(request.getParameter("pageSize")!=null){
            pageSize = Integer.parseInt(request.getParameter("pageSize"));
        }else if(request.getSession().getAttribute("p")!=null && request.getParameter("pageSize")==null){
            pageSize = (int) request.getSession().getAttribute("p");
        }
        List<User> bigList = userService.findAll();
        if(bigList!=null){
            int l=1;
            for(User user:bigList){
                user.setNumber(l);
                l++;
            }
            List<User> list = new ArrayList<User>();
            int count=bigList.size();
            request.setAttribute("count",count);
            //计算开始标识 = 页数大小 * （页码-1）
            int beginIndex = pageSize*(pageno - 1);
            //结束标识
            int endIndex = pageSize*pageno>bigList.size()?bigList.size():pageSize*pageno;
            for(int i=beginIndex;i<endIndex;i++){
                list.add(bigList.get(i));
            }
            for(User user:list){
                int number = user.getNumber();
                String name=user.getUserName();
                String sex=user.getSex();
                String age=user.getAge();
                String phone=user.getPhone();
            }
            request.getSession().setAttribute("list",list);
            int totalPage=0;
            double j = (double)count/pageSize;//计算共有多少页
            if(Math.round(j)!=j ){
                totalPage=(int)j+1;
            }else{
                totalPage = (int) j;
            }
            int currentPage=pageno;
            request.setAttribute("totalPage",totalPage);
            request.setAttribute("users",list);
            request.setAttribute("currentPage",currentPage);
            request.setAttribute("pageSize",pageSize);
            request.getSession().setAttribute("p", pageSize);
        }

       return "index";
    }
    @PostMapping(value = "/updateUserById")
    @ResponseBody
    public String  updateUserById(HttpServletRequest request){
        int userId = Integer.parseInt(request.getParameter("userId"));
        String userName = request.getParameter("userName");
        String sex = request.getParameter("sex");
        String age = request.getParameter("age");
        String phone = request.getParameter("phone");
        User user =new User();
        user.setUserId(userId);
        user.setUserName(userName);
        user.setSex(sex);
        user.setAge(age);
        user.setPhone(phone);
        int count = userService.updateByUserId(user);
        if(count>0){
            return "true";
        }
        return "false";
    }
    @PostMapping(value = "/delUserById")
    @ResponseBody
    public String delUserById(HttpServletRequest request){
        int userId = Integer.parseInt(request.getParameter("userId"));
        int count = userService.deleteByUserId(userId);
        if(count>0){
            return "true";
        }
        return "false";
    }
    @PostMapping(value = "/addUser")
    @ResponseBody
    public String addUser(HttpServletRequest request){
        String userName = request.getParameter("userName");
        String sex = request.getParameter("sex");
        String age = request.getParameter("age");
        String phone = request.getParameter("phone");
        String ct_date = sdf.format(new Date());
        User user =new User();
        user.setUserName(userName);
        user.setSex(sex);
        user.setAge(age);
        user.setPhone(phone);
        user.setCt_date(ct_date);
        int count = userService.add(user);
        if(count>0){
            return "true";
        }
        return "false";
    }
}
