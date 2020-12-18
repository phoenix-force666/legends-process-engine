package com.legends.process.engine.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.legends.process.engine.domain.LgeProcessFormRel;

import java.util.List;

/**
 * 流程表单关联Mapper接口
 * 
 * @author herion
 * @date 2020-12-16
 */
public interface LgeProcessFormRelMapper extends BaseMapper<LgeProcessFormRel>
{
    /**
     * 查询流程表单关联
     * 
     * @param id 流程表单关联ID
     * @return 流程表单关联
     */
    public LgeProcessFormRel selectLgeProcessFormRelById(Long id);

    public LgeProcessFormRel getProcessFormByProcessDeploymentId(String processDeploymentId);

    /**
     * 查询流程表单关联列表
     * 
     * @param lgeProcessFormRel 流程表单关联
     * @return 流程表单关联集合
     */
    public List<LgeProcessFormRel> selectLgeProcessFormRelList(LgeProcessFormRel lgeProcessFormRel);

    /**
     * 新增流程表单关联
     * 
     * @param lgeProcessFormRel 流程表单关联
     * @return 结果
     */
    public int insertLgeProcessFormRel(LgeProcessFormRel lgeProcessFormRel);

    /**
     * 修改流程表单关联
     * 
     * @param lgeProcessFormRel 流程表单关联
     * @return 结果
     */
    public int updateLgeProcessFormRel(LgeProcessFormRel lgeProcessFormRel);

    /**
     * 删除流程表单关联
     * 
     * @param id 流程表单关联ID
     * @return 结果
     */
    public int deleteLgeProcessFormRelById(Long id);

    /**
     * 批量删除流程表单关联
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteLgeProcessFormRelByIds(Long[] ids);
}
