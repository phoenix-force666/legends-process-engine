package com.legends.process.engine.repository;

import com.legends.process.engine.domain.legends.GroupTree;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author zhaopeng01
 * @version 1.0
 * @title: GroupTree
 * @projectName legends-process-engine
 * @description: TODO
 * @date 2020/12/2021:25
 */
public interface GroupTreeRepository extends MongoRepository<GroupTree, String> {
}
