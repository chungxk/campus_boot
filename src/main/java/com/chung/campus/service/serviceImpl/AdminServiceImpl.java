package com.chung.campus.service.serviceImpl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.chung.campus.entity.Admin;
import com.chung.campus.mapper.AdminMapper;
import com.chung.campus.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin queryPassForUsername(String username) {
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        return adminMapper.selectOne(wrapper);
    }

    @Override
    @Transactional
    public void updatePassByUsername(String username, String password) {
        UpdateWrapper<Admin> wrapper = new UpdateWrapper<>();
        wrapper.eq("username", username);
        wrapper.set("password", password);
        wrapper.set("update_date", LocalDateTime.now());
        adminMapper.update(null, wrapper);
    }


}
