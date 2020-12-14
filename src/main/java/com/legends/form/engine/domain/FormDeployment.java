package com.legends.form.engine.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 表单部署对象 form_deployment
 * 
 * @author herion
 * @date 2020-12-02
 */
public class FormDeployment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 表单发布ID */
    private Long deployId;

    /** 表单发布名称 */
    @Excel(name = "表单发布名称")
    private String deployName;

    /** 表单发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "表单发布时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deployTime;

    /** 发布状态（0正常 1停用） */
    @Excel(name = "发布状态", readConverterExp = "0=正常,1=停用")
    private Integer status;

    /** 表单租户 */
    @Excel(name = "表单租户")
    private Long tenantId;

    /** 表单来源 */
    @Excel(name = "表单来源")
    private String source;

    /** 创建人 */
    @Excel(name = "创建人")
    private Long createUser;

    /** 修改人 */
    @Excel(name = "修改人")
    private Long updateUser;

    /** 版本 */
    @Excel(name = "版本")
    private Integer version;

    public void setDeployId(Long deployId) 
    {
        this.deployId = deployId;
    }

    public Long getDeployId() 
    {
        return deployId;
    }
    public void setDeployName(String deployName) 
    {
        this.deployName = deployName;
    }

    public String getDeployName() 
    {
        return deployName;
    }
    public void setDeployTime(Date deployTime) 
    {
        this.deployTime = deployTime;
    }

    public Date getDeployTime() 
    {
        return deployTime;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setTenantId(Long tenantId) 
    {
        this.tenantId = tenantId;
    }

    public Long getTenantId() 
    {
        return tenantId;
    }
    public void setSource(String source) 
    {
        this.source = source;
    }

    public String getSource() 
    {
        return source;
    }
    public void setCreateUser(Long createUser) 
    {
        this.createUser = createUser;
    }

    public Long getCreateUser() 
    {
        return createUser;
    }
    public void setUpdateUser(Long updateUser) 
    {
        this.updateUser = updateUser;
    }

    public Long getUpdateUser() 
    {
        return updateUser;
    }
    public void setVersion(Integer version) 
    {
        this.version = version;
    }

    public Integer getVersion() 
    {
        return version;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("deployId", getDeployId())
            .append("deployName", getDeployName())
            .append("deployTime", getDeployTime())
            .append("status", getStatus())
            .append("tenantId", getTenantId())
            .append("source", getSource())
            .append("createUser", getCreateUser())
            .append("updateUser", getUpdateUser())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("version", getVersion())
            .toString();
    }
}
