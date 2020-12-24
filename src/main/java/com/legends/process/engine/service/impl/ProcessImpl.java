package com.legends.process.engine.service.impl;

import com.alibaba.fastjson.JSON;
import com.legends.process.engine.form.domain.FormDataEntity;
import com.legends.process.engine.form.service.IFormDataService;
import com.legends.process.engine.service.IProcessService;
import com.legends.process.engine.vo.PscCommonProcessRequest;
import com.legends.process.engine.vo.PscCommonTaskRequest;
import com.ruoyi.common.core.constant.CacheConstants;
import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.engine.*;
import org.camunda.bpm.engine.history.HistoricActivityInstance;
import org.camunda.bpm.engine.history.HistoricTaskInstance;
import org.camunda.bpm.engine.impl.persistence.entity.TaskEntity;
import org.camunda.bpm.engine.rest.dto.task.TaskDto;
import org.camunda.bpm.engine.runtime.ActivityInstance;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.webapp.impl.security.auth.AuthenticationService;
import org.camunda.bpm.webapp.impl.security.auth.Authentications;
import org.camunda.bpm.webapp.impl.security.auth.UserAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author herion
 */
@Service
public class ProcessImpl implements IProcessService {
	private static final Logger logger = LoggerFactory.getLogger(ProcessImpl.class);

	@Autowired
	private RuntimeService runtimeService;


	@Autowired
	private TaskService taskService;

	@Autowired
	private HistoryService historyService;

	@Autowired
	private ProcessEngine processEngine;
	

	@Autowired
	private IdentityService identityService;

	@Autowired
	IFormDataService formDataService;

	/**
	 * 初始化流程
	 * @param pscCommonProcessRequest
	 * @return TaskDto
	 * @throws Exception
	 */
	@Override
	public List<TaskDto> init(PscCommonProcessRequest pscCommonProcessRequest) throws Exception {
		String processInstanceId = null;
		List<TaskDto> resultList = new ArrayList<TaskDto>(16);
		Map<String, Object> variables = new HashMap<String, Object>(16);

		identityService.setAuthenticatedUserId(pscCommonProcessRequest.getStarter());
		if(null != pscCommonProcessRequest.getVariables()){
			variables.putAll(pscCommonProcessRequest.getVariables());
		}

		// 流程初始化
		ProcessInstance processInstance = null;
		// 流程初始化
		if (StringUtils.isNotBlank(pscCommonProcessRequest.getProcessDefKey())) {
			processInstance = runtimeService.startProcessInstanceByKey(pscCommonProcessRequest.getProcessDefKey(),variables);
		} else {
			processInstance = runtimeService.startProcessInstanceById(pscCommonProcessRequest.getProcessDefId(),variables);
		}
		// 创建成功
		if (processInstance != null && StringUtils.isNotBlank(processInstance.getId())) {
			processInstanceId = processInstance.getId();
			resultList = simpleGetTasks(processInstanceId);
		} else {
			throw new Exception("创建流程实例失败：");
		}

		//保存表单数据
		FormDataEntity formDataEntity=new FormDataEntity();
		formDataEntity.setFormId(pscCommonProcessRequest.getFormId());
		formDataEntity.setId(processInstance.getId());
		formDataEntity.setData(pscCommonProcessRequest.getFormData());
		formDataService.save(formDataEntity);
		return resultList;
	}

