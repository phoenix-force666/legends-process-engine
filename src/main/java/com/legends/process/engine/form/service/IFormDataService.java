package com.legends.process.engine.form.service;


import com.legends.process.engine.base.page.PageDomain;
import com.legends.process.engine.base.page.TableDataInfo;
import com.legends.process.engine.form.domain.FormDataEntity;
import org.jvnet.hk2.annotations.Service;

import java.util.List;

@Service
public interface IFormDataService {
    public List<FormDataEntity> selectList(FormDataEntity formDataEntity, PageDomain pageDomain);

    public TableDataInfo findByPage(FormDataEntity formDataEntity, PageDomain pageDomain);

    public int save(FormDataEntity formDataEntity);

    public FormDataEntity findById(String id);
}
