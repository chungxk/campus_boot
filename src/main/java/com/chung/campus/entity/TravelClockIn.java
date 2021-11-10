package com.chung.campus.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
    @TableField(fill = FieldFill.INSERT)
    private String createDate;
    private Student student;
    /*@TableLogic(value = "0", delval = "1") //逻辑删除
    private Integer isDel;*/

}
