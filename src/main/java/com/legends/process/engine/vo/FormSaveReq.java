package com.legends.process.engine.vo;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.io.Serializable;

@Data
public class FormSaveReq implements Serializable {
    private Long formId;
    private JSONObject form;
    private String processDefId;
}
