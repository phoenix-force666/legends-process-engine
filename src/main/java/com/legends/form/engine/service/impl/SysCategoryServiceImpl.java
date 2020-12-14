package com.legends.form.engine.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.legends.form.engine.mapper.SysCategoryMapper;
import com.legends.form.engine.domain.SysCategory;
import com.legends.form.engine.service.ISysCategoryService;

/**
 * 分类管理Service业务层处理
 * 
 * @author herion
 * @date 2020-12-02
 */
@Service
public class SysCategoryServiceImpl implements ISysCategoryService 
{
    @Autowired
    private SysCategoryMapper sysCategoryMapper;

    /**
     * 查询分类管理
     * 
     * @param categoryId 分类管理ID
     * @return 分类管理
     */
    @Override
    public SysCategory selectSysCategoryById(Long categoryId)
    {
        return sysCategoryMapper.selectSysCategoryById(categoryId);
    }

    /**
     * 查询分类管理列表
     * 
     * @param sysCategory 分类管理
     * @return 分类管理
     */
    @Override
    public List<SysCategory> selectSysCategoryList(SysCategory sysCategory)
    {
        return sysCategoryMapper.selectSysCategoryList(sysCategory);
    }

    /**
     * 新增分类管理
     * 
     * @param sysCategory 分类管理
     * @return 结果
     */
    @Override
    public int insertSysCategory(SysCategory sysCategory)
    {
        sysCategory.setCreateTime(DateUtils.getNowDate());
        return sysCategoryMapper.insertSysCategory(sysCategory);
    }

    /**
     * 修改分类管理
     * 
     * @param sysCategory 分类管理
     * @return 结果
     */
    @Override
    public int updateSysCategory(SysCategory sysCategory)
    {
        sysCategory.setUpdateTime(DateUtils.getNowDate());
        return sysCategoryMapper.updateSysCategory(sysCategory);
    }

    /**
     * 批量删除分类管理
     * 
     * @param categoryIds 需要删除的分类管理ID
     * @return 结果
     */
    @Override
    public int deleteSysCategoryByIds(Long[] categoryIds)
    {
        return sysCategoryMapper.deleteSysCategoryByIds(categoryIds);
    }

    /**
     * 删除分类管理信息
     * 
     * @param categoryId 分类管理ID
     * @return 结果
     */
    @Override
    public int deleteSysCategoryById(Long categoryId)
    {
        return sysCategoryMapper.deleteSysCategoryById(categoryId);
    }
}
