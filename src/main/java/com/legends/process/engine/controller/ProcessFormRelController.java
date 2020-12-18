package com.legends.process.engine.controller;

import com.legends.process.engine.biz.ProcessFormRelBiz;
import com.legends.process.engine.domain.LgeProcessFormRel;
import com.legends.process.engine.service.ILgeProcessFormRelService;
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
 * 流程表单关联Controller
 * 
 * @author herion
 * @date 2020-12-16
 */
@RestController
@RequestMapping("/processFromRel")
public class ProcessFormRelController extends BaseController
{
    @Autowired
    private ILgeProcessFormRelService lgeProcessFormRelService;

    @Autowired
    private ProcessFormRelBiz processFormRelBiz;

    /**
     * 查询流程表单关联列表
     */
    @GetMapping("/list")
    public TableDataInfo list(LgeProcessFormRel lgeProcessFormRel)
    {
        startPage();
        List<LgeProcessFormRel> list = lgeProcessFormRelService.selectLgeProcessFormRelList(lgeProcessFormRel);
        return getDataTable(list);
    }

    /**
     * 导出流程表单关联列表
     */
    @PostMapping("/export")
    public void export(HttpServletResponse response, LgeProcessFormRel lgeProcessFormRel) throws IOException
    {
        List<LgeProcessFormRel> list = lgeProcessFormRelService.selectLgeProcessFormRelList(lgeProcessFormRel);
        ExcelUtil<LgeProcessFormRel> util = new ExcelUtil<LgeProcessFormRel>(LgeProcessFormRel.class);
        util.exportExcel(response, list, "processFromRel");
    }

    /**
     * 获取流程表单关联详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(lgeProcessFormRelService.selectLgeProcessFormRelById(id));
    }

    /**
     * 获取表单
     * @param processDeploymentId
     * @return
     */
    @RequestMapping(value = "/form/{processDeploymentId}", method = RequestMethod.GET)
    public AjaxResult getProcessFormByProcessDeploymentId(@PathVariable(value = "processDeploymentId") String processDeploymentId) {
        return AjaxResult.success(processFormRelBiz.getProcessFormByProcessDeploymentId(processDeploymentId));
    }


    /**
     * 新增流程表单关联
     */
    @PostMapping
    public AjaxResult add(@RequestBody LgeProcessFormRel lgeProcessFormRel)
    {
        return toAjax(lgeProcessFormRelService.insertLgeProcessFormRel(lgeProcessFormRel));
    }

    /**
     * 修改流程表单关联
     */
    @PutMapping
    public AjaxResult edit(@RequestBody LgeProcessFormRel lgeProcessFormRel)
    {
        return toAjax(lgeProcessFormRelService.updateLgeProcessFormRel(lgeProcessFormRel));
    }

    /**
     * 删除流程表单关联
     */
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(lgeProcessFormRelService.deleteLgeProcessFormRelByIds(ids));
    }
}