	/**
	 * 启动流程
	 * @param pscCommonProcessRequest
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<TaskDto> start(PscCommonProcessRequest pscCommonProcessRequest, HttpServletRequest request) throws Exception {
		AuthenticationService authenticationService = new AuthenticationService();
		String engineName = processEngine.getName();
		UserAuthentication authentication = (UserAuthentication) authenticationService
				.createAuthenticate(engineName, pscCommonProcessRequest.getStarter(), null, null);

		logger.info("authentication--------->" + authentication.getName());
		Authentications.revalidateSession(request, authentication);
		identityService.setAuthenticatedUserId(authentication.getName());

		String processInstanceId = null;
		List<TaskDto> resultList = new ArrayList<TaskDto>();
		Map<String, Object> variables = new HashMap<String, Object>(16);
		// variables.put("assigneeList030", Arrays.asList("kermit", "demo"));
		// variables.put("assigneeList040", Arrays.asList("kermit", "demo"));
		variables.put("starter", pscCommonProcessRequest.getStarter());
		if(null == pscCommonProcessRequest ||Objects.isNull(pscCommonProcessRequest.getVariables())){
			variables.put("amount", "980");
			variables.put("school", "shanghai");
		}else{
			variables.putAll(pscCommonProcessRequest.getVariables());
		}

		ProcessInstance processInstance = null;
		// 流程初始化
		if (StringUtils.isNotBlank(pscCommonProcessRequest.getProcessDefKey())) {
			processInstance = runtimeService.startProcessInstanceByKey(pscCommonProcessRequest.getProcessDefKey(),variables);
		} else {
			processInstance = runtimeService.startProcessInstanceById(pscCommonProcessRequest.getProcessDefId(),variables);
		}
		// 创建成功
		if (processInstance != null && StringUtils.isNotBlank(processInstance.getId())) {
			processInstanceId = processInstance.getId();
			List<TaskDto> taskList = simpleGetTasks(processInstanceId);
			logger.info(JSON.toJSONString(taskList));
			if (taskList != null && taskList.size() == 1) {
				taskService.complete(taskList.get(0).getId(), variables);
				taskService.createComment(taskList.get(0).getId(), processInstanceId, "提交流程");
				resultList = simpleGetTasks(processInstanceId);
			} else {
				throw new Exception("获取提交任务失败：" + taskList.size());
			}
		} else {
			throw new Exception("创建流程实例失败：");
		}
		return resultList;
	}


	/**
	 * 根据流程定义Key查找历史任务记录
	 * @param processDefKey
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<HistoricTaskInstance> getHisTasks(String processDefKey) throws Exception {
		List<HistoricTaskInstance> resultList = new ArrayList<HistoricTaskInstance>();
		resultList = historyService.createHistoricTaskInstanceQuery().processDefinitionKey(processDefKey).list();
		return resultList;
	}

	/**
	 * 根据流程定义Key查找运行任务
	 * @param processDefKey
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<TaskDto> getTaskIds(String processDefKey) throws Exception {
		List<TaskDto> resultList = new ArrayList<TaskDto>();
		List<Task> taskList = taskService.createTaskQuery().processDefinitionKey(processDefKey).list();
		for (Task task : taskList) {
			TaskDto dto = new TaskDto();
			dto = TaskDto.fromEntity(task);
			resultList.add(dto);
		}
		return resultList;
	}


	/**
	 * 流程审批
	 * @param pscCommonTaskRequest
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<TaskDto> approve(PscCommonTaskRequest pscCommonTaskRequest, HttpServletRequest request) throws Exception {
		pscCommonTaskRequest.setUserId(request.getHeader(CacheConstants.DETAILS_USERNAME));

		AuthenticationService authenticationService = new AuthenticationService();
		String engineName = processEngine.getName();
		UserAuthentication authentication = (UserAuthentication) authenticationService.createAuthenticate(engineName,
				pscCommonTaskRequest.getUserId(), null, null);
		logger.info("authentication--------->" + authentication.getName());
		Authentications.revalidateSession(request, authentication);
		identityService.setAuthenticatedUserId(authentication.getName());
		List<TaskDto> taskList = new ArrayList<TaskDto>();
		Map<String, Object> variables = new HashMap<String, Object>();
		variables = pscCommonTaskRequest.getVariables();
		Map<String, Object> localVariables = new HashMap<String, Object>();
		localVariables = pscCommonTaskRequest.getLocalVariables();

		runtimeService.setVariables(pscCommonTaskRequest.getProcessInstId(), localVariables);
		if(StringUtils.isNoneBlank(pscCommonTaskRequest.getToActId())){
			taskService.complete(pscCommonTaskRequest.getTaskId(), variables);
			taskService.createComment(pscCommonTaskRequest.getTaskId(), pscCommonTaskRequest.getProcessInstId(), "重新提交流程");
			ActivityInstance tree = runtimeService.getActivityInstance(pscCommonTaskRequest.getProcessInstId());
			runtimeService
				.createProcessInstanceModification(pscCommonTaskRequest.getProcessInstId())
				.cancelActivityInstance(getInstanceIdForActivity(tree, tree.getActivityId()))
				.startBeforeActivity(pscCommonTaskRequest.getToActId())
				.execute();
		}else{
			taskService.createComment(pscCommonTaskRequest.getTaskId(), pscCommonTaskRequest.getProcessInstId(), "审批流程");
			taskService.complete(pscCommonTaskRequest.getTaskId(), variables);
			
		}
		
		taskList = simpleGetTasks(pscCommonTaskRequest.getProcessInstId());
		if (taskList != null && taskList.size() == 1) {
			taskService.setAssignee(taskList.get(0).getId(), pscCommonTaskRequest.getNextUserId());
		}
		taskList = simpleGetTasks(pscCommonTaskRequest.getProcessInstId());
		return taskList;
	}


	/**
	 * 流程撤回
	 * @param pscCommonTaskRequest
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<TaskDto> undo(PscCommonTaskRequest pscCommonTaskRequest, HttpServletRequest request)throws Exception {
		pscCommonTaskRequest.setUserId(request.getHeader(CacheConstants.DETAILS_USERNAME));
		AuthenticationService authenticationService = new AuthenticationService();
		String engineName = processEngine.getName();
		UserAuthentication authentication = (UserAuthentication) authenticationService.createAuthenticate(engineName,
				pscCommonTaskRequest.getUserId(), null, null);
		logger.info("authentication--------->" + authentication.getName());
		Authentications.revalidateSession(request, authentication);
		identityService.setAuthenticatedUserId(authentication.getName());
		List<TaskDto> taskList = new ArrayList<TaskDto>();

		ActivityInstance tree = runtimeService.getActivityInstance(pscCommonTaskRequest.getProcessInstId());
		taskService.createComment(pscCommonTaskRequest.getTaskId(), pscCommonTaskRequest.getProcessInstId(), "撤回流程");
		runtimeService.createProcessInstanceModification(pscCommonTaskRequest.getProcessInstId())
	      .cancelActivityInstance(getInstanceIdForActivity(tree, tree.getActivityId()))
	      .startBeforeActivity(pscCommonTaskRequest.getTaskDefKey())
	      .execute();
		
		taskList = simpleGetTasks(pscCommonTaskRequest.getProcessInstId());
//		if (taskList != null && taskList.size() == 1) {
//			taskService.setAssignee(taskList.get(0).getId(), pscCommonTaskRequest.getNextUserId());
//		}
//		taskList = simpleGetTasks(pscCommonTaskRequest.getProcessInstId());
		return taskList;
	}


	/**
	 * 流程驳回，驳回类型，1：起草节点，2：上一节点，3：目标节点
	 * @param pscCommonTaskRequest
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<TaskDto> rollback(PscCommonTaskRequest pscCommonTaskRequest, HttpServletRequest request) throws Exception {
		pscCommonTaskRequest.setUserId(request.getHeader(CacheConstants.DETAILS_USERNAME));
		String rejectType = pscCommonTaskRequest.getRejectType();
		if(StringUtils.isBlank(rejectType)){
			throw new Exception("驳回类型不能为空！");
		}
		AuthenticationService authenticationService = new AuthenticationService();
		String engineName = processEngine.getName();
		UserAuthentication authentication = (UserAuthentication) authenticationService.createAuthenticate(engineName,
				pscCommonTaskRequest.getUserId(), null, null);
		logger.info("authentication--------->" + authentication.getName());
		Authentications.revalidateSession(request, authentication);
		identityService.setAuthenticatedUserId(authentication.getName());
		List<TaskDto> taskList = new ArrayList<TaskDto>();

		ActivityInstance tree = runtimeService.getActivityInstance(pscCommonTaskRequest.getProcessInstId());
		if(rejectType.equals(PscCommonTaskRequest.REJECT_TO_START)){
			List<HistoricActivityInstance> resultList = historyService
					.createHistoricActivityInstanceQuery()
					.processInstanceId(pscCommonTaskRequest.getProcessInstId())
					.activityType("userTask")
					.finished()
					.orderByHistoricActivityInstanceEndTime()
					.asc()
					.list();
			if (resultList == null || resultList.size() <= 0) {
				throw new Exception("未找到发起节点");
			}
			pscCommonTaskRequest.setToActId(resultList.get(0).getActivityId());
		}else if(rejectType.equals(PscCommonTaskRequest.REJECT_TO_LAST)){
			List<HistoricActivityInstance> resultList = historyService
					.createHistoricActivityInstanceQuery()
					.processInstanceId(pscCommonTaskRequest.getProcessInstId())
					.activityType("userTask")
					.finished()
					.orderByHistoricActivityInstanceEndTime()
					.desc()
					.list();
			if (resultList == null || resultList.size() <= 0) {
				throw new Exception("未找到上一节点");
			}
			pscCommonTaskRequest.setToActId(resultList.get(0).getActivityId());
		}else if(rejectType.equals(PscCommonTaskRequest.REJECT_TO_TARGET)){
			if(StringUtils.isBlank(pscCommonTaskRequest.getToActId())){
				throw new Exception("指定目标节点不能为空！");
			}
		}else{
			throw new Exception("驳回类型值不对，三种类型  1：起草节点，2：上一节点，3：目标节点！");
		}
		
		taskService.createComment(pscCommonTaskRequest.getTaskId(), pscCommonTaskRequest.getProcessInstId(), "驳回流程");
		runtimeService
	      .createProcessInstanceModification(pscCommonTaskRequest.getProcessInstId())
	      .cancelActivityInstance(getInstanceIdForActivity(tree, pscCommonTaskRequest.getTaskDefKey()))
	      .startBeforeActivity(pscCommonTaskRequest.getToActId())
	      .execute();
		

		taskList = simpleGetTasks(pscCommonTaskRequest.getProcessInstId());
//		if (taskList != null && taskList.size() == 1) {
//			taskService.setAssignee(taskList.get(0).getId(), pscCommonTaskRequest.getNextUserId());
//		}
//		taskList = simpleGetTasks(pscCommonTaskRequest.getProcessInstId());
		return taskList;
	}

	/**
	 * 流程终止、作废，审批人执行为终止、提交人执行草稿状态的为作废
	 * @param pscCommonTaskRequest
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<TaskDto> terminate(PscCommonTaskRequest pscCommonTaskRequest, HttpServletRequest request)
			throws Exception {
		AuthenticationService authenticationService = new AuthenticationService();
		String engineName = processEngine.getName();
		UserAuthentication authentication = (UserAuthentication) authenticationService.createAuthenticate(engineName,
				pscCommonTaskRequest.getUserId(), null, null);
		logger.info("authentication--------->" + authentication.getName());
		Authentications.revalidateSession(request, authentication);
		identityService.setAuthenticatedUserId(authentication.getName());
		List<TaskDto> taskList = new ArrayList<TaskDto>();

		ActivityInstance tree = runtimeService.getActivityInstance(pscCommonTaskRequest.getProcessInstId());
		taskService.createComment(pscCommonTaskRequest.getTaskId(), pscCommonTaskRequest.getProcessInstId(), "终止、作废流程");
		runtimeService.deleteProcessInstance(pscCommonTaskRequest.getProcessInstId(),TaskEntity.DELETE_REASON_COMPLETED);
//		runtimeService
//	      .createProcessInstanceModification(pscCommonTaskRequest.getProcessInstId())
//	      .cancelActivityInstance(getInstanceIdForActivity(tree, pscCommonTaskRequest.getTaskDefKey()))
//	      .execute();
		taskList = simpleGetTasks(pscCommonTaskRequest.getProcessInstId());
//		if (taskList != null && taskList.size() == 1) {
//			taskService.setAssignee(taskList.get(0).getId(), pscCommonTaskRequest.getNextUserId());
//		}
//		taskList = simpleGetTasks(pscCommonTaskRequest.getProcessInstId());
		return taskList;
	}
	
	@Override
	public List<TaskDto> restart(PscCommonTaskRequest pscCommonTaskRequest, HttpServletRequest request)
			throws Exception {
		AuthenticationService authenticationService = new AuthenticationService();
		String engineName = processEngine.getName();
		UserAuthentication authentication = (UserAuthentication) authenticationService.createAuthenticate(engineName,
				pscCommonTaskRequest.getUserId(), null, null);
		logger.info("authentication--------->" + authentication.getName());
		Authentications.revalidateSession(request, authentication);
		identityService.setAuthenticatedUserId(authentication.getName());
		List<TaskDto> taskList = new ArrayList<TaskDto>();
		String processDefId = pscCommonTaskRequest.getProcessDefId();
		if(StringUtils.isBlank(processDefId)){
			processDefId = historyService
	    		.createHistoricProcessInstanceQuery()
	    		.processInstanceId(pscCommonTaskRequest.getProcessInstId())
	    		.singleResult().getProcessDefinitionId();
		}
		System.out.println("processDefId---->"+processDefId);
	    runtimeService.restartProcessInstances(processDefId)
		    .startBeforeActivity(pscCommonTaskRequest.getTaskDefKey())
		    .initialSetOfVariables()
		    .processInstanceIds(pscCommonTaskRequest.getProcessInstId())
		    .execute();
		
		/*HistoricProcessInstanceQuery hisProcessInstQuery = historyService
		        .createHistoricProcessInstanceQuery()
		        .processDefinitionId(pscCommonTaskRequest.getProcessDefId());

	    runtimeService.restartProcessInstances(pscCommonTaskRequest.getProcessInstId())
		    .startBeforeActivity(pscCommonTaskRequest.getTaskDefKey())
		    .historicProcessInstanceQuery(hisProcessInstQuery)
		    .initialSetOfVariables()
		    .execute();*/
	    
