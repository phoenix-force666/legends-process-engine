package com.legends.process.engine.service.impl.legends.group;

import com.legends.process.engine.base.utils.text.UUID;
import com.legends.process.engine.domain.legends.GroupTree;
import com.legends.process.engine.domain.legends.LgeGroupRel;
import com.legends.process.engine.entity.legends.LgeGroup;
import com.legends.process.engine.enums.LgeProcessEngine;
import com.legends.process.engine.mapper.LgeGroupRelMapper;
import com.legends.process.engine.repository.GroupTreeRepository;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.identity.UserQuery;
import org.camunda.bpm.engine.impl.persistence.entity.GroupEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author zero
 * @version 1.0
 * @title: GroupServiceImpl
 * @projectName legends-process-engine
 * @description: TODO
 * @date 2020/12/2015:37
 */
@Transactional
@Service
public class GroupServiceImpl {

  @Autowired IdentityService identityService;
/*  private final IdentityService identityService =
      LgeProcessEngine.INSTANCE.getProcessEngine().getIdentityService();*/

  @Autowired LgeGroupRelMapper lgeGroupRelMapper;

  @Autowired GroupTreeRepository groupTreeRepository;

  // 添加组
  public String addGroup(LgeGroup lgeGroup) {
    GroupEntity groupEntity = lgeGroup.getGroup();
    String id = UUID.fastUUID().toString(true);
    groupEntity.setId(id);
    identityService.saveGroup(groupEntity);
    addGroupRelationship(id, lgeGroup.getParentId());
    return id;
  }

  /**
   * 添加组与其父节点之间的关系
   *
   * @param id
   * @param parentId
   */
  private void addGroupRelationship(String id, String parentId) {
    LgeGroupRel lgeGroupRel = new LgeGroupRel();
    lgeGroupRel.setParentId(parentId);
    lgeGroupRel.setId(id);
    lgeGroupRelMapper.insertLgeGroupRel(lgeGroupRel);
  }

  /**
   * 更新组
   *
   * @param lgeGroup
   * @return
   */
  public String updateGroup(LgeGroup lgeGroup) {
    GroupEntity groupEntity = lgeGroup.getGroup();
    String id = lgeGroup.getGroup().getId();
    identityService.saveGroup(groupEntity);
    updateGroupRelationship(id, lgeGroup.getParentId());
    return id;
  }

  /**
   * 更新组之间的父级关系
   *
   * @param id
   * @param parentId
   */
  private void updateGroupRelationship(String id, String parentId) {
    LgeGroupRel lgeGroupRel = new LgeGroupRel();
    lgeGroupRel.setParentId(parentId);
    lgeGroupRel.setId(id);
    lgeGroupRelMapper.updateLgeGroupRel(lgeGroupRel);
  }

  /**
   * 获取所有组树节点
   *
   * @return
   */
  public List<GroupTree> getGroupTreeList() {
    return groupTreeRepository.findAll();
  }

  /**
   * 更新某个树节点
   *
   * @param groupTree
   * @return
   */
  public String updateGroupTree(GroupTree groupTree) {
    String id = groupTree.getId();
    groupTreeRepository.save(groupTree);
    return id;
  }

  /**
   * 删除mongo中的某个组树节点
   *
   * @param id
   */
  public void delGroupTree(String id) {
    groupTreeRepository.deleteById(id);
  }

  public Group getGroup(String id) {
    Group group = identityService.createGroupQuery().groupId(id).singleResult();
    return group;
  }

  /**
   * 根据组ID获取分页后的用户IDs
   *
   * @param id
   * @param first
   * @param max
   * @return
   */
  public List<String> getGroupUserIds(String id, Integer first, Integer max) {
    UserQuery userQuery = identityService.createUserQuery();
    List<User> users = userQuery.memberOfGroup(id).listPage(first, max);
    List<String> userids =
        users.stream()
            .map(
                user -> {
                  return user.getId();
                })
            .collect(Collectors.toList());
    return userids;
  }

  /**
   * 根据传入的组 id，获取其 所有父节点 id 对userids进行筛查，将组中已存在的进行剔除后添加
   *
   * @param id
   * @param userids
   * @return
   */
  public int addGroupUsers(String id, List<String> userids) {
    List<String> groupIds = new ArrayList<>();
    groupIds.add(id);
    groupIds = getParentGroupIds(id, groupIds);
    groupIds.stream()
        .forEach(
            gid -> {
              List<String> addUserIds = getRdToAddUserIds(gid, userids);
              addRdMembership(gid, userids);
            });

    return (int)
        identityService
            .createUserQuery()
            .memberOfGroup(id)
            .userIdIn(userids.toArray(new String[userids.size()]))
            .count();
  }

  /**
   * 批量建立组与用户关系
   * @param gid
   * @param userids
   */
  private void addRdMembership(String gid, List<String> userids) {
    userids.stream()
        .forEach(
            userid -> {
              identityService.createMembership(userid, gid);
            });
  }

  /**
   * @param gid
   * @param userids
   * @return
   */
  private List<String> getRdToAddUserIds(String gid, List<String> userids) {

    List<User> users =
        identityService
            .createUserQuery()
            .memberOfGroup(gid)
            .userIdIn(userids.toArray(new String[userids.size()]))
            .list();
    Iterator<String> uids = userids.iterator();
    while (uids.hasNext()) {
      String next = uids.next();
      users.stream()
          .forEach(
              user -> {
                if (user.getId().equals(next))
                  ;
                uids.remove();
              });
    }
    return StreamSupport.stream(
            Spliterators.spliteratorUnknownSize(uids, Spliterator.ORDERED), false)
        .collect(Collectors.toList());
  }

  /**
   * 递归获取包含自身在内的所有父级组ID
   *
   * @param id
   * @param ids
   * @return
   */
  private List<String> getParentGroupIds(String id, List<String> ids) {
    LgeGroupRel lgeGroupRel = lgeGroupRelMapper.selectLgeGroupRelById(id);
    List<String> newIds = ids;
    String pId = lgeGroupRel.getParentId();
    while (!"0".equals(pId)) {
      newIds.add(pId);
      return getParentGroupIds(pId, newIds);
    }
    return newIds;
  }

  /**
   * 删除组内的用户
   *
   * @param id
   * @param userids
   * @return
   */
  public int delGroupUsers(String id, List<String> userids) {
    userids.stream()
        .forEach(
            userid -> {
              identityService.deleteMembership(userid, id);
            });
    int userQuantity = (int) identityService.createGroupQuery().groupId(id).count();
    return userQuantity;
  }
}
