package com.legends.process.engine.service.impl.legends.user;

import com.legends.process.engine.enums.LgeProcessEngine;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zero
 * @version 1.0
 * @title: UserServiceImpl
 * @projectName legends-process-engine
 * @description: TODO
 * @date 2020/12/2114:57
 */
@Service
@Transactional
public class UserServiceImpl {

    @Autowired
    IdentityService identityService;
/*    private final IdentityService identityService =
            LgeProcessEngine.INSTANCE.getProcessEngine().getIdentityService();*/

    public Integer addUsers(List<User> users) {
        users.stream().forEach(user -> {
            identityService.saveUser(user);
        });
        return users.size();
    }
}
