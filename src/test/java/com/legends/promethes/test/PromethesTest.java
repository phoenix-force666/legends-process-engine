package com.legends.promethes.test;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class PromethesTest {
    public static void main(String[] args) {
        String url="http://192.181.4.48/alertmanager/api/v1/alerts";
        RestTemplate restTemplate=new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        //test:test 为用户密码
        String authorization=new String(Base64.getEncoder().encode("edsp:edsp".getBytes()));
        System.out.println(authorization);;
        // 若开启了用户认证需要在头添加
        headers.add("Authorization","Basic "+authorization);

        String content1 ="[\"id:69\"," +
                "\"name:UserBehavior-0--1193959466\"," +
                "\"Job name: Start workflow\"," +
                "\"State: FAILURE\"," +
                "\"Recovery: NO\"," +
                "\"Run time: 1\"," +
                "\"Start time: 2018-08-06 10:31:34\"," +
                "\"End time: 2018-08-06 10:31:49\"," +
                "\"Host: 192.168.xx.xx\"," +
                "\"Notify group :4\"]";



        String body="[\n" +
                "    {\n" +
                "      \"status\": \"firing\",\n" +
                "      \"receiver\": \"webhook\",\n" +
                "      \"labels\": {\n" +
                "        \"alertname\": \"测试告警\",\n" +
                "        \"application\": \"test\",\n" +
                "        \"host\": \"192.181.4.110\",\n" +
                "        \"instance\": \"test\",\n" +
                "        \"job\": \"legends\",\n" +
                "        \"namespace\": \"test\",\n" +
                "        \"product\": \"test\"\n" +
                "    },\n" +
                "      \"annotations\": {\n" +
                "        \"description\": \""+JSON.parseArray(content1).toJSONString().toString()+ "\" ,\n" +
                "        \"summary\": \"test down\"\n" +
                "    },\n" +
                "      \"startsAt\": \"2020-10-23T17:20:02.679354841+08:00\",\n" +
                "      \"endsAt\":  \"2020-12-02T10:32:47.686229825+08:00\",\n" +
                "      \"generatorURL\": \"http://localhost:9090/prometheus/graph?g0.expr=up+%3D%3D+0&g0.tab=1\"\n" +
                "    }\n" +
                "  ]";



        Root root=new Root();
        root.setStatus("firing");
        root.setReceiver("webhook");

        Annotations annotations1=new Annotations();
        annotations1.setDescription(content1);
        annotations1.setSummary("gaj");
        root.setAnnotations(annotations1);

        Labels labels1=new Labels();
        labels1.setAlertname("name");
        root.setLabels(labels1);

        List<Root> list=new ArrayList<>();
        list.add(root);
        System.out.println(JSON.toJSONString(list));

        HttpEntity entity = new HttpEntity<>(JSON.toJSONString(list), headers);
        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        System.out.println(result.getBody());

    }
}