		taskList = simpleGetTasks(pscCommonTaskRequest.getProcessInstId());
//		if (taskList != null && taskList.size() == 1) {
//			taskService.setAssignee(taskList.get(0).getId(), pscCommonTaskRequest.getNextUserId());
//		}
//		taskList = simpleGetTasks(pscCommonTaskRequest.getProcessInstId());
		return taskList;
	}
	
	@Override
	public List<TaskDto> turnOver(PscCommonTaskRequest pscCommonTaskRequest, HttpServletRequest request)
			throws Exception {
		AuthenticationService authenticationService = new AuthenticationService();
		String engineName = processEngine.getName();
		UserAuthentication authentication = (UserAuthentication) authenticationService.createAuthenticate(engineName,
				pscCommonTaskRequest.getUserId(), null, null);
		logger.info("authentication--------->" + authentication.getName());
		Authentications.revalidateSession(request, authentication);
		identityService.setAuthenticatedUserId(authentication.getName());
		List<TaskDto> taskList = new ArrayList<TaskDto>();
		Task task = taskService.createTaskQuery().taskId(pscCommonTaskRequest.getTaskId()).singleResult();
	    task.setAssignee(pscCommonTaskRequest.getNextUserId());
	    taskService.saveTask(task);
	    String comment = pscCommonTaskRequest.getUserId()+"将流程转办给"+pscCommonTaskRequest.getNextUserId()+"处理";
	    taskService.createComment(pscCommonTaskRequest.getTaskId(), pscCommonTaskRequest.getProcessInstId(),comment);
		taskList = simpleGetTasks(pscCommonTaskRequest.getProcessInstId());
//		if (taskList != null && taskList.size() == 1) {
//			taskService.setAssignee(taskList.get(0).getId(), pscCommonTaskRequest.getNextUserId());
//		}
//		taskList = simpleGetTasks(pscCommonTaskRequest.getProcessInstId());
		return taskList;
	}
	
	@Override
	public List<TaskDto> jump(PscCommonTaskRequest pscCommonTaskRequest, HttpServletRequest request)
			throws Exception {
		AuthenticationService authenticationService = new AuthenticationService();
		String engineName = processEngine.getName();
		UserAuthentication authentication = (UserAuthentication) authenticationService.createAuthenticate(engineName,
				pscCommonTaskRequest.getUserId(), null, null);
		logger.info("authentication--------->" + authentication.getName());
		Authentications.revalidateSession(request, authentication);
		identityService.setAuthenticatedUserId(authentication.getName());
		List<TaskDto> taskList = new ArrayList<TaskDto>();
		
		ActivityInstance tree = runtimeService.getActivityInstance(pscCommonTaskRequest.getProcessInstId());
		if(StringUtils.isBlank(pscCommonTaskRequest.getJumpType())){
			throw new Exception("跳转类型不能为空！");
		}
		if(StringUtils.isBlank(pscCommonTaskRequest.getToActId())){
			throw new Exception("目标节点不能为空！");
		}
		if(pscCommonTaskRequest.getJumpType().equals(PscCommonTaskRequest.JUMP_BACK)){
			taskService.createComment(pscCommonTaskRequest.getTaskId(), pscCommonTaskRequest.getProcessInstId(), "往回跳转流程");
			runtimeService
		      .createProcessInstanceModification(pscCommonTaskRequest.getProcessInstId())
		      .cancelActivityInstance(getInstanceIdForActivity(tree, pscCommonTaskRequest.getTaskDefKey()))
		      .startBeforeActivity(pscCommonTaskRequest.getToActId())
		      .execute();
		}else if(pscCommonTaskRequest.getJumpType().equals(PscCommonTaskRequest.JUMP_FORWARD)){
			taskService.complete(pscCommonTaskRequest.getTaskId(), pscCommonTaskRequest.getVariables());
			taskService.createComment(pscCommonTaskRequest.getTaskId(), pscCommonTaskRequest.getProcessInstId(), "往前跳转流程");
			ActivityInstance tree2 = runtimeService.getActivityInstance(pscCommonTaskRequest.getProcessInstId());
			runtimeService
				.createProcessInstanceModification(pscCommonTaskRequest.getProcessInstId())
				.cancelActivityInstance(getInstanceIdForActivity(tree2, tree2.getActivityId()))
				.startBeforeActivity(pscCommonTaskRequest.getToActId())
				.execute();
		}
		taskList = simpleGetTasks(pscCommonTaskRequest.getProcessInstId());
//		if (taskList != null && taskList.size() == 1) {
//			taskService.setAssignee(taskList.get(0).getId(), pscCommonTaskRequest.getNextUserId());
//		}
//		taskList = simpleGetTasks(pscCommonTaskRequest.getProcessInstId());
		return taskList;
	}
	
	public List<TaskDto> simpleGetTasks(String processInstId) throws Exception {
		List<TaskDto> resultList = new ArrayList<TaskDto>();
		List<Task> taskList = taskService.createTaskQuery().processInstanceId(processInstId).list();
		for (Task task : taskList) {
			TaskDto dto = new TaskDto();
			dto = TaskDto.fromEntity(task);
			resultList.add(dto);
		}
		return resultList;
	}

	public String getInstanceIdForActivity(ActivityInstance activityInstance, String activityId) {
		ActivityInstance instance = getChildInstanceForActivity(activityInstance, activityId);
		if (instance != null) {
			return instance.getId();
		}
		return null;
	}

	public ActivityInstance getChildInstanceForActivity(ActivityInstance activityInstance, String activityId) {
		if (activityId.equals(activityInstance.getActivityId())) {
			return activityInstance;
		}

		for (ActivityInstance childInstance : activityInstance.getChildActivityInstances()) {
			ActivityInstance instance = getChildInstanceForActivity(childInstance, activityId);
			if (instance != null) {
				return instance;
			}
		}

		return null;
	}
}
