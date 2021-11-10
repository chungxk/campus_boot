package com.chung.campus.controller;

import com.chung.campus.entity.Msg;
import com.chung.campus.entity.SecondaryName;
import com.chung.campus.service.SecondaryNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;
//二级学院下拉框
@Controller
public class SecondaryNameController {

    @Autowired
    private SecondaryNameService secondaryNameService;

    /**
     * 二级学院下拉框显示信息
     * @return 返回相关的二级学院名字
     */
    @ResponseBody
    @RequestMapping(value = "/secondary", method = RequestMethod.GET)
    public Msg selectDate(){
        List<SecondaryName> all = secondaryNameService.getAll();

        return Msg.success().add("info", all);
    }

}
