package com.legends.process.engine.dao;

import com.legends.process.engine.entity.DemoEntity;

public interface DemoDao {

    void saveDemo(DemoEntity demoEntity);

    void removeDemo(Long id);

    void updateDemo(DemoEntity demoEntity);

    DemoEntity findDemoById(Long id);
}
