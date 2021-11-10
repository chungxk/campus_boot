package com.chung.campus.service.serviceImpl;


import com.chung.campus.mapper.SearchMapper;
import com.chung.campus.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchMapper searchMapper;

    public List queryByCondition(String date, String select) {
        return searchMapper.queryByCondition(date, select);
    }
}
