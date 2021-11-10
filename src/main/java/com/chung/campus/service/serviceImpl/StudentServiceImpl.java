package com.chung.campus.service.serviceImpl;


import com.chung.campus.entity.Student;
import com.chung.campus.mapper.StudentMapper;
import com.chung.campus.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> getAll(String name, String id, String secondary, String status) {
        return studentMapper.getAll(name, id, secondary, status);
    }

    @Override
    public Student getStuById(String id) {
        return studentMapper.getStuById(id);
    }

    @Override
    public int updatePass(String username, String password) {
        return studentMapper.updatePass(username, password);
    }

    @Override
    public Integer updateAll(String username, String name, String dormitory, Integer year, String gender, Integer secondary, String stuClass, String teacher, String phone) {
        return studentMapper.updateAll(username, name, dormitory, year, gender, secondary, stuClass, teacher, phone);
    }

    @Override
    public void deleteStuById(String id) {
        studentMapper.deleteStuById(id);
    }

    @Override
    public Integer batchAddCount(String startUsername, String endUsername) {
        Long start = Long.parseLong(startUsername);
        Long end = Long.parseLong(endUsername);
        int item = 0;
        for(Long i = start; i <= end; i++){
            String username = String.valueOf(i);
            if("".equals(studentMapper.getStuById(username)) || studentMapper.getStuById(username) == null) {
                studentMapper.batchAddCount(username);
                item++;
            }

        }
        return item;
    }

    @Override
    public void deleteClockInById(String id) {
        studentMapper.deleteClockInById(id);
    }

    @Override
    public Integer updateAllWx(String username, String name, String dormitory, int schoolYear, Integer secondary, String stuClass, String teacher, String phone) {
        return studentMapper.updateAllWx(username, name, dormitory, schoolYear, secondary, stuClass, teacher, phone);
    }
}
