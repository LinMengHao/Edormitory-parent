package com.lmh.dormitoryService.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BuildQuery {

    @ApiModelProperty("楼名")
    private String name;

    @ApiModelProperty("楼地址")
    private String address;
}
