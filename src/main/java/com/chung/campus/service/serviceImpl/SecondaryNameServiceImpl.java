package com.chung.campus.service.serviceImpl;


import com.chung.campus.entity.SecondaryName;
import com.chung.campus.mapper.SecondaryNameMapper;
import com.chung.campus.service.SecondaryNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecondaryNameServiceImpl implements SecondaryNameService {

    @Autowired
    private SecondaryNameMapper selectDateMapper;

    public List<SecondaryName> getAll() {
        return selectDateMapper.getAll();
    }
}
