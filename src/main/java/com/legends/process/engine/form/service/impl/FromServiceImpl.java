package com.legends.process.engine.form.service.impl;

import com.legends.process.engine.base.page.PageDomain;
import com.legends.process.engine.base.page.TableDataInfo;
import com.legends.process.engine.base.utils.MongoUtil;
import com.legends.process.engine.form.domain.FormEntity;
import com.legends.process.engine.form.service.IFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;


@Service
public class FromServiceImpl implements IFormService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Resource
    private MongoUtil mongoUtil;

    @Override
    public List<FormEntity> selectList(FormEntity formEntity,PageDomain pageDomain) {
        return mongoTemplate.findAll(FormEntity.class);
    }


    /**
     * 分页查询
     */
    @Override
    public TableDataInfo findByPage(FormEntity formEntity,PageDomain pageDomain) {
        Query query = new Query(new Criteria());
        mongoUtil.start(pageDomain, query);

        List<FormEntity> formEntitys = mongoTemplate.find(query, FormEntity.class);
        long count = mongoTemplate.count(query, FormEntity.class);
        return mongoUtil.resp(formEntitys,count);
    }

    @Override
    public int save(FormEntity formEntity) {
        FormEntity result=mongoTemplate.save(formEntity);
        int i=1;
        if(Objects.isNull(result)){
            i=0;
        }
        return i;
    }
}
