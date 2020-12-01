package com.lmh.manageService.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class StudentQuery {
    @ApiModelProperty(value = "学号")
    private String studentId;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "状态  0表示在校1表示外出")
    private Integer status;

    @ApiModelProperty(value = "电话号码")
    private String phoneNum;

    @ApiModelProperty(value = "宿舍Id")
    private Integer dormitoryId;
}
