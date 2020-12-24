package com.legends.process.engine.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.legends.process.engine.domain.legends.LgeGroupRel;

import java.util.List;

/**
 * @author zero
 * @version 1.0
 * @title: LgeGroupRelMapper
 * @projectName legends-process-engine
 * @description: TODO
 * @date 2020/12/2017:09
 */

public interface LgeGroupRelMapper extends BaseMapper<LgeGroupRel> {
    /** 获取group rel列表
     *
     * @param parentId
     * parentId 为空时返回所有
     * @return
     */
    public List<LgeGroupRel> selectLgeGroupRelList(String parentId);
    public LgeGroupRel selectLgeGroupRelById(String id);

    public void insertLgeGroupRel(LgeGroupRel lgeGroupRel);
    public void updateLgeGroupRel(LgeGroupRel lgeGroupRel);

    public void deleteLgeGroupRelById(String id);
}
