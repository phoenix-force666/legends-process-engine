//package com.legends.process.engine.dao.impl;
//
//import com.legends.process.engine.dao.MongoBaseDao;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.stereotype.Component;
//
//import java.lang.reflect.ParameterizedType;
//import java.util.List;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//
//@Component
//public class MongoBaseDaoImpl<T> implements MongoBaseDao<T> {
//
//    private String collectionName;
//
//    private Class<T> entityClass;
//
//    public MongoBaseDaoImpl(String collectionName) {
//        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
//        this.entityClass = (Class<T>) pt.getActualTypeArguments()[0];
//        this.collectionName = collectionName;
//    }
//
//    @Autowired
//    private MongoTemplate mongoTemplate;
//
//    @Override
//    public List<T> findAll() {
//        return mongoTemplate.findAll(entityClass, collectionName);
//    }
//
//    @Override
//    public T findById(String id) {
//        return mongoTemplate.findById(id, entityClass, collectionName);
//    }
//
//    @Override
//    public void insert(T entity, String collectionName) {
//        mongoTemplate.save(entity,collectionName);
//    }
//    @Override
//    public void insertEntity(T entity) {
//        mongoTemplate.save(entity, collectionName);
//    }
//
//    @Override
//    public T getEntity(String name) {
//        Query query = new Query(Criteria.where(entityClass.getSimpleName() + "Name").is(name));
//        return mongoTemplate.findOne(query, entityClass, collectionName);
//    }
//
//    @Override
//    public void deleteEntity(String name) {
//        Query query = new Query(Criteria.where(entityClass.getSimpleName() + "Name").is(name));
//        mongoTemplate.remove(query, entityClass, collectionName);
//    }
//
//}
