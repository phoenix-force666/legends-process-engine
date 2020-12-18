package com.legends.process.engine.biz;


import com.alibaba.fastjson.JSONObject;
import com.legends.process.engine.domain.LgeProcessFormRel;
import com.legends.process.engine.form.domain.FormEntity;
import com.legends.process.engine.form.service.IFormService;
import com.legends.process.engine.mapper.LgeProcessFormRelMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class ProcessFormRelBiz {

    @Autowired
    private LgeProcessFormRelMapper processFormRelMapper;

    @Autowired
    private IFormService formService;

    /**
     * 获取表单
     * @param processDeploymentId
     * @return
     */
    public JSONObject getProcessFormByProcessDeploymentId(String processDeploymentId){
        LgeProcessFormRel lgeProcessFormRel=processFormRelMapper.getProcessFormByProcessDeploymentId(processDeploymentId);
        log.info("getFormDeployId",lgeProcessFormRel.getFormDeployId());
        FormEntity formEntity =formService.findById(lgeProcessFormRel.getFormDeployId());
        if(Objects.isNull(formEntity)){
            return null;
        }
        return formEntity.getData();
    }
}
