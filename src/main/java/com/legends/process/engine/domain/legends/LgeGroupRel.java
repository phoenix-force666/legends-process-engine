package com.legends.process.engine.domain.legends;

import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * @author zhaopeng01
 * @version 1.0
 * @title: LgeGroupDomain
 * @projectName legends-process-engine
 * @description: TODO
 * @date 2020/12/2015:34
 */
public class LgeGroupRel  extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String parentId = "0";
    // 主键
    private String id;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
