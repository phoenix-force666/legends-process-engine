package com.legends.process.engine.form.domain;


import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "formEngine")
@Data
public class FormEntity implements Serializable {

    /**
     * 表单ID
     */
    @Id
    private Long id;
    /**
     * 表单名称
     */
    private String name;

    /**
     * 流程 key
     */
    private String processKey;



    /**
     * 表单数据
     */
    private JSONObject data;

}
