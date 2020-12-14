package com.legends.form.engine.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.legends.form.engine.mapper.FormDeploymentMapper;
import com.legends.form.engine.domain.FormDeployment;
import com.legends.form.engine.service.IFormDeploymentService;

/**
 * 表单部署Service业务层处理
 * 
 * @author herion
 * @date 2020-12-02
 */
@Service
public class FormDeploymentServiceImpl implements IFormDeploymentService 
{
    @Autowired
    private FormDeploymentMapper formDeploymentMapper;

    /**
     * 查询表单部署
     * 
     * @param deployId 表单部署ID
     * @return 表单部署
     */
    @Override
    public FormDeployment selectFormDeploymentById(Long deployId)
    {
        return formDeploymentMapper.selectFormDeploymentById(deployId);
    }

    /**
     * 查询表单部署列表
     * 
     * @param formDeployment 表单部署
     * @return 表单部署
     */
    @Override
    public List<FormDeployment> selectFormDeploymentList(FormDeployment formDeployment)
    {
        return formDeploymentMapper.selectFormDeploymentList(formDeployment);
    }

    /**
     * 新增表单部署
     * 
     * @param formDeployment 表单部署
     * @return 结果
     */
    @Override
    public int insertFormDeployment(FormDeployment formDeployment)
    {
        formDeployment.setCreateTime(DateUtils.getNowDate());
        return formDeploymentMapper.insertFormDeployment(formDeployment);
    }

    /**
     * 修改表单部署
     * 
     * @param formDeployment 表单部署
     * @return 结果
     */
    @Override
    public int updateFormDeployment(FormDeployment formDeployment)
    {
        formDeployment.setUpdateTime(DateUtils.getNowDate());
        return formDeploymentMapper.updateFormDeployment(formDeployment);
    }

    /**
     * 批量删除表单部署
     * 
     * @param deployIds 需要删除的表单部署ID
     * @return 结果
     */
    @Override
    public int deleteFormDeploymentByIds(Long[] deployIds)
    {
        return formDeploymentMapper.deleteFormDeploymentByIds(deployIds);
    }

    /**
     * 删除表单部署信息
     * 
     * @param deployId 表单部署ID
     * @return 结果
     */
    @Override
    public int deleteFormDeploymentById(Long deployId)
    {
        return formDeploymentMapper.deleteFormDeploymentById(deployId);
    }
}
