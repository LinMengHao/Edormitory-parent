package com.lmh.scoreService.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author lmh
 * @since 2020-12-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Score对象", description="")
public class Score implements Serializable {

private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty("得分")
    private Double score;

    @ApiModelProperty("得分宿舍ID")
    private Integer dormitoryId;

    @ApiModelProperty("修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date modifiedTime;

    @ApiModelProperty("图片上传")
    private String img;

    @ApiModelProperty("评分者")
    private String author;

    @ApiModelProperty("评分者电话")
    private String phoneNum;

    @ApiModelProperty("版本")
    @Version
    private Integer version;

    @ApiModelProperty("得分描述")
    private String description;

    @ApiModelProperty("状态0未发布，1发布")
    private Integer status;

}
