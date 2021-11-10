package com.chung.campus.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    private String id;
    private String username;
    private String password;
    private String confirmPass;
    private Integer status;
    private Integer secondaryColName;
    private Integer identity;

}
