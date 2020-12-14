package com.legends.process.engine.process.controller;

import com.alibaba.fastjson.JSON;
import com.legends.process.engine.base.controller.BaseController;
import com.legends.process.engine.base.page.PageDomain;
import com.legends.process.engine.base.page.TableDataInfo;
import com.legends.process.engine.base.page.TableSupport;
import com.legends.process.engine.process.service.ITaskService;
import com.legends.process.engine.process.vo.MyProcessReqVO;
import com.legends.process.engine.process.vo.MyProcessRespVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("myProcess")
@Slf4j
public class MyProcessController extends BaseController {

	@Autowired
	private ITaskService taskService;

	@Autowired
	private HistoryService historyService;

	@ApiOperation(value = "我的申请", notes = "我的申请")
	@GetMapping("/list")
	public TableDataInfo myProcess(MyProcessReqVO myProcessReqVO) throws Exception{
		PageDomain pageDomain=TableSupport.getPageDomain();
		log.info(JSON.toJSONString(pageDomain));
		List<MyProcessRespVO> list=taskService.myProcess(myProcessReqVO);
		return getDataTable(list);
	}


//	/**
//	 * 我的申请
//	 * @param pn
//	 * @param ps
//	 * @return
//	 * @throws IOException
//	 */
//	@PostMapping(value = "/process/ap/{pn}/{ps}")
//	public PageResultVO processApplyList(@RequestBody MyProcessParamVO paramVO, @PathVariable Integer pn, @PathVariable Integer ps) throws IOException, ParseException {
//		//通过userId获取中文名
////		String userId = SecurityUtils.getCurrentUserLogin();
//		String userId ="admin";
//		//初始化流程清单
//		List<HistoricProcessInstance> list = null;
////		if(null == paramVO.getTitle() || paramVO.getTitle().isEmpty()){
////			paramVO.setTitle("%");
////		}else {
////			paramVO.setTitle("%" + paramVO.getTitle() + "%");
////		}
//		//初始化结果集
//		List<ProcessMyCommonVO> dataList = new ArrayList<ProcessMyCommonVO>();
//		//如果全不空
//		list = historyService.createHistoricProcessInstanceQuery()
//				.startedBy(userId)
//				.variableValueLike("title",paramVO.getTitle())
//				.processInstanceId(paramVO.getInstanceId())
//				.startedAfter(null == paramVO.getStart() ? null : DtmStringFormat.strYmdToDate(paramVO.getStart()))
//				.startedBefore(null == paramVO.getEnd() ? null : DtmStringFormat.strYmdToDate(paramVO.getEnd()))
//				.orderByProcessInstanceStartTime()
//				.desc()
//				.listPage(pn * ps,ps);
//		//查询总条数
//		long count = historyService.createHistoricProcessInstanceQuery()
//				.startedBy(userId)
//				.variableValueLike("title",paramVO.getTitle())
//				.processInstanceId(paramVO.getInstanceId())
//				.startedAfter(null == paramVO.getStart() ? null : DtmStringFormat.strYmdToDate(paramVO.getStart()))
//				.startedBefore(null == paramVO.getEnd() ? null : DtmStringFormat.strYmdToDate(paramVO.getEnd())).count();
//		for(HistoricProcessInstance historic : list){
//			//初始化
//			ProcessMyCommonVO processVO = new ProcessMyCommonVO(historic.getId(),
//					historyService.createHistoricVariableInstanceQuery().processInstanceId(historic.getId()).variableName("appName").singleResult().getValue().toString(),
//					AttendanceUtil.resolveTypeByBusinessKey(historic.getBusinessKey()),
//					historic.getBusinessKey(),
//					historyService.createHistoricVariableInstanceQuery().processInstanceId(historic.getId()).variableName("instanceFormType").singleResult().getValue().toString(),
//					historyService.createHistoricVariableInstanceQuery().processInstanceId(historic.getId()).variableName("title").singleResult().getValue().toString(),
//					historic.getStartUserId(),
//					sendGet(UrlConstant.GET_ZHNAME_BY_UID+historic.getStartUserId(),null),
//					DtmStringFormat.dateTimeToStrYmdHms(historic.getStartTime()),
//					null
//			);
//			//设置状态
//			if("completed".equals(historic.getState().toLowerCase())){
//				processVO.setWfState("completed");
//				dataList.add(processVO);
//			}
//			if("active".equals(historic.getState().toLowerCase())){
//				//查询当前任务
//				List<Task> reTask = taskService.createTaskQuery()
//						.taskDefinitionKey("Task_reject")
//						.processInstanceId(historic.getId())
//						.list();
//				if(0 == reTask.size()){
//					//查询服务
//					List<Task> tasks = taskService.createTaskQuery()
//							.processInstanceId(historic.getId())
//							.list();
//					processVO.setWfState("active");
//					String ass = "";
//					for(Task ts : tasks){
//						ass += sendGet(UrlConstant.GET_ZHNAME_BY_UID+ts.getAssignee(),null) +",";
//					}
//					//设置当前审批人
//					processVO.setAssignee(ass.substring(0,ass.length() - 1));
//					dataList.add(processVO);
//				}else {
//					for(Task ts : reTask){
//						String operate = runtimeService.createVariableInstanceQuery().processInstanceIdIn(historic.getId()).variableName("operate").singleResult().getValue().toString();
//						if("reject".equals(operate)){
//							processVO.setWfState("rejected");
//							//设置当前审批人
//							processVO.setAssignee(sendGet(UrlConstant.GET_ZHNAME_BY_UID+ts.getAssignee(),null));
//							//获取驳回原因
//							HistoricVariableInstance hVars = historyService.createHistoricVariableInstanceQuery()
//									.processInstanceId(historic.getId())
//									.variableName("opinion")
//									.singleResult();
//							processVO.setOpinion(null == hVars.getValue() ? "" : hVars.getValue().toString());
//						}else {
//							processVO.setWfState("cancel");
//						}
//						dataList.add(processVO);
//					}
//				}
//			}
//		}
//		return new PageResultVO(GlobalReturnCode.SUCCESS_CODE, "SUCCESS",ps,pn,(int)count,dataList);
//	}

}
