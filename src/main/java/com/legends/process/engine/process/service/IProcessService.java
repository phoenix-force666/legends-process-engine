package com.legends.process.engine.process.service;

import com.legends.process.engine.process.vo.PscCommonProcessRequest;
import com.legends.process.engine.process.vo.PscCommonTaskRequest;
import org.camunda.bpm.engine.history.HistoricTaskInstance;
import org.camunda.bpm.engine.rest.dto.task.TaskDto;
import org.jvnet.hk2.annotations.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Service
public interface IProcessService {

	/**
	 * 流程初始化
	 * @param pscCommonProcessRequest
	 * @return
	 * @throws Exception
	 */
	public List<TaskDto> init(PscCommonProcessRequest pscCommonProcessRequest) throws Exception;

	/**
	 * 流程提交
	 * @param pscCommonProcessRequest
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<TaskDto> start(PscCommonProcessRequest pscCommonProcessRequest, HttpServletRequest request) throws Exception;

	/**
	 * 根据流程定义Key查找历史任务记录
	 * @param processDefKey
	 * @return
	 * @throws Exception
	 */
	public List<HistoricTaskInstance> getHisTasks(String processDefKey)throws Exception;

	/**
	 * 根据流程定义Key查找运行任务
	 * @param processDefKey
	 * @return
	 * @throws Exception
	 */
	public List<TaskDto> getTaskIds(String processDefKey)throws Exception;

