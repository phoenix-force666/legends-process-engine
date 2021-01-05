package com.legends.form.engine.service;


import com.legends.process.engine.base.page.PageDomain;
import com.legends.process.engine.base.page.TableDataInfo;
import com.legends.form.engine.domain.FormEntity;
import org.jvnet.hk2.annotations.Service;

import java.util.List;

@Service
public interface IFormService {
    public List<FormEntity> selectList(FormEntity formEntity,PageDomain pageDomain);

    public TableDataInfo findByPage(FormEntity formEntity, PageDomain pageDomain);

    public int save(FormEntity formEntity);

    public FormEntity findById(String id);

    public int upsertById(FormEntity formEntity);


}
