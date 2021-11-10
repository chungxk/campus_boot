package com.chung.campus.service.serviceImpl;


import com.chung.campus.entity.ClockIn;
import com.chung.campus.mapper.TravelClockInMapper;
import com.chung.campus.service.TravelClockInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravelClockInServiceImpl implements TravelClockInService {

    @Autowired
    private TravelClockInMapper travelClockInMapper;

    @Override
    public Integer insertClockIn(String username, String dest, String purpose, String phone, String startDate, String endDate) {
        return travelClockInMapper.insertClockIn(username, dest, purpose, phone, startDate, endDate);
    }

    @Override
    public List<ClockIn> getAll(String date, String secondary, String position) {
        return travelClockInMapper.getAll(date, secondary, position);
    }

    @Override
    public void deleteClockInById(String id, String createDate) {
        travelClockInMapper.deleteClockInById(id, createDate);
    }
}
