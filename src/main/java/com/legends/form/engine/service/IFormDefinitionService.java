package com.legends.form.engine.service;

import java.util.List;
import com.legends.form.engine.domain.FormDefinition;

/**
 * 表单定义Service接口
 * 
 * @author herion
 * @date 2020-12-02
 */
public interface IFormDefinitionService 
{
    /**
     * 查询表单定义
     * 
     * @param definitionId 表单定义ID
     * @return 表单定义
     */
    public FormDefinition selectFormDefinitionById(Long definitionId);

    /**
     * 查询表单定义列表
     * 
     * @param formDefinition 表单定义
     * @return 表单定义集合
     */
    public List<FormDefinition> selectFormDefinitionList(FormDefinition formDefinition);

    /**
     * 新增表单定义
     * 
     * @param formDefinition 表单定义
     * @return 结果
     */
    public int insertFormDefinition(FormDefinition formDefinition);

    /**
     * 修改表单定义
     * 
     * @param formDefinition 表单定义
     * @return 结果
     */
    public int updateFormDefinition(FormDefinition formDefinition);

    /**
     * 批量删除表单定义
     * 
     * @param definitionIds 需要删除的表单定义ID
     * @return 结果
     */
    public int deleteFormDefinitionByIds(Long[] definitionIds);

    /**
     * 删除表单定义信息
     * 
     * @param definitionId 表单定义ID
     * @return 结果
     */
    public int deleteFormDefinitionById(Long definitionId);
}
