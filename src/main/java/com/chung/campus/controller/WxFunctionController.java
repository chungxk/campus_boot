package com.chung.campus.controller;


import com.chung.campus.entity.ClockIn;
import com.chung.campus.entity.Msg;
import com.chung.campus.entity.PublicMessage;
import com.chung.campus.entity.Student;
import com.chung.campus.service.ClockInService;
import com.chung.campus.service.PublicMessageService;
import com.chung.campus.service.StudentService;
import com.chung.campus.service.TravelClockInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

//微信小程序springMVC
@Controller
public class WxFunctionController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private ClockInService clockInService;
    @Autowired
    private TravelClockInService travelClockInService;
    @Autowired
    private PublicMessageService publicMessageService;

    /**
     * 用于小程序首页当天是否已经打卡确认
     * @param map 前端传来的id值
     * @return 返回状态码给前端判断是否已经打开
     */
    @RequestMapping(value = "/WxGetTodayClockIn", method = RequestMethod.POST)
    @ResponseBody
    public Msg getTodayClockIn(@RequestBody Map<String,String> map){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(date);
        String idStr = map.get("id");
        Long perId = Long.parseLong(idStr);
        ClockIn todayClockIn = clockInService.queryTodayClockIn(perId, today);
        System.out.println(todayClockIn);
        if(todayClockIn != null){
            return Msg.success().add("check", 1);
        }
        return Msg.success().add("check", 0);
    }

    /**
     * 小程序端根据id取对应的公告，并增加阅读量hot的值
     * @param map 前端传来的message的id值
     * @return 返回状态码和message给前端
     */
    @RequestMapping(value = "/WxGetMessageById", method = RequestMethod.POST)
    @ResponseBody
    public Msg getMessageById(@RequestBody Map<String,String> map){
        String messageId = map.get("messageId");
        PublicMessage messageByHot = publicMessageService.getMessageById(messageId);
        Long hot = messageByHot.getHot() + 1;
        publicMessageService.updateHot(messageId, hot);
        PublicMessage message = publicMessageService.getMessageById(messageId);
        return Msg.success().add("message", message);
    }

    /**
     * 微信小程序公告栏信息列表
     * @return 返回成功信息和 公告list集合
     */
    @RequestMapping(value = "/WxGetPublicMessage", method = RequestMethod.GET)
    @ResponseBody
    public Msg getPublicMessage(){
        List<PublicMessage> messages = publicMessageService.getAll();
        return Msg.success().add("messages", messages);
    }

    /**
     * 修改学生个人信息
     * @param map 接收前端传来的json串
     * @return 返回前端的状态码
     */
    @RequestMapping(value = "/WxModifyStuInfo", method = RequestMethod.POST)
    @ResponseBody
    public Msg modifyStuInfo(@RequestBody Map<String, String> map){
        Integer code = 1;
        //根据学生id修改学生信息
        if(map.get("gender") == null) {
            code = studentService.updateAllWx(map.get("username"), map.get("name"), map.get("dormitory"), Integer.parseInt(map.get("schoolYear")) + 2016, Integer.parseInt(map.get("secondary")) + 1, map.get("stuClass"), map.get("teacher"), map.get("phone"));
        }else{
            code = studentService.updateAll(map.get("username"), map.get("name"), map.get("dormitory"), Integer.parseInt(map.get("schoolYear")) + 2016, map.get("gender"), Integer.parseInt(map.get("secondary")) + 1, map.get("stuClass"), map.get("teacher"), map.get("phone"));
        }
        //返回转态码  code=0则为插入失败 code!=0时则为插入成功
        if(code != 0){
            return Msg.success();
        }else{
            return Msg.fail();
        }
    }

    /**
     * 出行报备
     * @param travelInfo 由于map集合不能转换成String类型，只能使用这样的方法来解析json串
     * @return 返回前端的状态码
     */
    @RequestMapping(value = "/WxTravelClockIn", method = RequestMethod.POST)
    @ResponseBody
    public Msg travelClockIn(@RequestBody String travelInfo){
        //分解返回的json串
        String[] kv = travelInfo.split(",");
        String[] value1 = kv[0].split(":");
        String[] value2 = kv[1].split(":");
        String[] value3 = kv[2].split(":");
        String[] value4 = kv[3].split(":");
        String[] value5 = kv[4].split(":");
        String[] value6 = kv[5].split(":");
        String[] value7 = kv[6].split(":");
        String[] value8 = kv[7].split(":");
        String[] value9 = kv[8].split(":");
        String[] value10 = kv[9].split(":");

        String username = value1[1].replace("\"", "").replace("{", "").replace("}", "");
        String dest = value2[1].replace("\"", "").replace("{", "").replace("}", "");
        String purpose = value3[1].replace("\"", "").replace("{", "").replace("}", "");
        String phone = value4[1].replace("\"", "").replace("{", "").replace("}", "");
        String startDateYear = value5[2].replace("\"", "").replace("{", "").replace("}", "");
        String startDateMonth = value6[1].replace("\"", "").replace("{", "").replace("}", "");
        String startDateDay = value7[1].replace("\"", "").replace("{", "").replace("}", "");
        String endDateYear = value8[2].replace("\"", "").replace("{", "").replace("}", "");
        String endDateMonth = value9[1].replace("\"", "").replace("{", "").replace("}", "");
        String endDateDay = value10[1].replace("\"", "").replace("{", "").replace("}", "");

        int startYear = Integer.parseInt(String.valueOf(startDateYear));
        int endYear = Integer.parseInt(String.valueOf(endDateYear));
        int startMonth = Integer.parseInt(String.valueOf(startDateMonth));
        int endMonth = Integer.parseInt(String.valueOf(endDateMonth));
        int startDay = Integer.parseInt(String.valueOf(startDateDay));
        int endDay = Integer.parseInt(String.valueOf(endDateDay));

        //判断结束日期是否小于开始日期，如果是则返回失败码
        if(endYear < startYear){
            return Msg.fail().add("failCode", 201);
        }
        if(endYear == startYear && endMonth < startMonth){
            return Msg.fail().add("failCode", 201);
        }
        if(endYear == startYear && endMonth == startMonth && endDay < startDay){
            return Msg.fail().add("failCode", 201);
        }

        String startDate = startDateYear + "-" + startDateMonth + "-" + startDateDay;
        String endDate = endDateYear + "-" + endDateMonth + "-" + endDateDay;

        //插入出行数据
        Integer code = travelClockInService.insertClockIn(username, dest, purpose, phone, startDate, endDate);

        //返回转态码  code=0则为插入失败 code!=0时则为插入成功
        if(code == 0){
            return Msg.fail();
        }else{
            return Msg.success();
        }
    }

    /**
     * 我的页面显示学生基本信息
     * @param map 封装前端传来的json串
     * @return 返回前端的状态码
     */
    @RequestMapping("/WxGetStuInfo")
    @ResponseBody
    public Msg getStuInfo(@RequestBody Map<String, String> map){
        Student stu = studentService.getStuById(map.get("username"));
        return Msg.success().add("stuInfo", stu);
    }

    /**
     * 小程序端修改密码
     * @param map 前端传来的数据
     * @return 返回状态码给前端
     */
    @RequestMapping(value = "/WxModifyPass", produces="text/html;charset=UTF-8")
    @ResponseBody
    public String modifyPass(@RequestBody Map<String, String> map){
        //判断输入的密码和确认密码是否一致，不一致则返回失败状态码
        if(!map.get("password").equals(map.get("confirmPass"))){
            return "{\"flag\":\"0\"}";
        }

        //根据id值查询该学生，如果为空则不能修改密码，则返回失败状态码
        Student stu = studentService.getStuById(map.get("username"));
        if(stu == null){
            return "{\"flag\":\"1\"}";
        }

        //取得该学生的是否为第一次登录的状态码
        Integer status = stu.getStatus();

        //根据学生id修改密码
        int code = studentService.updatePass(map.get("username"), map.get("password"));

        //如果code=0则修改失败，code!=0则修改成功；status=0则为第一次登录，跳转到填写个人信息页面，status!=0则跳转到功能页面
        if(code == 0){
            return "{\"flag\":\"3\"}";
        }else{
            if(status == 0){
                return "{\"flag\":\"200\"}";
            }else{
                return "{\"flag\":\"201\"}";
            }
        }
    }
