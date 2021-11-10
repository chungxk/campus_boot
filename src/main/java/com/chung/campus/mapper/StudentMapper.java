package com.chung.campus.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chung.campus.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentMapper extends BaseMapper<Student> {

    List<Student> getAll(@Param("name") String name, @Param("id") String id, @Param("secondary") String secondary, @Param("status") String status);

    Student getStuById(String username);

    int updatePass(@Param("username") String username, @Param("password") String password);

    Integer updateAll(@Param("username") String username, @Param("name") String name, @Param("dormitory") String dormitory, @Param("year") Integer year, @Param("gender") String gender, @Param("secondary") Integer secondary, @Param("stuClass") String stuClass, @Param("teacher") String teacher, @Param("phone") String phone);

    void deleteStuById(String id);


    void batchAddCount(String username);

    void deleteClockInById(String id);

    Integer updateAllWx(@Param("username") String username, @Param("name") String name, @Param("dormitory") String dormitory, @Param("year") Integer year, @Param("secondary") Integer secondary, @Param("stuClass") String stuClass, @Param("teacher") String teacher, @Param("phone") String phone);
}
