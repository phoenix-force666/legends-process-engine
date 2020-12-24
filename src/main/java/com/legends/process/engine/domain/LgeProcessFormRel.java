package com.legends.process.engine.domain;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 流程单关联对象 lge_process_form_rel
 *
 * @author herion
 * @date 2020-12-22
 */
public class LgeProcessFormRel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 流程发布ID */
    @Excel(name = "流程发布ID")
    private String processDeploymentId;

    /** 表单发布key */
    @Excel(name = "表单发布key")
    private Long formDeployId;

    /** 表单id */
    @Excel(name = "表单id")
    private String formId;

    /** 流程定义ID */
    @Excel(name = "流程定义ID")
    private String processDefId;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setProcessDeploymentId(String processDeploymentId)
    {
        this.processDeploymentId = processDeploymentId;
    }

    public String getProcessDeploymentId()
    {
        return processDeploymentId;
    }
    public void setFormDeployId(Long formDeployId)
    {
        this.formDeployId = formDeployId;
    }

    public Long getFormDeployId()
    {
        return formDeployId;
    }
    public void setFormId(String formId)
    {
        this.formId = formId;
    }

    public String getFormId()
    {
        return formId;
    }
    public void setProcessDefId(String processDefId)
    {
        this.processDefId = processDefId;
    }

    public String getProcessDefId()
    {
        return processDefId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("processDeploymentId", getProcessDeploymentId())
                .append("formDeployId", getFormDeployId())
                .append("formId", getFormId())
                .append("processDefId", getProcessDefId())
                .toString();
    }
}