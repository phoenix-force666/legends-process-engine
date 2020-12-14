package com.legends.process.engine.process.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.camunda.bpm.engine.rest.dto.runtime.StartProcessInstanceDto;

/**
 * 流程提交参数对象
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value="CommonProcessRequest-流程提交参数对象")
@Data
public class CommonProcessRequest {
	@ApiModelProperty(value="流程定义ID")
	private String	processDefId;
	
	@ApiModelProperty(value="流程定义Key")
	private String	processDefKey;

	@ApiModelProperty(value="启动者")
	private String	starter;
	@ApiModelProperty(value="流程标题")
	private	String	title;
	
	private StartProcessInstanceDto startProcessInstanceDto;
   
	public static String REJECT_TO_START ="1";
	public static String REJECT_TO_LAST ="2";
	public static String REJECT_TO_TARGET ="3";
   
	public static String WITHDRAW_TO_START ="1";
	public static String WITHDRAW_TO_TARGET ="2";

}
