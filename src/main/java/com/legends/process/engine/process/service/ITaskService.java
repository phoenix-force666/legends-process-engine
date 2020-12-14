package com.legends.process.engine.process.service;

import com.legends.process.engine.process.vo.MyProcessReqVO;
import com.legends.process.engine.process.vo.MyProcessRespVO;
import org.camunda.bpm.engine.rest.dto.task.TaskDto;

import java.util.List;

public interface ITaskService {

    public List<TaskDto> page();

    /**
     * 我的申请
     * @param myProcessReqVO
     * @return
     */
    public List<MyProcessRespVO> myProcess(MyProcessReqVO myProcessReqVO);
}
