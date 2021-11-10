package com.chung.campus.controller;


import com.chung.campus.entity.ClockIn;
import com.chung.campus.entity.Msg;
import com.chung.campus.entity.Student;
import com.chung.campus.service.ClockInService;
import com.chung.campus.service.StudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//学生打开信息显示
@Controller
public class ClockInController {

    @Autowired
    private ClockInService clockInService;
    @Autowired
    private StudentService studentService;

    /**
     * 删除学生每日打卡记录
     * @param map 封装前端传来的数据
     * @return 返回个前端的状态码
     */
    @ResponseBody
    @RequestMapping(value = "/delDayClockIn", method = RequestMethod.POST)
    public Msg deleteClockIn(@RequestBody Map<String, String> map){
        clockInService.deleteClockInById(Long.parseLong(map.get("psnId")), map.get("createDate"));
        return Msg.success();
    }

    /**
     * 按id值回显学生信息到模块窗
     * @param id 按照id查询
     * @return 返回json字符串
     */
    @ResponseBody
    @RequestMapping(value = "/stu/{id}", method = RequestMethod.GET)
    public Msg getStuById(@PathVariable("id") String id){
        Student student = studentService.getStuById(id);
        return Msg.success().add("stu", student);
    }

    /**
     * 得到学生全部打卡信息，默认是无条件查询
     * @param pn 开始页数
     * @param date 日期筛选
     * @param secondary 二级学院筛选
     * @return 返回json数组
     */
    @ResponseBody
    @RequestMapping("/clockIns")
    public Msg getStusWithJson(@RequestParam(value = "pn", defaultValue = "1")Integer pn, @RequestParam("date")String date, @RequestParam("secondary")String secondary, @RequestParam("condition")String condition, @RequestParam("tem")String tem){
        PageHelper.startPage(pn, 40);
        List<ClockIn> clockIns = clockInService.getAll(date, secondary, condition, tem);
        PageInfo page = new PageInfo(clockIns, 5);
        return Msg.success().add("pageInfo", page);
    }

}
