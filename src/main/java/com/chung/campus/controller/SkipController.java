package com.chung.campus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//页面跳转
@Controller
public class SkipController {



    /**
     *
     * @return 跳转信息信息页面
     */
    @RequestMapping("/publicMessage")
    public String skipTest(){
        return "publicMessage.html";
    }

    /**
     *
     * @return 跳转打卡信息页面
     */
    @RequestMapping("/clockIn")
    public String skipClockIn(){
        return "clockIn.html";
    }

    /**
     *
     * @return 跳转学生信息页面
     */
    @RequestMapping("/stuInfo")
    public String skipStuInfo(){
        return "stuInfo.html";
    }

    /**
     *
     * @return 跳转到学生出行报备信息页面
     */
    @RequestMapping("/travelReport")
    public String skipTravelReport(){
        return "travelReport.html";
    }

    /**
     *
     * @return 跳转到管理员修改密码界面
     */
    @RequestMapping("/modifyPass")
    public String skipModifyPass(){
        return "modifyPass.html";
    }

    /**
     *
     * @return 跳转到关于我们界面
     */
    @RequestMapping("/aboutUs")
    public String skipAboutUs(){
        return "aboutUs.html";
    }

    /**
     * 退出登录功能
     * @param request 域对象
     * @return 重定向回登录界面
     */
    @RequestMapping("/exitLogin")
    public String skipExitLogin(HttpServletRequest request){
        //获取域对象，退出登录把session里的管理员id和身份去掉
        HttpSession session = request.getSession();
        session.removeAttribute("username");
        session.removeAttribute("identity");
        return "redirect:/";
    }
}
