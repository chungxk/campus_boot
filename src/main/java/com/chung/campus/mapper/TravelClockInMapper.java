package com.chung.campus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chung.campus.entity.ClockIn;
import com.chung.campus.entity.TravelClockIn;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TravelClockInMapper extends BaseMapper<TravelClockIn> {
    Integer insertClockIn(@Param("username") String username, @Param("dest") String dest, @Param("purpose") String purpose, @Param("phone") String phone, @Param("startDate") String startDate, @Param("endDate") String endDate);

    List<ClockIn> getAll(@Param("date") String date, @Param("secondary") String secondary, @Param("position") String position);

    void deleteClockInById(@Param("id") String id, @Param("createDate") String createDate);
}
