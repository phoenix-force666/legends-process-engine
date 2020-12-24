package com.legends.process.engine.enums;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author zero
 * @version 1.0
 * @title: LgeProcessEngine
 * @projectName legends-process-engine
 * @description: TODO
 * @date 2020/12/2015:39
 */
public enum LgeProcessEngine {
/*    INSTANCE;
    @Value("engine.jdbcUrl")
    private String jdbcUrl;
    @Value("engine.username")
    private String username;
    @Value("engine.password")
    private String password;
    private final ProcessEngine processEngine;
    LgeProcessEngine() {
        this.processEngine = ProcessEngineConfiguration
                .createStandaloneProcessEngineConfiguration()
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_FALSE)
                .setJdbcUrl(jdbcUrl)
                //.setJdbcUrl("jdbc:mariadb://127.0.0.1:3306/phoenix_camunda?useUnicode=true&characterEncoding=utf8mb4&allowMultiQueries=true")
                .setJdbcDriver("org.mysql.jc.jdbc.Driver")
                .setJdbcUsername(username)
                .setJdbcPassword(password)
                .setJobExecutorActivate(true)
                .setHistory("full")
                .buildProcessEngine();
    }
    public ProcessEngine getProcessEngine() {
        return processEngine;
    }*/
}
