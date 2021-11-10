package com.chung.campus.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chung.campus.entity.SecondaryName;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SecondaryNameMapper extends BaseMapper<SecondaryName> {
    List<SecondaryName> getAll();
}
