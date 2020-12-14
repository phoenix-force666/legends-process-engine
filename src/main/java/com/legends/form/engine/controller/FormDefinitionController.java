package com.legends.form.engine.controller;

import com.legends.form.engine.domain.FormDefinition;
import com.legends.form.engine.mapper.FormDefinitionMapper;
import com.legends.form.engine.service.IFormDefinitionService;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 表单定义Controller
 * 
 * @author herion
 * @date 2020-12-02
 */
@RestController
@RequestMapping("/definition")
public class FormDefinitionController extends BaseController
{
    @Autowired
    private IFormDefinitionService formDefinitionService;

    @Autowired
    private FormDefinitionMapper formDefinitionMapper;

    /**
     * 查询表单定义列表
     */
//    @PreAuthorize(hasPermi = "formEngine:definition:list")
    @GetMapping("/list")
    public TableDataInfo list(FormDefinition formDefinition) {
        startPage();
        List<FormDefinition> list = formDefinitionService.selectFormDefinitionList(formDefinition);
//        List<FormDefinition> list  =formDefinitionMapper.selectList(new QueryWrapper<FormDefinition>());
        return getDataTable(list);
    }

    /**
     * 导出表单定义列表
     */
//    @PreAuthorize(hasPermi = "formEngine:definition:export")
//    @Log(title = "表单定义", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FormDefinition formDefinition) throws IOException
    {
        List<FormDefinition> list = formDefinitionService.selectFormDefinitionList(formDefinition);
        ExcelUtil<FormDefinition> util = new ExcelUtil<FormDefinition>(FormDefinition.class);
        util.exportExcel(response, list, "definition");
    }

    /**
     * 获取表单定义详细信息
     */
//    @PreAuthorize(hasPermi = "formEngine:definition:query")
    @GetMapping(value = "/{definitionId}")
    public AjaxResult getInfo(@PathVariable("definitionId") Long definitionId)
    {
        return AjaxResult.success(formDefinitionService.selectFormDefinitionById(definitionId));
    }

    /**
     * 新增表单定义
     */
//    @PreAuthorize(hasPermi = "formEngine:definition:add")
//    @Log(title = "表单定义", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FormDefinition formDefinition)
    {
        return toAjax(formDefinitionService.insertFormDefinition(formDefinition));
    }

    /**
     * 修改表单定义
     */
//    @PreAuthorize(hasPermi = "formEngine:definition:edit")
//    @Log(title = "表单定义", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FormDefinition formDefinition)
    {
        return toAjax(formDefinitionService.updateFormDefinition(formDefinition));
    }

    /**
     * 删除表单定义
     */
//    @PreAuthorize(hasPermi = "formEngine:definition:remove")
//    @Log(title = "表单定义", businessType = BusinessType.DELETE)
	@DeleteMapping("/{definitionIds}")
    public AjaxResult remove(@PathVariable Long[] definitionIds)
    {
        return toAjax(formDefinitionService.deleteFormDefinitionByIds(definitionIds));
    }
}
