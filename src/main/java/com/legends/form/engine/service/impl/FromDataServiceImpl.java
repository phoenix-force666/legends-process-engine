package com.legends.form.engine.service.impl;

import com.legends.process.engine.base.page.PageDomain;
import com.legends.process.engine.base.page.TableDataInfo;
import com.legends.process.engine.base.utils.MongoUtil;
import com.legends.form.engine.domain.FormDataEntity;
import com.legends.form.engine.service.IFormDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;


@Service
public class FromDataServiceImpl implements IFormDataService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Resource
    private MongoUtil mongoUtil;

    @Override
    public List<FormDataEntity> selectList(FormDataEntity formEntity, PageDomain pageDomain) {
        return mongoTemplate.findAll(FormDataEntity.class);
    }


    /**
     * 分页查询
     */
    @Override
    public TableDataInfo findByPage(FormDataEntity formDataEntity,PageDomain pageDomain) {
        Query query = new Query(new Criteria());
        mongoUtil.start(pageDomain, query);

        List<FormDataEntity> formDataEntitys = mongoTemplate.find(query, FormDataEntity.class);
        long count = mongoTemplate.count(query, FormDataEntity.class);
        return mongoUtil.resp(formDataEntitys,count);
    }

    @Override
    public int save(FormDataEntity formDataEntity) {
        FormDataEntity result=mongoTemplate.save(formDataEntity);
        int i=1;
        if(Objects.isNull(result)){
            i=0;
        }
        return i;
    }

    @Override
    public FormDataEntity findById(String id) {
        return mongoTemplate.findById(id,FormDataEntity.class);
    }
}
