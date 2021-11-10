package com.chung.campus.service.serviceImpl;


import com.chung.campus.entity.PublicMessage;
import com.chung.campus.mapper.PublicMessageMapper;
import com.chung.campus.service.PublicMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicMessageImpl implements PublicMessageService {
    @Autowired
    private PublicMessageMapper publicMessageMapper;

    @Override
    public List<PublicMessage> getAll() {
        return publicMessageMapper.getAll();
    }

    @Override
    public PublicMessage getMessageById(String id) {
        return publicMessageMapper.getMessageById(id);
    }

    @Override
    public int insertMessage(String title, String context, String createDate, Integer audit, Long authorId) {
        return publicMessageMapper.insertMessage(title, context, createDate, audit, authorId);
    }

    @Override
    public int updateMessage(Long id, String title, String context, String createDate, Integer audit) {
        return publicMessageMapper.updateMessage(id, title, context, createDate, audit);
    }

    @Override
    public int auditMessageById(Long id, Integer audit, String auditDate) {
        return publicMessageMapper.auditMessageById(id, audit, auditDate);
    }

    @Override
    public void updateHot(String messageId, Long hot) {
        publicMessageMapper.updateHot(messageId, hot);
    }

    @Override
    public int delMessage(String messageId) {
        return publicMessageMapper.delMessage(messageId);
    }
}
