package com.chung.campus.service;


import com.chung.campus.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAll(String name, String id, String secondary, String status);

    Student getStuById(String id);

    int updatePass(String username, String password);

    Integer updateAll(String username, String name, String dormitory, Integer year, String gender, Integer secondary, String stuClass, String teacher, String phone);

    void deleteStuById(String id);

    Integer batchAddCount(String startUsername, String endUsername);

    void deleteClockInById(String id);

    Integer updateAllWx(String username, String name, String dormitory, int schoolYear, Integer secondary, String stuClass, String teacher, String phone);
}
