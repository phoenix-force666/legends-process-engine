package com.legends.process.engine.domain.legends;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * @author zhaopeng01
 * @version 1.0
 * @title: GroupTreeRel
 * @projectName legends-process-engine
 * @description: TODO
 * @date 2020/12/319:42
 */
@Data
public class GroupTreeRel {
    private String id;
    private String parentId;
    private String label;
    int userQuantity = 0;
    List<GroupTreeRel> children;
}
