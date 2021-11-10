package com.chung.campus.controller;

import com.chung.campus.entity.Admin;
import com.chung.campus.entity.Msg;
import com.chung.campus.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

//管理员修改密码功能
@Controller
public class ModifyPassController {

    @Autowired
    private AdminService adminService;

    /**
     *  管理员修改密码
     * @param admin 前端返回来的数据使用Admin封装
     * @return 返回给前端的状态码
     */
    @ResponseBody
    @RequestMapping(value = "/modifyPass", method = RequestMethod.POST)
    public Msg modifyPassword(Admin admin){

        //判断密码和确认密码不一致，返回失败状态码
        if(!admin.getPassword().equals(admin.getConfirmPass())){
            return Msg.fail().add("failCode", 201);
        }

        //根据管理员id查询管理员信息
        Admin queryAdmin = adminService.queryPassForUsername(admin.getUsername());

        //判断原始密码和新密码是否一致，一致的则返回失败状态码
        if(queryAdmin.getPassword().equals(admin.getPassword())){
            return Msg.fail().add("failCode", 202);
        }

        //上述条件都不成立。则进行修改更新密码，返回成功状态码
        adminService.updatePassByUsername(admin.getUsername(), admin.getPassword());
        return Msg.success().add("successCode", 101);
    }

}
