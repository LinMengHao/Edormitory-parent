package com.lmh.dormitoryService.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DormitoryQuery {
    @ApiModelProperty("楼字Id")
    private Integer buildId;

    @ApiModelProperty("楼层")
    private Integer storey;

    @ApiModelProperty("最大入住数")
    private Integer maxNum;

    @ApiModelProperty("现有人数")
    private Integer nowNum;

    @ApiModelProperty("房间号")
    private String roomNum;

    @ApiModelProperty("状态 0完好1需维修")
    private Integer status;
}
