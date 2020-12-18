package com.legends.process.engine.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MyProcessVO implements Serializable {
    @ApiModelProperty(value="发起流程id")
    private String id;
    @ApiModelProperty(value="流程名称")
    private String procName;
    @ApiModelProperty(value="流程标题")
    private String title;

    @ApiModelProperty(value="流程状态")
    private String state;

    @ApiModelProperty(value="流程分类")
    private String category;

    @ApiModelProperty(value="当前节点名称")
    private String taskName;

    @ApiModelProperty(value="当前审批人")
    private String assignee;
    @ApiModelProperty(value="租户ID")
    private String tenantId;
    @ApiModelProperty(value="流程发起者")
    private String startUserId;
    @ApiModelProperty(value="流程开始时间")
    private Date startTime;
    @ApiModelProperty(value="流程结束时间")
    private Date endTime;

    @ApiModelProperty(value="流程定义key")
    private String procDefKey;

    @ApiModelProperty(value="流程定义ID")
    private String processDefId;

    @ApiModelProperty(value="流程实例ID")
    private String processInstId;

    @ApiModelProperty(value="任务定义key")
    private String taskDefKey;

    @ApiModelProperty(value="任务ID")
    private String taskId;


}
