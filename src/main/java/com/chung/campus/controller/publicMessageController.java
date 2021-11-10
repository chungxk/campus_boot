package com.chung.campus.controller;

import com.chung.campus.entity.Msg;
import com.chung.campus.entity.PublicMessage;
import com.chung.campus.service.PublicMessageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class publicMessageController {

    @Autowired
    private PublicMessageService publicMessageService;

    /**
     * 删除公告
     * @param map 前端传来的值
     * @return 返回状态码给前端
     */
    @ResponseBody
    @RequestMapping(value = "/delMessage", method = RequestMethod.POST)
    public Msg delMessage(@RequestBody Map<String, String> map){
        int code = publicMessageService.delMessage(map.get("messageId"));
        if(code == 0){
            return Msg.fail();
        }else{
            return Msg.success();
        }
    }

    /**
     * 管理员审核公告信息
     * @param idStr 前端传来的String类型的id
     * @param auditStr 前端传来的String类型的audit
     * @return 返回状态码给前端
     */
    @ResponseBody
    @RequestMapping(value = "/auditMessage", method = RequestMethod.POST)
    public Msg auditMessage(@RequestParam("id")String idStr, @RequestParam("auditCode")String auditStr){
        //获取当前时间
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String auditDate = sdf.format(date);

        Long id = Long.parseLong(idStr);
        Integer audit = Integer.parseInt(auditStr);

        int code = publicMessageService.auditMessageById(id, audit, auditDate);
        if(code == 0){
            return Msg.fail();
        }else{
            return Msg.success();
        }
    }

    /**
     *
     * @param map 前端传来的值
     * @return 返回状态码给前端
     */
    @ResponseBody
    @RequestMapping(value = "/publicMessUpdate", method = RequestMethod.POST)
    public Msg publicMessageUpdate(@RequestBody Map<String, String> map){
        //获取当前时间
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String createDate = sdf.format(date);

        //审核状态
        Integer audit = 0;

        int code = publicMessageService.updateMessage(Long.parseLong(map.get("id")), map.get("title"), map.get("context"), createDate, audit);
        if(code == 0){
            return Msg.fail();
        }else{
            return Msg.success();
        }
    }

    /**
     * 发布公告
     * @param map 前端传回来的数据
     * @param session 得到发布该公告的人员身份
     * @return 返回状态码给前端
     */
    @ResponseBody
    @RequestMapping(value = "/publicMess", method = RequestMethod.POST)
    public Msg publicMessage(@RequestBody Map<String, String> map, HttpSession session){
        //获取当前时间
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String createDate = sdf.format(date);

        //获取当前用户
        Object identity = session.getAttribute("identity");
        if(identity == null){
            return Msg.fail();
        }
        String authorIdStr = String.valueOf(identity);
        Long authorId = Long.parseLong(authorIdStr);

        //审核状态
        Integer audit = 0;

        int code = publicMessageService.insertMessage(map.get("title"), map.get("context"), createDate, audit, authorId);
        if(code == 0){
            return Msg.fail();
        }else{
            return Msg.success();
        }

    }

    /**
     * 按id值回显发布信息到模块窗
     * @param id 按照id查询
     * @return 返回json字符串
     */
    @ResponseBody
    @RequestMapping(value = "/message/{id}", method = RequestMethod.GET)
    public Msg getStuById(@PathVariable("id") String id){
        PublicMessage message = publicMessageService.getMessageById(id);
        return Msg.success().add("message", message);
    }

    /**
     * 得到学生全部打卡信息，默认是无条件查询
     * @param pn 开始页数
     * @return 返回json数组
     */
    @ResponseBody
    @RequestMapping("/message")
    public Msg getMessagesWithJson(@RequestParam(value = "pn", defaultValue = "1")Integer pn){
        PageHelper.startPage(pn, 40);
        List<PublicMessage> Message = publicMessageService.getAll();
        PageInfo page = new PageInfo(Message, 5);
        return Msg.success().add("pageInfo", page);
    }
}
