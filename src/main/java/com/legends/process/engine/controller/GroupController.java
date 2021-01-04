package com.legends.process.engine.controller;

import com.legends.cloud.common.base.ComResp;
import com.legends.process.engine.domain.legends.GroupTree;
import com.legends.process.engine.entity.legends.LgeGroup;
import com.legends.process.engine.service.impl.legends.group.GroupServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.camunda.bpm.engine.identity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zero
 * @version 1.0
 * @title: GroupController
 * @projectName legends-process-engine
 * @description: TODO
 * @date 2020/12/1821:43
 */
@RestController
@RequestMapping("/lge/groups")
public class GroupController {
  @Autowired GroupServiceImpl groupService;

  @GetMapping("/treelist")
  @ApiOperation(value = "获取组树", notes = "获取组树")
  public ComResp<List<GroupTree>> getGroupTree(
      @RequestParam(defaultValue = "") String parentId,
      @RequestParam(defaultValue = "") String id,
      @RequestParam(defaultValue = "") String name) {
    return new ComResp.Builder().data(groupService.getGroupTreeList(parentId, id, name)).build();
  }

  @PostMapping("/treelist")
  @ApiOperation(value = "更新组树", notes = "更新组树")
  public ComResp<String> updateTreelist(@RequestBody GroupTree groupTree) {
    String id = groupService.updateGroupTree(groupTree);
    return new ComResp.Builder().data(id).build();
  }

  @DeleteMapping("/treelist/{id}")
  @ApiOperation(value = "删除组树", notes = "删除组树")
  public ComResp<String> delTreelist(@PathVariable String id) {
    groupService.delGroupTree(id);
    return new ComResp.Builder().data(id).build();
  }

  // 获取组列表
  @GetMapping()
  public ComResp<List<Group>> getGroupList(@RequestBody String query) {
    return null;
  }

  /**
   * 根据组ID获取组
   *
   * @param id
   * @return
   */
  @GetMapping("/{id}")
  @ApiOperation(value = "查看组信息", notes = "查看组信息")
  public ComResp<Group> getGroup(@PathVariable String id) {

    return new ComResp.Builder().data(groupService.getGroup(id)).build();
  }

  /**
   * 根据组ID获取组
   *
   * @param id
   * @return
   */
  @DeleteMapping("/{id}")
  @ApiOperation(value = "根据组ID删除组", notes = "根据组ID删除组")
  public ComResp<String> deleteGroup(@PathVariable String id) {

    return new ComResp.Builder().data(groupService.delGroup(id)).build();
  }

  /**
   * 根据组ID获取其下用户IDs
   *
   * @param id
   * @return
   */
  @GetMapping("/{id}/users")
  @ApiOperation(value = "获取组用户ID列表", notes = "获取组用户ID列表")
  public ComResp<List<String>> getGroupUserIds(
      @PathVariable String id,
      @RequestParam(defaultValue = "0") Integer first,
      @RequestParam(defaultValue = "10") Integer max) {

    return new ComResp.Builder().data(groupService.getGroupUserIds(id, first, max)).build();
  }

  // 新增组
  @PostMapping()
  @ApiOperation(value = "新增组并返回组ID", notes = "新增组并返回组ID")
  public ComResp<String> addGroup(@RequestBody LgeGroup lgeGroup) {
    String id = groupService.addGroup(lgeGroup);
    return new ComResp.Builder().data(id).build();
  }

  // 更新组
  @PutMapping()
  @ApiOperation(value = "更新组并返回组ID", notes = "更新组并返回组ID")
  public ComResp<String> updateGroup(@RequestBody LgeGroup lgeGroup) {
    String id = groupService.updateGroup(lgeGroup);
    return new ComResp.Builder().data(id).build();
  }

  /**
   * 更新组ID下的用户
   *
   * @param id
   * @param userids
   * @return 组下的所有用户数
   */
  @PostMapping("/{id}/users")
  @ApiOperation(value = "更新组用户", notes = "更新组用户")
  public ComResp<Integer> updateGroupUsers(
      @PathVariable String id, @RequestBody List<String> userids) {
    int userQuantity = groupService.addGroupUsers(id, userids);
    return new ComResp.Builder().data(userQuantity).build();
  }

  /**
   * 删除组ID下的用户
   *
   * @param id
   * @param userids
   * @return 组下的所有用户数
   */
  @DeleteMapping("/{id}/users")
  @ApiOperation(value = "删除组用户", notes = "删除组用户")
  public ComResp<Integer> delGroupUsers(
      @PathVariable String id, @RequestBody List<String> userids) {
    int userQuantity = groupService.delGroupUsers(id, userids);
    return new ComResp.Builder().data(userQuantity).build();
  }
}
