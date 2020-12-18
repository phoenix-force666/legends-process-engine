package com.legends.process.engine.service.impl;

import com.alibaba.fastjson.JSON;
import com.legends.process.engine.service.IMyProcessService;
import com.legends.process.engine.vo.MyProcessReq;
import com.legends.process.engine.vo.MyProcessVO;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.rest.dto.task.TaskDto;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class MyProcessServcieImpl implements IMyProcessService {

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private RuntimeService runtimeService;

    @Override
    public List<TaskDto> page(){
        List<Task> tasks = taskService.createTaskQuery()
                            .orderByDueDate().asc()
                            .listPage(1,10);

        List<TaskDto> resultList = new ArrayList<TaskDto>();
        for (Task task : tasks) {
            TaskDto dto = new TaskDto();
            dto = TaskDto.fromEntity(task);
            resultList.add(dto);

            Map<String, Object> variables = taskService.getVariables(task.getId());
            if(Objects.nonNull(variables)){
                System.out.println(JSON.toJSONString(variables));
            }
        }


        List<HistoricProcessInstance> list = null;
//		if(null == paramVO.getTitle() || paramVO.getTitle().isEmpty()){
//			paramVO.setTitle("%");
//		}else {
//			paramVO.setTitle("%" + paramVO.getTitle() + "%");
//		}
        //初始化结果集
//        List<ProcessMyCommonVO> dataList = new ArrayList<ProcessMyCommonVO>();
        //如果全不空
        list = historyService.createHistoricProcessInstanceQuery()
//                .startedBy(userId)
//                .variableValueLike("title",paramVO.getTitle())
//                .processInstanceId(paramVO.getInstanceId())
//                .startedAfter(null == paramVO.getStart() ? null : DtmStringFormat.strYmdToDate(paramVO.getStart()))
//                .startedBefore(null == paramVO.getEnd() ? null : DtmStringFormat.strYmdToDate(paramVO.getEnd()))
                .orderByProcessInstanceStartTime()
                .desc()
                .listPage(0,10);

        list.forEach(history ->{
            System.out.println(historyService.createHistoricVariableInstanceQuery().processInstanceId(history.getId()).variableName("instanceFormType").singleResult().getValue().toString());
            System.out.println(historyService.createHistoricVariableInstanceQuery().processInstanceId(history.getId()).variableName("title").singleResult().getValue().toString());
        });



        return resultList;
    }

    /**
     * 我的申请
     * @param myProcessReqVO
     * @return
     */
    @Override
    public List<MyProcessVO> applyList(String userName,MyProcessReq myProcessReqVO) {

        //初始化结果集
        List<MyProcessVO> respVOList = new ArrayList<MyProcessVO>();

        //查询我提交的流程
        List<HistoricProcessInstance> list  = historyService.createHistoricProcessInstanceQuery()
                .startedBy(userName)
//                .variableValueLike("title",paramVO.getTitle())
//                .processInstanceId(paramVO.getInstanceId())
//                .startedAfter(null == paramVO.getStart() ? null : DtmStringFormat.strYmdToDate(paramVO.getStart()))
//                .startedBefore(null == paramVO.getEnd() ? null : DtmStringFormat.strYmdToDate(paramVO.getEnd()))
                .orderByProcessInstanceStartTime()
                .desc()
                .listPage(0,10);

        list.forEach(historicProcessInstance -> {
            MyProcessVO myProcessRespVO=new MyProcessVO();

            myProcessRespVO.setId(historicProcessInstance.getId());
            myProcessRespVO.setProcName(historicProcessInstance.getProcessDefinitionName());
            myProcessRespVO.setTitle(historicProcessInstance.getProcessDefinitionName());
            myProcessRespVO.setStartUserId(historicProcessInstance.getStartUserId());
            myProcessRespVO.setState(historicProcessInstance.getState());
            myProcessRespVO.setProcDefKey(historicProcessInstance.getProcessDefinitionKey());
            myProcessRespVO.setTenantId(historicProcessInstance.getTenantId());
            myProcessRespVO.setStartTime(historicProcessInstance.getStartTime());
            myProcessRespVO.setEndTime(historicProcessInstance.getEndTime());

            if("active".equals(historicProcessInstance.getState().toLowerCase())){
                //查询当前任务
                List<Task> reTask = taskService.createTaskQuery()
//                        .taskDefinitionKey("Task_reject")
                        .processInstanceId(historicProcessInstance.getId())
                        .list();
                if(0 == reTask.size()){
                    //查询服务
                    List<Task> tasks = taskService.createTaskQuery()
                            .processInstanceId(historicProcessInstance.getId())
                            .list();
//                    String ass = "";
//                    for(Task ts : tasks){
//                        ass += sendGet(UrlConstant.GET_ZHNAME_BY_UID+ts.getAssignee(),null) +",";
//                    }
//                    //设置当前审批人
//                    processVO.setAssignee(ass.substring(0,ass.length() - 1));
                    if(tasks.size()>0){
                        myProcessRespVO.setAssignee(tasks.get(0).getAssignee());
                        myProcessRespVO.setTaskName(tasks.get(0).getName());
                        myProcessRespVO.setTaskId(tasks.get(0).getId());
                        myProcessRespVO.setTaskDefKey(tasks.get(0).getTaskDefinitionKey());
                        myProcessRespVO.setProcessInstId(tasks.get(0).getProcessInstanceId());
                        myProcessRespVO.setProcessDefId(tasks.get(0).getProcessDefinitionId());
                    }
//
                }else {
                    for(Task ts : reTask){
                        myProcessRespVO.setTaskName(ts.getName());
                        myProcessRespVO.setAssignee(ts.getAssignee());
                        myProcessRespVO.setTaskId(ts.getId());
                        myProcessRespVO.setTaskDefKey(ts.getTaskDefinitionKey());
                        myProcessRespVO.setProcessInstId(ts.getProcessInstanceId());
                        myProcessRespVO.setProcessDefId(ts.getProcessDefinitionId());
//                        String operate = runtimeService.createVariableInstanceQuery()
//                                .processInstanceIdIn(historicProcessInstance.getId())
//                                .variableName("operate")
//                                .singleResult().getValue().toString();
//                        if("reject".equals(operate)){
//                            //设置当前审批人
////                            processVO.setAssignee(sendGet(UrlConstant.GET_ZHNAME_BY_UID+ts.getAssignee(),null));
//                            //获取驳回原因
//                            HistoricVariableInstance hVars = historyService.createHistoricVariableInstanceQuery()
//                                    .processInstanceId(historicProcessInstance.getId())
//                                    .variableName("opinion")
//                                    .singleResult();
//                            System.out.println(hVars.getValue().toString());
////                            processVO.setOpinion(null == hVars.getValue() ? "" : hVars.getValue().toString());
//                        }else {
////                            processVO.setWfState("cancel");
//                        }
                    }
                }
            }
            respVOList.add(myProcessRespVO);
        });

        return respVOList;
    }

    /**
     * 我的待审核
     * @param myProcessReq
     * @return
     */
    @Override
    public List<MyProcessVO> approveList(String userName,MyProcessReq myProcessReq) {
        List<Task> tasks=taskService.createTaskQuery()
                .taskAssignee(userName)
//                .taskCandidateGroup()//组
                .list();

        List<MyProcessVO> list=new ArrayList<MyProcessVO>();
        tasks.forEach( task -> {
            HistoricProcessInstance historicProcessInstances  = historyService.createHistoricProcessInstanceQuery()
                    .processInstanceId(task.getProcessInstanceId())
                    .singleResult();

            MyProcessVO myProcessVO = new MyProcessVO();
            myProcessVO.setId(task.getId());
            myProcessVO.setProcName(historicProcessInstances.getProcessDefinitionName());
            myProcessVO.setTitle(historicProcessInstances.getProcessDefinitionName());
            myProcessVO.setStartUserId(historicProcessInstances.getStartUserId());
            myProcessVO.setState(historicProcessInstances.getState());
            myProcessVO.setProcDefKey(task.getTaskDefinitionKey());
            myProcessVO.setTenantId(task.getTenantId());
            myProcessVO.setStartTime(task.getCreateTime());
            myProcessVO.setEndTime(historicProcessInstances.getEndTime());
            myProcessVO.setTaskName(task.getName());
            myProcessVO.setAssignee(task.getAssignee());
            myProcessVO.setTaskId(task.getId());
            myProcessVO.setTaskDefKey(task.getTaskDefinitionKey());
            myProcessVO.setProcessInstId(task.getProcessInstanceId());
            myProcessVO.setProcessDefId(task.getProcessDefinitionId());
            list.add(myProcessVO);
        });

        return list;
    }


}
