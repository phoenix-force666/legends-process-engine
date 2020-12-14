package com.legends.process.engine.mongodb;

import com.alibaba.fastjson.JSONObject;
import com.legends.LegendsProcessEngineApplication;
import com.legends.process.engine.dao.DemoDao;
import com.legends.process.engine.entity.DemoEntity;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LegendsProcessEngineApplication.class)
public class SpringBootMongodbApplicationTests {

    @Autowired
    private DemoDao demoDao;

    @Test
    public void saveDemoTest() {

        DemoEntity demoEntity = new DemoEntity();
        demoEntity.setId(1L);
        demoEntity.setTitle("Spring Boot 中使用 MongoDB");
        demoEntity.setDescription("描述字段");
        demoEntity.setBy("herion");
        demoEntity.setUrl("http://www.herion.com");

        demoDao.saveDemo(demoEntity);

        demoEntity = new DemoEntity();
        demoEntity.setId(2L);
        demoEntity.setTitle("Spring Boot 中使用 MongoDB");
        demoEntity.setDescription("描述字段");
        demoEntity.setBy("herion");
        demoEntity.setUrl("http://www.herion.com");

        demoDao.saveDemo(demoEntity);
    }

    @Test
    @Order(10)
    public void removeDemoTest() {
        demoDao.removeDemo(2L);
    }

    @Test
    public void updateDemoTest() {

        DemoEntity demoEntity = new DemoEntity();
        demoEntity.setId(1L);
        demoEntity.setTitle("Spring Boot 中使用 MongoDB 更新数据");
        demoEntity.setDescription("描述字段");
        demoEntity.setBy("herion");
        demoEntity.setUrl("http://www.herion.com");

        demoDao.updateDemo(demoEntity);
    }

    @Test
    public void findDemoByIdTest() {

        DemoEntity demoEntity = demoDao.findDemoById(1L);

        System.out.println(JSONObject.toJSONString(demoEntity));
    }
}
