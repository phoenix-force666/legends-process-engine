package com.legends.form.engine.controller;

import com.legends.form.engine.domain.SysCategory;
import com.legends.form.engine.service.ISysCategoryService;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 分类管理Controller
 * 
 * @author herion
 * @date 2020-12-02
 */
@RestController
@RequestMapping("/category")
public class SysCategoryController extends BaseController
{
    @Autowired
    private ISysCategoryService sysCategoryService;

    /**
     * 查询分类管理列表
     */
//    @PreAuthorize(hasPermi = "formEngine:category:list")
    @GetMapping("/list")
    public AjaxResult list(SysCategory sysCategory)
    {
        List<SysCategory> list = sysCategoryService.selectSysCategoryList(sysCategory);
        return AjaxResult.success(list);
    }

    /**
     * 导出分类管理列表
     */
//    @PreAuthorize(hasPermi = "formEngine:category:export")
//    @Log(title = "分类管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysCategory sysCategory) throws IOException
    {
        List<SysCategory> list = sysCategoryService.selectSysCategoryList(sysCategory);
        ExcelUtil<SysCategory> util = new ExcelUtil<SysCategory>(SysCategory.class);
        util.exportExcel(response, list, "category");
    }

    /**
     * 获取分类管理详细信息
     */
//    @PreAuthorize(hasPermi = "formEngine:category:query")
    @GetMapping(value = "/{categoryId}")
    public AjaxResult getInfo(@PathVariable("categoryId") Long categoryId)
    {
        return AjaxResult.success(sysCategoryService.selectSysCategoryById(categoryId));
    }

    /**
     * 新增分类管理
     */
//    @PreAuthorize(hasPermi = "formEngine:category:add")
//    @Log(title = "分类管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysCategory sysCategory)
    {
        return toAjax(sysCategoryService.insertSysCategory(sysCategory));
    }

    /**
     * 修改分类管理
     */
//    @PreAuthorize(hasPermi = "formEngine:category:edit")
//    @Log(title = "分类管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysCategory sysCategory)
    {
        return toAjax(sysCategoryService.updateSysCategory(sysCategory));
    }

    /**
     * 删除分类管理
     */
//    @PreAuthorize(hasPermi = "formEngine:category:remove")
//    @Log(title = "分类管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{categoryIds}")
    public AjaxResult remove(@PathVariable Long[] categoryIds)
    {
        return toAjax(sysCategoryService.deleteSysCategoryByIds(categoryIds));
    }
}
