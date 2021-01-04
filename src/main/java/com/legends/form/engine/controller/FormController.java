package com.legends.form.engine.controller;


import com.legends.process.engine.base.controller.BaseController;
import com.legends.process.engine.base.domain.AjaxResult;
import com.legends.process.engine.base.page.PageDomain;
import com.legends.process.engine.base.page.TableDataInfo;
import com.legends.form.engine.domain.FormEntity;
import com.legends.form.engine.service.IFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/form")
public class FormController extends BaseController {
    @Autowired
    private IFormService formService;

    /**
     * 查询列表
     */
    @GetMapping("/list")
    public TableDataInfo list(FormEntity formEntity) {
        PageDomain pageDomain=startMongoPage();
        List<FormEntity> list = formService.selectList(formEntity,pageDomain);
        return getDataTable(list);
    }

    /**
     * 查询表单
     * @param processDefId
     * @return
     */
    @GetMapping("/{processDefId}")
    public AjaxResult getFormByProcessDefId(@PathVariable(value = "processDefId") String processDefId) {
        return AjaxResult.success(formService.findById(processDefId));
    }

    /**
     * 分页
     * @param formEntity
     * @return
     */
    @GetMapping("/page")
    public TableDataInfo page(FormEntity formEntity) {
        PageDomain pageDomain=startMongoPage();
        return formService.findByPage(formEntity,pageDomain);
    }

    /**
     * 新增
     */
    @PostMapping
    public AjaxResult add(@RequestBody FormEntity formEntity){
        return toAjax(formService.save(formEntity));
    }

//    /**
//     * 修改
//     */
//    @PutMapping
//    public AjaxResult edit(@RequestBody SysGroup sysGroup)
//    {
//        return toAjax(sysGroupService.updateSysGroup(sysGroup));
//    }
//
//    /**
//     * 删除
//     */
//    @DeleteMapping("/{groupIds}")
//    public AjaxResult remove(@PathVariable Integer[] groupIds)
//    {
//        return toAjax(sysGroupService.deleteSysGroupByIds(groupIds));
//    }
}
