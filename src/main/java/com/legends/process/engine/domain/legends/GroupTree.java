package com.legends.process.engine.domain.legends;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;

/**
 * @author zero
 * @version 1.0
 * @title: GroupTree
 * @projectName legends-process-engine
 * @description: TODO
 * @date 2020/12/2021:00
 */
@Data
@Document("grouptree")
public class GroupTree implements Serializable {
    @Field("_id")
    String id;
    @Field("label")
    String label;
    @Field("userQuantity")
    int userQuantity = 0;
    @Field("children")
    List<GroupTree> children;
}
