package com.chung.campus.controller;


import com.chung.campus.entity.AddCount;
import com.chung.campus.entity.Msg;
import com.chung.campus.entity.Student;
import com.chung.campus.service.StudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 删除学生每日打卡记录
     * @param map 封装前端传来的数据
     * @return 返回个前端的状态码
     */
    @ResponseBody
    @RequestMapping(value = "/delStu", method = RequestMethod.POST)
    public Msg deleteStu(@RequestBody Map<String, String> map){
        studentService.deleteClockInById(map.get("id"));
        return Msg.success();
    }

    /**
     * 更新学生信息
     * @param map 封装前端传来的json
     * @return 返回状态码给前端
     */
    @ResponseBody
    @RequestMapping(value = "/updateStu", method = RequestMethod.POST)
    public Msg updateStu(@RequestBody Map<String, String> map){
        //根据id更新学生信息
        Integer code = studentService.updateAll(map.get("username"), map.get("name"), map.get("dormitory"), Integer.parseInt(map.get("schoolYear")), map.get("gender"), Integer.parseInt(map.get("secondaryCollege")), map.get("stuClass"), map.get("teacher"), map.get("phone"));

        //返回转态码  code=0则为插入失败 code!=0时则为插入成功
        if(code == 0){
            return Msg.fail();
        }else{
            return Msg.success();
        }
    }

    @ResponseBody
    @RequestMapping(value = "/addStu", method = RequestMethod.PUT)
    public Msg updateEmp(AddCount addCount){
        Integer successItem = studentService.batchAddCount(addCount.getStartUsername(), addCount.getEndUsername());

        return Msg.success().add("successItem", successItem);
    }

    @ResponseBody
    @RequestMapping(value = "/stu/{id}", method = RequestMethod.DELETE)
    public Msg deleteEmp(@PathVariable("id") String id){
        studentService.deleteStuById(id);
        return Msg.success();
    }

    @ResponseBody
    @RequestMapping("/stus")
    public Msg getAll(@RequestParam(value = "pn", defaultValue = "1")Integer pn, @RequestParam("name")String name, @RequestParam("id")String id, @RequestParam("secondary")String secondary, @RequestParam("status")String status){
        //数据为非空时加上%进行模糊查询
        if(!("".equals(name))) name = "%" + name + "%";
        if(!("".equals(id))) id = "%" + id + "%";

        PageHelper.startPage(pn, 50);
        List<Student> stus = studentService.getAll(name, id, secondary, status);
        PageInfo page = new PageInfo(stus, 5);
        return Msg.success().add("pageInfo", page);
    }
}
