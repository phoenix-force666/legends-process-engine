package com.legends.form.engine.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.legends.form.engine.mapper.FormDefinitionMapper;
import com.legends.form.engine.domain.FormDefinition;
import com.legends.form.engine.service.IFormDefinitionService;

/**
 * 表单定义Service业务层处理
 * 
 * @author herion
 * @date 2020-12-02
 */
@Service
public class FormDefinitionServiceImpl implements IFormDefinitionService 
{
    @Autowired
    private FormDefinitionMapper formDefinitionMapper;

    /**
     * 查询表单定义
     * 
     * @param definitionId 表单定义ID
     * @return 表单定义
     */
    @Override
    public FormDefinition selectFormDefinitionById(Long definitionId)
    {
        return formDefinitionMapper.selectFormDefinitionById(definitionId);
    }

    /**
     * 查询表单定义列表
     * 
     * @param formDefinition 表单定义
     * @return 表单定义
     */
    @Override
    public List<FormDefinition> selectFormDefinitionList(FormDefinition formDefinition)
    {
        return formDefinitionMapper.selectFormDefinitionList(formDefinition);
    }

    /**
     * 新增表单定义
     * 
     * @param formDefinition 表单定义
     * @return 结果
     */
    @Override
    public int insertFormDefinition(FormDefinition formDefinition)
    {
        formDefinition.setCreateTime(DateUtils.getNowDate());
        return formDefinitionMapper.insertFormDefinition(formDefinition);
    }

    /**
     * 修改表单定义
     * 
     * @param formDefinition 表单定义
     * @return 结果
     */
    @Override
    public int updateFormDefinition(FormDefinition formDefinition)
    {
        formDefinition.setUpdateTime(DateUtils.getNowDate());
        return formDefinitionMapper.updateFormDefinition(formDefinition);
    }

    /**
     * 批量删除表单定义
     * 
     * @param definitionIds 需要删除的表单定义ID
     * @return 结果
     */
    @Override
    public int deleteFormDefinitionByIds(Long[] definitionIds)
    {
        return formDefinitionMapper.deleteFormDefinitionByIds(definitionIds);
    }

    /**
     * 删除表单定义信息
     * 
     * @param definitionId 表单定义ID
     * @return 结果
     */
    @Override
    public int deleteFormDefinitionById(Long definitionId)
    {
        return formDefinitionMapper.deleteFormDefinitionById(definitionId);
    }
}