//* @param map 封装前端传来的json串
    /**
     * 学生每日打卡

     * @return 返回前端的状态码
     */
    @RequestMapping(value = "/WxDayClockIn", produces="text/html;charset=UTF-8")
    @ResponseBody
    public String dayClockIn(@RequestBody Map map){//
        //创建当前时间
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String createDate = sdf.format(date);

        //插入打卡数据
        clockInService.setClockIn(Long.parseLong(map.get("psnId").toString()), map.get("temperature").toString(), Integer.parseInt(map.get("healthCode").toString()), Integer.parseInt(map.get("travelConditions").toString()), map.get("currentLocation").toString(), createDate);

        //返回状态码
        return "{\"flag\":\"1\"}";
    }

    /**
     * 小程序登录功能
     * @param map 封装前端传来的json串
     * @return 返回前端的状态码
     */
    @RequestMapping(value = "/WxClockInByWechat",produces="text/html;charset=UTF-8")
    @ResponseBody
    public String Login(@RequestBody Map<String, String> map) {
        //按账号查询用户信息
        Student stu = studentService.getStuById(map.get("username"));

        //查询无此学生信息返回转态码
        if(stu == null){
            return "{\"flag\":\"0\"}";
        }

        //密码正确
        if(map.get("password").equals(stu.getPassword())){

            if(stu.getStatus() == 0){
                //若是第一次登录，跳转到修改页面
                return "{\"flag\":\"2\"}";
            }else{
                //若不是第一次登录，则跳到主页面
                return "{\"flag\":\"1\"}";
            }
        }else{
            //密码错误，不跳转
            return "{\"flag\":\"0\"}";
        }
    }
}
