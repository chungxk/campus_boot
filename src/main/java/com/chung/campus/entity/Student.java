package com.chung.campus.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Integer id;
    private Long username;
    private String password;
    private String name;
    private String dormitory;
    private Integer schoolYear;
    private Integer gender;
    private Integer status;
    private String phone;
    private String teacher;
    private Integer secondaryCollege;
    private String stuClass;
    private SecondaryName secondaryName;

}
