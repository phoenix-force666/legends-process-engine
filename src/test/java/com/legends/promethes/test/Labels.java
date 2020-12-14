package com.legends.promethes.test;

import lombok.Data;

@Data
public class Labels {
    private String alertname;

    private String application;

    private String host;

    private String instance;

    private String job;

    private String namespace;

    private String product;
}