package com.legends.process.engine.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "demo_collection")
@Data
public class DemoEntity implements Serializable {
    @Id
    private Long id;

    private String title;

    private String description;

    private String by;

    private String url;
	
}
