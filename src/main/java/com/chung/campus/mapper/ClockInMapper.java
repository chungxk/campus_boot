package com.chung.campus.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chung.campus.entity.ClockIn;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClockInMapper extends BaseMapper<ClockIn> {
    List<ClockIn> getAll(@Param("date") String date, @Param("secondary") String secondary, @Param("condition") String condition, @Param("tem") String tem);

    List<ClockIn> queryByToday(@Param("psnId") Long psnId, @Param("createDate") String createDate);

    void updateClockIn(@Param("psnId") Long psnId, @Param("temperature") String temperature, @Param("healthCode") Integer healthCode, @Param("travelConditions") Integer travelConditions, @Param("currentLocation") String currentLocation, @Param("createDate") String createDate);

    void setNewClockIn(@Param("psnId") Long psnId, @Param("temperature") String temperature, @Param("healthCode") Integer healthCode, @Param("travelConditions") Integer travelConditions, @Param("currentLocation") String currentLocation, @Param("createDate") String createDate);

    void deleteClockInById(@Param("psnId") Long psnId, @Param("createDate") String createDate);

    ClockIn queryTodayClockIn(@Param("psnId") Long psnId, @Param("createDate") String today);
}
