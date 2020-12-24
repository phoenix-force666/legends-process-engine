package com.legends.process.engine.form.vo;


import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class FormVO implements Serializable {
    private JSONObject form;
    private Map<String,Object> formData;

}
