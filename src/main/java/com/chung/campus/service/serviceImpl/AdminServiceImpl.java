package com.chung.campus.service.serviceImpl;


import com.chung.campus.entity.Admin;
import com.chung.campus.mapper.AdminMapper;
import com.chung.campus.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin queryPassForUsername(String username) {
        return adminMapper.queryPassForUsername(username);
    }

    @Override
    public void updatePassByUsername(String username, String password) {
        adminMapper.updatePassByUsername(username, password);
    }


}
