package com.legends.promethes.test;
import lombok.Data;

@Data
public class Root
{
    private String status;

    private String receiver;

    private Labels labels;

    private Annotations annotations;

    private String startsAt;

    private String endsAt;

    private String generatorURL;
}