	/**
	 * 流程审批
	 * @param pscCommonTaskRequest
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<TaskDto>  approve(PscCommonTaskRequest pscCommonTaskRequest, HttpServletRequest request) throws Exception;

	/**
	 * 流程撤回
	 * @param pscCommonTaskRequest
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<TaskDto> undo(PscCommonTaskRequest pscCommonTaskRequest, HttpServletRequest request) throws Exception;


	/**
	 * 流程驳回，驳回类型，1：起草节点，2：上一节点，3：目标节点
	 * @param comReq
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<TaskDto> rollback(PscCommonTaskRequest comReq, HttpServletRequest request) throws Exception;

	/**
	 * 流程终止、作废，审批人执行为终止、提交人执行草稿状态的为作废
	 * @param pscCommonTaskRequest
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<TaskDto> terminate(PscCommonTaskRequest pscCommonTaskRequest, HttpServletRequest request) throws Exception;

	/**
	 * 流程重启，被审批通过、终止、作废的流程可重新启动到指定节点
	 * @param pscCommonTaskRequest
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<TaskDto> restart(PscCommonTaskRequest pscCommonTaskRequest, HttpServletRequest request) throws Exception;

	/**
	 * 流程转办，将当期任务转交给其他人处理
	 * @param pscCommonTaskRequest
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<TaskDto> turnOver(PscCommonTaskRequest pscCommonTaskRequest, HttpServletRequest request) throws Exception;


	/**
	 * 流程跳转，跳转类型,1:往前跳转，2：往回跳转
	 * @param pscCommonTaskRequest
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<TaskDto> jump(PscCommonTaskRequest pscCommonTaskRequest, HttpServletRequest request) throws Exception;
	
	
	
	/* * 
	 * 
	 * @ApiOperation(value = "流程跟踪", notes =
	 * "获取流程跟踪图Url，根据流程实例Id和当前用户Id获取流程跟踪图Url")
	 * 
	 * @RequestMapping(value =
	 * "/simpleGetProcessDiagramUrl/{processInstId}/{uid}", method =
	 * RequestMethod.GET) public String simpleGetProcessDiagramUrl(
	 * 
	 * @ApiParam(name = "processInstId", value = "流程实例Id", required =
	 * true) @PathVariable String processInstId,
	 * 
	 * @ApiParam(name = "uid", value = "用户Id", required = true) @PathVariable
	 * String uid,HttpServletRequest request) throws Exception;
	 * 
	 * @ApiOperation(value = "流程撤回", notes = "流程撤回,如果该任务允许收回，收回已办任务")
	 * 
	 * @RequestMapping(value = "/simpleUndoProcess", method =
	 * RequestMethod.POST) public TaskInstance simpleUndoProcess(@RequestBody
	 * PscCommonTaskRequest pscCommonTaskRequest ) throws Exception;
	 * 
	 * @ApiOperation(value = "流程驳回", notes = "流程驳回,回退任务到目标节点")
	 * 
	 * @RequestMapping(value = "/simpleRollbackProcess", method =
	 * RequestMethod.POST) public TaskInstance
	 * simpleRollbackProcess(@RequestBody PscCommonTaskRequest
	 * pscCommonTaskRequest ) throws Exception;
	 * 
	 * @ApiOperation(value = "流程转办", notes =
	 * "将待办任务委派给其他人处理，不会产生新任务，该操作更改任务执行人信息")
	 * 
	 * @RequestMapping(value = "/simpleTurnOverProcess", method =
	 * RequestMethod.POST) public boolean simpleTurnOverProcess(@RequestBody
	 * PscCommonTaskRequest pscCommonTaskRequest ) throws Exception;
	 * 
	 * @ApiOperation(value = "流程传阅", notes = "流程传阅，创建传阅任务实例")
	 * 
	 * @RequestMapping(value = "/simpleUserCCTask", method = RequestMethod.POST)
	 * public TaskInstance simpleUserCCTask(@RequestBody PscCommonTaskRequest
	 * pscCommonTaskRequest ) throws Exception;
	 * 
	 * @ApiOperation(value = "流程查阅", notes = "流程查阅，如果该任务尚未阅读，标记任务读取时间")
	 * 
	 * @RequestMapping(value = "/simpleReadProcess", method =
	 * RequestMethod.POST) public void simpleReadProcess(@RequestBody
	 * PscCommonTaskRequest pscCommonTaskRequest ) throws Exception;
	 * 
	 * @ApiOperation(value = "流程复活", notes = "流程复活，通过id流程复活，重新激活已结束的流程实例")
	 * 
	 * @RequestMapping(value = "/simpleReactivateProcess", method =
	 * RequestMethod.POST) public TaskInstance
	 * simpleReactivateProcess(@RequestBody PscCommonTaskRequest
	 * pscCommonTaskRequest ) throws Exception;
	 * 
	 * @ApiOperation(value = "流程重启", notes =
	 * "流程复活，通过id重置流程到第一个节点，将任务创建给流程启动者，等同于由启动者撤销重办业务（适用于开始事件后是UserTask的人工流程）。 撤销操作将删除令牌、 所有待办已办任务等流程数据， 如果给定了reStartReason值 ，将撤销原因初始化成审批记录。如果该流程启动了子流程一并将子流程删除"
	 * )
	 * 
	 * @RequestMapping(value = "/simpleRestartProcess", method =
	 * RequestMethod.POST) public TaskInstance simpleRestartProcess(@RequestBody
	 * PscCommonTaskRequest pscCommonTaskRequest ) throws Exception;
	 * 
	 * @ApiOperation(value = "流程终止", notes = "流程终止，通过id终止一个流程实例")
	 * 
	 * @RequestMapping(value = "/simpleTerminateProcess", method =
	 * RequestMethod.POST) public boolean simpleTerminateProcess(@RequestBody
	 * PscCommonTaskRequest pscCommonTaskRequest ) throws Exception;
	 * 
	 * @ApiOperation(value = "流程删除", notes =
	 * "流程删除，通过id删除流程实例，与流程实例(及子流程实例嵌套)相关的业务数据、控制数据、变量等全部删除")
	 * 
	 * @RequestMapping(value = "/simpleTerminateProcess", method =
	 * RequestMethod.POST) public boolean simpleDeleteProcess(@RequestBody
	 * PscCommonTaskRequest pscCommonTaskRequest ) throws Exception;
	 */
}
