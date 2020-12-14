package com.legends.process.engine.form.controller;


import com.legends.process.engine.base.controller.BaseController;
import com.legends.process.engine.base.domain.AjaxResult;
import com.legends.process.engine.base.page.PageDomain;
import com.legends.process.engine.base.page.TableDataInfo;
import com.legends.process.engine.form.domain.FormEntity;
import com.legends.process.engine.form.service.IFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/from")
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
     * 分页
     * @param formEntity
     * @return
     */
    @GetMapping("/page")
    public TableDataInfo page(FormEntity formEntity) {
        PageDomain pageDomain=startMongoPage();
        return formService.findByPage(formEntity,pageDomain);
    }



//
//    /**
//     * 导出列表
//     */
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, SysGroup sysGroup) throws IOException
//    {
//        List<SysGroup> list = sysGroupService.selectSysGroupList(sysGroup);
//        ExcelUtil<SysGroup> util = new ExcelUtil<SysGroup>(SysGroup.class);
//        util.exportExcel(response, list, "group");
//    }
//
//    /**
//     * 获取详细信息
//     */
//    @GetMapping(value = "/{groupId}")
//    public AjaxResult getInfo(@PathVariable("groupId") Integer groupId)
//    {
//        return AjaxResult.success(sysGroupService.selectSysGroupById(groupId));
//    }

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
