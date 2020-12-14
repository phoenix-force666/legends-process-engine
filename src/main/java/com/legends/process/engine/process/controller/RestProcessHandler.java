package com.legends.process.engine.process.controller;

import com.legends.process.engine.process.vo.CommonProcessRequest;
import io.swagger.annotations.ApiOperation;
import org.camunda.bpm.engine.rest.dto.task.TaskDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/rest/process")
public interface RestProcessHandler {


	@ApiOperation(value = "流程提交", notes = "流程提交")
	@RequestMapping(value = "/start", method = RequestMethod.POST)
	public List<TaskDto> start(@RequestBody CommonProcessRequest commonProcessRequest, HttpServletRequest request) throws Exception;

}
