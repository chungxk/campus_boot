package com.chung.campus.controller;


import com.chung.campus.entity.ClockIn;
import com.chung.campus.entity.Msg;
import com.chung.campus.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/*条件查询*/
@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Msg search(@RequestBody String param){
        //得到的JSON字符串格式 裂开&
        String[] arrayParam = param.split("&");

        //裂开 =
        String[] arrayDate = arrayParam[0].split("=");
        String[] arraySelect = arrayParam[1].split("=");

        //0为数组名 1位数组值
        String date = arrayDate[1];
        String select = arraySelect[1];

        List<ClockIn> clockInList = searchService.queryByCondition(date, select);

        return Msg.success().add("info", clockInList);
    }


}
