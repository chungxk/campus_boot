package com.chung.campus.service.serviceImpl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chung.campus.entity.ClockIn;
import com.chung.campus.mapper.ClockInMapper;
import com.chung.campus.service.ClockInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClockInServiceImpl implements ClockInService {

    @Autowired
    private ClockInMapper clockInMapper;

    @Override
    public List<ClockIn> getAll(String date, String secondary, String condition, String tem) {
        return clockInMapper.getAll(date, secondary, condition, tem);
    }

    @Override
    public void setClockIn(Long psnId, String temperature,Integer healthCode, Integer travelConditions, String currentLocation, String createDate) {
        List<ClockIn> isExist = clockInMapper.queryByToday(psnId, createDate);
        System.out.println(isExist != null && isExist.size() != 0);
        if(isExist != null && isExist.size() != 0){
            clockInMapper.updateClockIn(psnId, temperature, healthCode, travelConditions, currentLocation, createDate);
        }else{
            clockInMapper.setNewClockIn(psnId, temperature, healthCode, travelConditions, currentLocation, createDate);
        }
    }

    @Override
    public void deleteClockInById(Long id, String createDate) {
        clockInMapper.deleteClockInById(id, createDate);
    }

    @Override
    public ClockIn queryTodayClockIn(Long perId, String today) {
        return clockInMapper.queryTodayClockIn(perId, today);
    }

}
