package com.legends.process.engine.service;

import com.legends.process.engine.vo.MyProcessReq;
import com.legends.process.engine.vo.MyProcessVO;
import org.camunda.bpm.engine.rest.dto.task.TaskDto;

import java.util.List;

public interface IMyProcessService {

    public List<TaskDto> page();

    /**
     * 我的申请
     * @param myProcessReq
     * @return
     */
    public List<MyProcessVO> applyList(String userName,MyProcessReq myProcessReq);

    /**
     * 我的待审批
     * @param myProcessReq
     * @return
     */
    public List<MyProcessVO> approveList(String userName,MyProcessReq myProcessReq);
}
