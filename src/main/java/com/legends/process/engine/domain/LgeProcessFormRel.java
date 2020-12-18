package com.legends.process.engine.domain;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 流程单关联对象 lge_process_form_rel
 *
 * @author herion
 * @date 2020-12-16
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("processDeploymentId", getProcessDeploymentId())
                .append("formDeployId", getFormDeployId())
                .append("formId", getFormId())
                .toString();
    }
}