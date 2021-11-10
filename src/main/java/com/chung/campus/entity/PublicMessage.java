package com.chung.campus.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicMessage {
    private Long id;
    private String title;
    private String context;
    private String createDate;
    private Integer audit;
    private String auditDate;
    private Long authorId;
    private SecondaryName secondaryName;
    private Long hot;

}
