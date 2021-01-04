package com.legends.process.engine.biz;


import com.alibaba.fastjson.JSONObject;
import com.legends.form.engine.domain.FormDataEntity;
import com.legends.form.engine.domain.FormEntity;
import com.legends.form.engine.service.IFormDataService;
import com.legends.form.engine.service.IFormService;
import com.legends.form.engine.vo.FormVO;
import com.legends.process.engine.domain.LgeProcessFormRel;
import com.legends.process.engine.mapper.LgeProcessFormRelMapper;
import com.legends.process.engine.vo.FormSaveReq;
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
            formEntity =formService.findById(processDeploymentId);
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
            formEntity =formService.findById(processDefId);
        }
        return formEntity;
    }

    /**
     * 保存表单
     * @param formSaveReq
     */
    public void save(FormSaveReq formSaveReq){
//        LgeProcessFormRel lgeProcessFormRel=new LgeProcessFormRel();
//        lgeProcessFormRel.setProcessDefId(formSaveReq.getProcessDefId());
//        processFormRelMapper.insertLgeProcessFormRel(lgeProcessFormRel);
    }
}
