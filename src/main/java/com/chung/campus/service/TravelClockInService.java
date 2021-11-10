package com.chung.campus.service;

import com.chung.campus.entity.ClockIn;

import java.util.List;

public interface TravelClockInService {
    Integer insertClockIn(String username, String dest, String purpose, String phone, String startDate, String endDate);

    List<ClockIn> getAll(String date, String secondary, String position);

    void deleteClockInById(String id, String createDate);
}
