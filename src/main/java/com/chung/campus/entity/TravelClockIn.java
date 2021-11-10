package com.chung.campus.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class TravelClockIn {
    private Long id;
    private String username;
    private String dest;
    private String purpose;
    private String phone;
    private String startDate;
    private String endDate;
    private String createDate;
    private Student student;

}
