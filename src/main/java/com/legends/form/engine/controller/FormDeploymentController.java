package com.legends.form.engine.controller;

import com.legends.form.engine.domain.FormDeployment;
import com.legends.form.engine.service.IFormDeploymentService;
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
 * 表单部署Controller
 * 
 * @author herion
 * @date 2020-12-02
 */
@RestController
@RequestMapping("/deployment")
public class FormDeploymentController extends BaseController
{
    @Autowired
    private IFormDeploymentService formDeploymentService;

    /**
     * 查询表单部署列表
     */
//    @PreAuthorize(hasPermi = "formEngine:deployment:list")
    @GetMapping("/list")
    public TableDataInfo list(FormDeployment formDeployment)
    {
        startPage();
        List<FormDeployment> list = formDeploymentService.selectFormDeploymentList(formDeployment);
        return getDataTable(list);
    }

    /**
     * 导出表单部署列表
     */
//    @PreAuthorize(hasPermi = "formEngine:deployment:export")
//    @Log(title = "表单部署", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FormDeployment formDeployment) throws IOException
    {
        List<FormDeployment> list = formDeploymentService.selectFormDeploymentList(formDeployment);
        ExcelUtil<FormDeployment> util = new ExcelUtil<FormDeployment>(FormDeployment.class);
        util.exportExcel(response, list, "deployment");
    }

    /**
     * 获取表单部署详细信息
     */
//    @PreAuthorize(hasPermi = "formEngine:deployment:query")
//    @GetMapping(value = "/{deployId}")
    public AjaxResult getInfo(@PathVariable("deployId") Long deployId)
    {
        return AjaxResult.success(formDeploymentService.selectFormDeploymentById(deployId));
    }

    /**
     * 新增表单部署
     */
//    @PreAuthorize(hasPermi = "formEngine:deployment:add")
//    @Log(title = "表单部署", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FormDeployment formDeployment)
    {
        return toAjax(formDeploymentService.insertFormDeployment(formDeployment));
    }

    /**
     * 修改表单部署
     */
//    @PreAuthorize(hasPermi = "formEngine:deployment:edit")
//    @Log(title = "表单部署", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FormDeployment formDeployment)
    {
        return toAjax(formDeploymentService.updateFormDeployment(formDeployment));
    }

    /**
     * 删除表单部署
     */
//    @PreAuthorize(hasPermi = "formEngine:deployment:remove")
//    @Log(title = "表单部署", businessType = BusinessType.DELETE)
	@DeleteMapping("/{deployIds}")
    public AjaxResult remove(@PathVariable Long[] deployIds)
    {
        return toAjax(formDeploymentService.deleteFormDeploymentByIds(deployIds));
    }
}
