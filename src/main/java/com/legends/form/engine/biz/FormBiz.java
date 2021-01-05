package com.legends.form.engine.biz;

import com.alibaba.fastjson.JSONObject;
import com.legends.form.engine.domain.FormDataEntity;
import com.legends.form.engine.domain.FormEntity;
import com.legends.form.engine.service.IFormDataService;
import com.legends.form.engine.service.IFormService;
import com.legends.form.engine.vo.FormVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class FormBiz {

    @Autowired
    private IFormDataService formDataService;
    @Autowired
    private IFormService formService;

    /**
     * 获取表单与数据
     * @param processDefId
     * @param processInstId
     * @return
     */
    public FormVO getProcessFormInfoByProcessDefId(String processDefId, String processInstId){
        FormEntity formEntity =formService.findById(processDefId);
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
}
