package com.chung.campus.controller;


import com.chung.campus.entity.ClockIn;
import com.chung.campus.entity.Msg;
import com.chung.campus.service.TravelClockInService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class TravelClockInsController {

    @Autowired
    private TravelClockInService travelClockInService;

    /**
     * 删除学生每日打卡记录
     * @param map 封装前端传来的数据
     * @return 返回个前端的状态码
     */
    @ResponseBody
    @RequestMapping(value = "/delTravelClockIn", method = RequestMethod.POST)
    public Msg deleteTravel(@RequestBody Map<String, String> map){
        travelClockInService.deleteClockInById(map.get("id"), map.get("createDate"));
        return Msg.success();
    }

    /**
     *
     * @param pn 开始的页数
     * @param date 日期查询
     * @param secondary 二级学院查询
     * @param position 位置查询
     * @return 返回给前端的状态码和查询信息
     */
    @ResponseBody
    @RequestMapping("/travelClockIns")
    public Msg travelClockIns(@RequestParam(value = "pn", defaultValue = "1")Integer pn, @RequestParam("date")String date, @RequestParam("secondary")String secondary, @RequestParam("position")String position){
        //如果非空 加上%进行模糊查询
        if(!("".equals(date)))  date = "%" + date + "%";
        if(!("".equals(position))) position = "%" + position + "%";

        PageHelper.startPage(pn, 40);
        List<ClockIn> clockIns = travelClockInService.getAll(date, secondary, position);
        PageInfo page = new PageInfo(clockIns, 10);
        return Msg.success().add("pageInfo", page);
    }


}
