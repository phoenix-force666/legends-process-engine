package com.legends.process.engine.service.impl;

import com.legends.process.engine.domain.LgeProcessFormRel;
import com.legends.process.engine.mapper.LgeProcessFormRelMapper;
import com.legends.process.engine.service.ILgeProcessFormRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 流程表单关联Service业务层处理
 * 
 * @author herion
 * @date 2020-12-16
 */
@Service
public class LgeProcessFormRelServiceImpl implements ILgeProcessFormRelService 
{
    @Autowired
    private LgeProcessFormRelMapper lgeProcessFormRelMapper;

    /**
     * 查询流程表单关联
     * 
     * @param id 流程表单关联ID
     * @return 流程表单关联
     */
    @Override
    public LgeProcessFormRel selectLgeProcessFormRelById(Long id)
    {
        return lgeProcessFormRelMapper.selectLgeProcessFormRelById(id);
    }

    /**
     * 查询流程表单关联列表
     * 
     * @param lgeProcessFormRel 流程表单关联
     * @return 流程表单关联
     */
    @Override
    public List<LgeProcessFormRel> selectLgeProcessFormRelList(LgeProcessFormRel lgeProcessFormRel)
    {
        return lgeProcessFormRelMapper.selectLgeProcessFormRelList(lgeProcessFormRel);
    }

    /**
     * 新增流程表单关联
     * 
     * @param lgeProcessFormRel 流程表单关联
     * @return 结果
     */
    @Override
    public int insertLgeProcessFormRel(LgeProcessFormRel lgeProcessFormRel) {
        return lgeProcessFormRelMapper.insertLgeProcessFormRel(lgeProcessFormRel);
    }

    /**
     * 修改流程表单关联
     * 
     * @param lgeProcessFormRel 流程表单关联
     * @return 结果
     */
    @Override
    public int updateLgeProcessFormRel(LgeProcessFormRel lgeProcessFormRel)
    {
        return lgeProcessFormRelMapper.updateLgeProcessFormRel(lgeProcessFormRel);
    }

    /**
     * 批量删除流程表单关联
     * 
     * @param ids 需要删除的流程表单关联ID
     * @return 结果
     */
    @Override
    public int deleteLgeProcessFormRelByIds(Long[] ids)
    {
        return lgeProcessFormRelMapper.deleteLgeProcessFormRelByIds(ids);
    }

    /**
     * 删除流程表单关联信息
     * 
     * @param id 流程表单关联ID
     * @return 结果
     */
    @Override
    public int deleteLgeProcessFormRelById(Long id)
    {
        return lgeProcessFormRelMapper.deleteLgeProcessFormRelById(id);
    }
}
