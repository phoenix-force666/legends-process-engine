package com.legends.form.engine.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 表单定义对象 form_definition
 * 
 * @author herion
 * @date 2020-12-02
 */
@TableName("form_definition")
public class FormDefinition extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 表单定义ID */
    private Long definitionId;

    /** 租户 */
    @Excel(name = "租户")
    private Integer tenantId;

    /** 表单定义名称 */
    @Excel(name = "表单定义名称")
    private String definitionName;

    /** 表单定义key */
    @Excel(name = "表单定义key")
    private String definitionKey;

    /** 表单定义描述 */
    @Excel(name = "表单定义描述")
    private String definitionDesc;

    /** 表单部署ID */
    @Excel(name = "表单部署ID")
    private Long deploymentId;

    /** 表单数据ID(存放在mongodb中） */
    @Excel(name = "表单数据ID(存放在mongodb中）")
    private String dataId;

    /** 表单定义分类 */
    @Excel(name = "表单定义分类")
    private Long categoryId;

    /** 创建人 */
    @Excel(name = "创建人")
    private Long createUser;

    /** 修改人 */
    @Excel(name = "修改人")
    private Long updateUser;

    /** 版本 */
    @Excel(name = "版本")
    private Integer version;

    public void setDefinitionId(Long definitionId) 
    {
        this.definitionId = definitionId;
    }

    public Long getDefinitionId() 
    {
        return definitionId;
    }
    public void setTenantId(Integer tenantId) 
    {
        this.tenantId = tenantId;
    }

    public Integer getTenantId() 
    {
        return tenantId;
    }
    public void setDefinitionName(String definitionName) 
    {
        this.definitionName = definitionName;
    }

    public String getDefinitionName() 
    {
        return definitionName;
    }
    public void setDefinitionKey(String definitionKey) 
    {
        this.definitionKey = definitionKey;
    }

    public String getDefinitionKey() 
    {
        return definitionKey;
    }
    public void setDefinitionDesc(String definitionDesc) 
    {
        this.definitionDesc = definitionDesc;
    }

    public String getDefinitionDesc() 
    {
        return definitionDesc;
    }
    public void setDeploymentId(Long deploymentId) 
    {
        this.deploymentId = deploymentId;
    }

    public Long getDeploymentId() 
    {
        return deploymentId;
    }
    public void setDataId(String dataId) 
    {
        this.dataId = dataId;
    }

    public String getDataId() 
    {
        return dataId;
    }
    public void setCategoryId(Long categoryId) 
    {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() 
    {
        return categoryId;
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
            .append("definitionId", getDefinitionId())
            .append("tenantId", getTenantId())
            .append("definitionName", getDefinitionName())
            .append("definitionKey", getDefinitionKey())
            .append("definitionDesc", getDefinitionDesc())
            .append("deploymentId", getDeploymentId())
            .append("dataId", getDataId())
            .append("categoryId", getCategoryId())
            .append("createUser", getCreateUser())
            .append("updateUser", getUpdateUser())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("version", getVersion())
            .toString();
    }
}
