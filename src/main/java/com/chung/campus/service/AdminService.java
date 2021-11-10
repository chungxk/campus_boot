package com.chung.campus.service;


import com.chung.campus.entity.Admin;

public interface AdminService {

    Admin queryPassForUsername(String username);

    void updatePassByUsername(String username, String password);
}
