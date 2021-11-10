package com.chung.campus.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ClockIn {
    private Integer id;
    private Long psnId;
    private String temperature;
    private Integer healthCode;
    private Integer travelConditions;
    private String currentLocation;
    private String createDate;
    private Student student;

}
