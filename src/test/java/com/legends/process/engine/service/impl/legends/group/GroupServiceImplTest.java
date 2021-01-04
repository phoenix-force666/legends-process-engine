package com.legends.process.engine.service.impl.legends.group;

import com.legends.process.engine.domain.legends.GroupTreeRel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author zhaopeng01
 * @version 1.0
 * @title: GroupServiceImplTest
 * @projectName legends-process-engine
 * @description: TODO
 * @date 2020/12/3110:27
 */
class GroupServiceImplTest {

    @Autowired GroupServiceImpl groupService;

    @Test
    void addGroup() {
    }

    @Test
    void updateGroup() {
    }

    @Test
    void getGroupTreeList() {
        List<GroupTreeRel> groupTreeRels = groupService.getGroupTreeList(null, null, null);
        System.out.println(groupTreeRels);
    }

    @Test
    void updateGroupTree() {
    }

    @Test
    void delGroupTree() {
    }

    @Test
    void getGroup() {
    }

    @Test
    void getGroupUserIds() {
    }

    @Test
    void addGroupUsers() {
    }

    @Test
    void delGroupUsers() {
    }

    @Test
    void delGroup() {
    }
}