package com.legends.form.engine.service.impl;

import com.legends.form.engine.domain.FormEntity;
import com.legends.form.engine.service.IFormService;
import com.legends.process.engine.base.page.PageDomain;
import com.legends.process.engine.base.page.TableDataInfo;
import com.legends.process.engine.base.utils.MongoUtil;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;


@Service
@Slf4j
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

    @Override
    public FormEntity findById(String id) {
        return mongoTemplate.findById(id,FormEntity.class);
    }

    @Override
    public int upsertById(FormEntity formEntity) {
        if(Objects.isNull(formEntity)){
            log.error("参数为空或缺少必填项！！！");
            return 0;
        }
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(formEntity.getProcessDefId()));

        Update update = new Update();
        update.set("data", formEntity.getData());

        /**
         * 数据库有，就新增， 没有，就修改
         */
        UpdateResult updateResult=mongoTemplate.upsert(query, update, FormEntity.class);
        return (int)updateResult.getMatchedCount();
    }
}
