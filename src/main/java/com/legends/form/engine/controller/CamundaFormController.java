package com.legends.form.engine.controller;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.form.StartFormData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;

@RestController
@RequestMapping("camunda/form")
public class CamundaFormController {
    @Autowired
    private FormService formService;

    @GetMapping("getStartFormData/{processDefinitionId}")
    public StartFormData getStartFormData(@PathVariable(name = "processDefinitionId") String processDefinitionId){
        return formService.getStartFormData(processDefinitionId);
    }

    @GetMapping("getDeployedStartForm/{processDefinitionId}")
    public InputStream getDeployedStartForm(@PathVariable(name = "processDefinitionId") String processDefinitionId){
        return formService.getDeployedStartForm(processDefinitionId);
    }


    @GetMapping("getDeployedTaskForm/{processDefinitionId}")
    public InputStream getDeployedTaskForm(@PathVariable(name = "taskId") String taskId){
        return formService.getDeployedTaskForm(taskId);
    }

}
