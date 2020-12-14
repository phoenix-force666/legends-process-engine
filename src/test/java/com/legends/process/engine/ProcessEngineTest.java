package com.legends.process.engine;

import com.legends.LegendsProcessEngineApplication;
import org.camunda.bpm.engine.*;
import org.camunda.bpm.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LegendsProcessEngineApplication.class)
public class ProcessEngineTest {

    @Test
    public void test(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        RepositoryService repositoryService = processEngine.getRepositoryService();

        RuntimeService runtimeService = processEngine.getRuntimeService();


        TaskService taskService = processEngine.getTaskService();
        IdentityService identityService = processEngine.getIdentityService();
        FormService formService = processEngine.getFormService();
        HistoryService historyService = processEngine.getHistoryService();
        ManagementService managementService = processEngine.getManagementService();
        FilterService filterService = processEngine.getFilterService();
        ExternalTaskService externalTaskService = processEngine.getExternalTaskService();
        CaseService caseService = processEngine.getCaseService();
        DecisionService decisionService = processEngine.getDecisionService();


        List<Task> tasks = taskService.createTaskQuery()
//                .taskAssignee("kermit")
//                .processVariableValueEquals("orderId", "0815")
                .orderByDueDate().asc()
                .list();

        tasks.forEach(task ->{
            System.out.println("task:"+task.toString());
        });

    }
}
