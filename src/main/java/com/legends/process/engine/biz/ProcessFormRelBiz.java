package com.legends.process.engine.biz;


import com.alibaba.fastjson.JSONObject;
import com.legends.process.engine.domain.LgeProcessFormRel;
import com.legends.process.engine.form.domain.FormDataEntity;
import com.legends.process.engine.form.domain.FormEntity;
import com.legends.process.engine.form.service.IFormDataService;
import com.legends.process.engine.form.service.IFormService;
import com.legends.process.engine.form.vo.FormVO;
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

    @Autowired
    private IFormDataService formDataService;



    /**
     * 获取表单
     * @param processDeploymentId
     * @return
     */
    public FormEntity getProcessFormByProcessDeploymentId(String processDeploymentId){
        LgeProcessFormRel lgeProcessFormRel=processFormRelMapper.getProcessFormByProcessDeploymentId(processDeploymentId);
        FormEntity formEntity =null;
        if(Objects.nonNull(lgeProcessFormRel)){
            formEntity =formService.findById(lgeProcessFormRel.getFormDeployId());
        }
        return formEntity;
    }


    /**
     * 获取表单与数据
     * @param processDefId
     * @param processInstId
     * @return
     */
    public FormVO getProcessFormInfoByProcessDefId(String processDefId,String processInstId){
        FormEntity formEntity =this.getProcessFormByProcessDefId(processDefId);
        JSONObject data=null;
        if(Objects.nonNull(formEntity)){
            data=formEntity.getData();
            data.put("formBtns",false);
            data.put("disabled",true);
            formEntity.setData(data);
        }
        FormDataEntity formDataEntity=formDataService.findById(processInstId);
        FormVO formVo=new FormVO();
        formVo.setForm(data);
        if(Objects.nonNull(formDataEntity)){
            formVo.setFormData(formDataEntity.getData());
        }

        return formVo;
    }

    /**
     * 获取表单
     * @param processDefId
     * @return
     */
    public FormEntity getProcessFormByProcessDefId(String processDefId){
        LgeProcessFormRel lgeProcessFormRel=processFormRelMapper.getProcessFormByProcessDefId(processDefId);
        FormEntity formEntity =null;
        if(Objects.nonNull(lgeProcessFormRel)){
            formEntity =formService.findById(lgeProcessFormRel.getFormDeployId());
        }
        return formEntity;
    }
}
