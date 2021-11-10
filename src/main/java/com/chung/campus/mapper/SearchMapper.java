package com.chung.campus.mapper;


import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SearchMapper {
    List queryByCondition(String date, String select);
}
