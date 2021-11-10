package com.chung.campus.service;


import com.chung.campus.entity.ClockIn;

import java.util.List;

public interface ClockInService {
    List<ClockIn> getAll(String date, String secondary, String condition, String tem);

    void setClockIn(Long psnId, String temperature, Integer healthCode, Integer travelConditions, String currentLocation, String createDate);

    void deleteClockInById(Long id, String createDate);

    ClockIn queryTodayClockIn(Long psnId, String today);
}
