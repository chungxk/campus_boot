package com.chung.campus.controller;


import com.chung.campus.entity.Admin;
import com.chung.campus.entity.Msg;
import com.chung.campus.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

//管理员登录功能
@Controller
public class LoginController {

    @Autowired
    private AdminService adminService;

    /**
     *
     * @param admin 前端返回来的数据使用Admin类封装
     * @param session 用来保存管理员id和管理员身份
     * @return 返回给前端的状态码
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Msg Login(Admin admin, HttpSession session){
        Admin queryAdmin = adminService.queryPassForUsername(admin.getUsername());

        //查询用户名不存在
        if(queryAdmin == null){
            return Msg.fail().add("failCode", 200);
        }

        //第一次登录则需要修改密码
        if(queryAdmin.getStatus() == 0 && (admin.getPassword()).equals(queryAdmin.getPassword())){
            session.setAttribute("username", admin.getUsername());
            session.setAttribute("identity", queryAdmin.getIdentity());
            return Msg.success().add("successCode", 101);
        }

        //登陆成功之后跳转到系统页面
        if(admin.getPassword().equals(queryAdmin.getPassword())){
            session.setAttribute("username", admin.getUsername());
            session.setAttribute("identity", queryAdmin.getIdentity());
            return Msg.success().add("successCode", 100);
        }

        //登录失败则重定向回登录页面
        return Msg.fail().add("failCode", 201);
    }

}
