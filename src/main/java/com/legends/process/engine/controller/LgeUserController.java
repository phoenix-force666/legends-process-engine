package com.legends.process.engine.controller;

import com.legends.cloud.common.base.ComResp;
import com.legends.process.engine.service.impl.legends.user.UserServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.impl.persistence.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhaopeng01
 * @version 1.0
 * @title: LgeUserController
 * @projectName legends-process-engine
 * @description: TODO
 * @date 2020/12/2114:49
 */
@RestController
@RequestMapping("/lge/users")
public class LgeUserController {

    @Autowired
    UserServiceImpl userService;

    /**
     * 将一组用户批量添加进系统
     * @param users
     * @return
     */
    @PostMapping()
    @ApiOperation(value = "添加一组用户", notes = "添加一组用户")
    public ComResp<Integer> addUsers(@RequestBody List<UserEntity> users) {
        Integer count = userService.addUsers(users);
        return new ComResp.Builder().data(count).build();
    }

    /**
     * 将一组用户批量添加进系统
     * @param users
     * @return
     */
    @GetMapping()
    @ApiOperation(value = "添加一组用户", notes = "添加一组用户")
    public ComResp<List<String>> getUsers(@RequestBody List<UserEntity> users) {
        Integer count = userService.addUsers(users);
        return new ComResp.Builder().data(count).build();
    }
}
