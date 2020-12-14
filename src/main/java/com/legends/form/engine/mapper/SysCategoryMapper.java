package com.legends.form.engine.mapper;

import com.legends.form.engine.domain.SysCategory;

import java.util.List;

/**
 * 分类管理Mapper接口
 * 
 * @author herion
 * @date 2020-12-02
 */
public interface SysCategoryMapper
{
    /**
     * 查询分类管理
     * 
     * @param categoryId 分类管理ID
     * @return 分类管理
     */
    public SysCategory selectSysCategoryById(Long categoryId);

    /**
     * 查询分类管理列表
     * 
     * @param sysCategory 分类管理
     * @return 分类管理集合
     */
    public List<SysCategory> selectSysCategoryList(SysCategory sysCategory);

    /**
     * 新增分类管理
     * 
     * @param sysCategory 分类管理
     * @return 结果
     */
    public int insertSysCategory(SysCategory sysCategory);

    /**
     * 修改分类管理
     * 
     * @param sysCategory 分类管理
     * @return 结果
     */
    public int updateSysCategory(SysCategory sysCategory);

    /**
     * 删除分类管理
     * 
     * @param categoryId 分类管理ID
     * @return 结果
     */
    public int deleteSysCategoryById(Long categoryId);

    /**
     * 批量删除分类管理
     * 
     * @param categoryIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysCategoryByIds(Long[] categoryIds);
}
