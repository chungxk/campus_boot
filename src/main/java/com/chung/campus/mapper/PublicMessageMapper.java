package com.chung.campus.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chung.campus.entity.PublicMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PublicMessageMapper extends BaseMapper<PublicMessage> {
    List<PublicMessage> getAll();

    PublicMessage getMessageById(String id);

    int insertMessage(@Param("title") String title, @Param("context") String context, @Param("createDate") String createDate, @Param("audit") Integer audit, @Param("authorId") Long authorId);

    int updateMessage(@Param("id") Long id, @Param("title") String title, @Param("context") String context, @Param("createDate") String createDate, @Param("audit") Integer audit);

    int auditMessageById(@Param("id") Long id, @Param("audit") Integer audit, @Param("auditDate") String auditDate);

    void updateHot(@Param("id") String id, @Param("hot") Long hot);

    int delMessage(String messageId);
}
