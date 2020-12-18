package com.legends.process.engine.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class MyProcessReq implements Serializable {

    @ApiModelProperty(value="流程标题")
    private String title;

    @ApiModelProperty(value="流程ID")
    private String instanceId;

    @ApiModelProperty(value="流程分类")
    private String category;

    @ApiModelProperty(value="流程开始时间")
    private Date startTime;

    @ApiModelProperty(value="流程结束时间")
    private Date endTime;
}
