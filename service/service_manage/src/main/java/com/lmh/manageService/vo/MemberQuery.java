package com.lmh.manageService.vo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class MemberQuery {
    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("电话号码")
    private String phoneNum;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("楼字ID")
    private Integer buildId;

    @ApiModelProperty("状态 0在岗1休息")
    private Integer status;

    @ApiModelProperty("出生日期")
    private Date birthDay;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}
