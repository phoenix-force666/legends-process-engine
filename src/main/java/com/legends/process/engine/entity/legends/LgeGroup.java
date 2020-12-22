package com.legends.process.engine.entity.legends;

import com.legends.process.engine.base.utils.text.UUID;
import lombok.*;
import org.camunda.bpm.engine.impl.persistence.entity.GroupEntity;

/**
 * @author zhaopeng01
 * @version 1.0
 * @title: LgeGroup
 * @projectName legends-process-engine
 * @description: TODO
 * @date 2020/12/2015:15
 */
@Data
public class LgeGroup {
    private String parentId = "0";
    private GroupEntity group;
}
