package com.legends.form.engine.service;

import java.util.List;
import com.legends.form.engine.domain.FormDeployment;

/**
 * 表单部署Service接口
 * 
 * @author herion
 * @date 2020-12-02
 */
public interface IFormDeploymentService 
{
    /**
     * 查询表单部署
     * 
     * @param deployId 表单部署ID
     * @return 表单部署
     */
    public FormDeployment selectFormDeploymentById(Long deployId);

    /**
     * 查询表单部署列表
     * 
     * @param formDeployment 表单部署
     * @return 表单部署集合
     */
    public List<FormDeployment> selectFormDeploymentList(FormDeployment formDeployment);

    /**
     * 新增表单部署
     * 
     * @param formDeployment 表单部署
     * @return 结果
     */
    public int insertFormDeployment(FormDeployment formDeployment);

    /**
     * 修改表单部署
     * 
     * @param formDeployment 表单部署
     * @return 结果
     */
    public int updateFormDeployment(FormDeployment formDeployment);

    /**
     * 批量删除表单部署
     * 
     * @param deployIds 需要删除的表单部署ID
     * @return 结果
     */
    public int deleteFormDeploymentByIds(Long[] deployIds);

    /**
     * 删除表单部署信息
     * 
     * @param deployId 表单部署ID
     * @return 结果
     */
    public int deleteFormDeploymentById(Long deployId);
}
