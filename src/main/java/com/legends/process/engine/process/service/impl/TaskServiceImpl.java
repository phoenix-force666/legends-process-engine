package com.legends.process.engine.process.service.impl;

import com.alibaba.fastjson.JSON;
import com.legends.process.engine.process.service.ITaskService;
import com.legends.process.engine.process.vo.MyProcessReqVO;
import com.legends.process.engine.process.vo.MyProcessRespVO;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.rest.dto.task.TaskDto;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class TaskServiceImpl implements ITaskService {

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
    public List<MyProcessRespVO> myProcess(MyProcessReqVO myProcessReqVO) {

        //初始化结果集
        List<MyProcessRespVO> respVOList = new ArrayList<MyProcessRespVO>();


        //查询我提交的流程
        List<HistoricProcessInstance> list  = historyService.createHistoricProcessInstanceQuery()
                .startedBy("admin")
//                .variableValueLike("title",paramVO.getTitle())
//                .processInstanceId(paramVO.getInstanceId())
//                .startedAfter(null == paramVO.getStart() ? null : DtmStringFormat.strYmdToDate(paramVO.getStart()))
//                .startedBefore(null == paramVO.getEnd() ? null : DtmStringFormat.strYmdToDate(paramVO.getEnd()))
                .orderByProcessInstanceStartTime()
                .desc()
                .listPage(0,10);

        list.forEach(historicProcessInstance -> {
            MyProcessRespVO myProcessRespVO=new MyProcessRespVO();

            myProcessRespVO.setId(historicProcessInstance.getId());
            myProcessRespVO.setProcName(historicProcessInstance.getProcessDefinitionName());
            myProcessRespVO.setTitle(historicProcessInstance.getProcessDefinitionName());
            myProcessRespVO.setStartUserId(historicProcessInstance.getStartUserId());
            myProcessRespVO.setState(historicProcessInstance.getState());
            myProcessRespVO.setProcDefKey(historicProcessInstance.getProcessDefinitionKey());
            myProcessRespVO.setTenantId(historicProcessInstance.getTenantId());
            myProcessRespVO.setStartTime(historicProcessInstance.getStartTime());
            myProcessRespVO.setEndTime(historicProcessInstance.getEndTime());

            respVOList.add(myProcessRespVO);
        });

        return respVOList;
    }


    /**
     * 我的申请
     * @param pn
     * @param ps
     * @return
     * @throws IOException
     */
//    public PageResultVO processApplyList(@RequestBody MyProcessParamVO paramVO, @PathVariable Integer pn, @PathVariable Integer ps) throws IOException, ParseException {
//        //通过userId获取中文名
////		String userId = SecurityUtils.getCurrentUserLogin();
//        String userId ="admin";
//        //初始化流程清单
//        List<HistoricProcessInstance> list = null;
////		if(null == paramVO.getTitle() || paramVO.getTitle().isEmpty()){
////			paramVO.setTitle("%");
////		}else {
////			paramVO.setTitle("%" + paramVO.getTitle() + "%");
////		}
//        //初始化结果集
//        List<ProcessMyCommonVO> dataList = new ArrayList<ProcessMyCommonVO>();
//        //如果全不空
//        list = historyService.createHistoricProcessInstanceQuery()
//                .startedBy(userId)
//                .variableValueLike("title",paramVO.getTitle())
//                .processInstanceId(paramVO.getInstanceId())
////                .startedAfter(null == paramVO.getStart() ? null : DtmStringFormat.strYmdToDate(paramVO.getStart()))
////                .startedBefore(null == paramVO.getEnd() ? null : DtmStringFormat.strYmdToDate(paramVO.getEnd()))
//                .orderByProcessInstanceStartTime()
//                .desc()
//                .listPage(pn * ps,ps);
//        //查询总条数
//        long count = historyService.createHistoricProcessInstanceQuery()
//                .startedBy(userId)
//                .variableValueLike("title",paramVO.getTitle())
//                .processInstanceId(paramVO.getInstanceId())
////                .startedAfter(null == paramVO.getStart() ? null : DtmStringFormat.strYmdToDate(paramVO.getStart()))
////                .startedBefore(null == paramVO.getEnd() ? null : DtmStringFormat.strYmdToDate(paramVO.getEnd()))
//                .count();
//        for(HistoricProcessInstance historic : list){
//            //初始化
//            ProcessMyCommonVO processVO = new ProcessMyCommonVO(historic.getId(),
//                    historyService.createHistoricVariableInstanceQuery()
//                            .processInstanceId(historic.getId())
//                            .variableName("appName")
//                            .singleResult()
//                            .getValue()
//                            .toString(),
////                    AttendanceUtil.resolveTypeByBusinessKey(historic.getBusinessKey()),
//                    historic.getBusinessKey(),
//                    historyService.createHistoricVariableInstanceQuery().processInstanceId(historic.getId()).variableName("instanceFormType").singleResult().getValue().toString(),
//                    historyService.createHistoricVariableInstanceQuery().processInstanceId(historic.getId()).variableName("title").singleResult().getValue().toString(),
//                    historic.getStartUserId(),
//                    sendGet(UrlConstant.GET_ZHNAME_BY_UID+historic.getStartUserId(),null),
////                    DtmStringFormat.dateTimeToStrYmdHms(historic.getStartTime()),
//                    null
//            );
//            //设置状态
//            if("completed".equals(historic.getState().toLowerCase())){
//                processVO.setWfState("completed");
//                dataList.add(processVO);
//            }
//            if("active".equals(historic.getState().toLowerCase())){
////                //查询当前任务
////                List<Task> reTask = taskService.createTaskQuery()
////                        .taskDefinitionKey("Task_reject")
////                        .processInstanceId(historic.getId())
////                        .list();
////                if(0 == reTask.size()){
////                    //查询服务
////                    List<Task> tasks = taskService.createTaskQuery()
////                            .processInstanceId(historic.getId())
////                            .list();
////                    processVO.setWfState("active");
////                    String ass = "";
////                    for(Task ts : tasks){
////                        ass += sendGet(UrlConstant.GET_ZHNAME_BY_UID+ts.getAssignee(),null) +",";
////                    }
////                    //设置当前审批人
////                    processVO.setAssignee(ass.substring(0,ass.length() - 1));
////                    dataList.add(processVO);
//                }else {
////                    for(Task ts : reTask){
////                        String operate = runtimeService.createVariableInstanceQuery().processInstanceIdIn(historic.getId()).variableName("operate").singleResult().getValue().toString();
////                        if("reject".equals(operate)){
////                            processVO.setWfState("rejected");
////                            //设置当前审批人
////                            processVO.setAssignee(sendGet(UrlConstant.GET_ZHNAME_BY_UID+ts.getAssignee(),null));
////                            //获取驳回原因
////                            HistoricVariableInstance hVars = historyService.createHistoricVariableInstanceQuery()
////                                    .processInstanceId(historic.getId())
////                                    .variableName("opinion")
////                                    .singleResult();
////                            processVO.setOpinion(null == hVars.getValue() ? "" : hVars.getValue().toString());
////                        }else {
////                            processVO.setWfState("cancel");
////                        }
////                        dataList.add(processVO);
////                    }
////                }
//            }
//        }
//        return new PageResultVO(GlobalReturnCode.SUCCESS_CODE, "SUCCESS",ps,pn,(int)count,dataList);
//    }



}
