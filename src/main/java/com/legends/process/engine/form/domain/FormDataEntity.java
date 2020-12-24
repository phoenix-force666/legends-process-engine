package com.legends.process.engine.form.domain;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "form_data")
@Data
public class FormDataEntity {

    /**
     * 表单数据ID关联流程实例id
     */
    @Id
    private String id;
    /**
     * 表单id
     */
    private Long formId;

    /**
     * 表单数据
     */
    private Map<String, Object> data;
}
