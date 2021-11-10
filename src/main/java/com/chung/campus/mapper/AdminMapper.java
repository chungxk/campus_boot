package com.chung.campus.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chung.campus.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
    Admin queryPassForUsername(String username);

    void updatePassByUsername(@Param("username") String username, @Param("password") String password);
}
