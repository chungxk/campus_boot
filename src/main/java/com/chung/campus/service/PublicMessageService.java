package com.chung.campus.service;


import com.chung.campus.entity.PublicMessage;

import java.util.List;

public interface PublicMessageService {
    List<PublicMessage> getAll();

    PublicMessage getMessageById(String id);

    int insertMessage(String title, String context, String createDate, Integer audit, Long authorId);

    int updateMessage(Long id, String title, String context, String createDate, Integer audit);

    int auditMessageById(Long id, Integer audit, String auditDate);

    void updateHot(String messageId, Long hot);

    int delMessage(String messageId);
}
