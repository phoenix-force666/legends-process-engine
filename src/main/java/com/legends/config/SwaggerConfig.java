package com.legends.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
@ComponentScan(basePackages = {"com.legends.process.engine"})
@Primary
public class SwaggerConfig implements SwaggerResourcesProvider {
//    @Bean
//    public Docket swaggerPluggin() {
//        return new Docket(DocumentationType.SWAGGER_2)
//        		.globalOperationParameters(createGlobalParams())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.legends.process.engine"))
//                .paths(PathSelectors.any())
//                .build()
//                .apiInfo(new ApiInfoBuilder()
//                        .title("legends process engine 服务列表")
//                        .description("©2020 Copyright. Powered By legends.")
//                        .contact(new Contact("herion", "", "herionZhang@126.com"))
//                        .license("legends process engine")
//                        .build())
//                .useDefaultResponseMessages(false);
//    }
    
    private List<Parameter> createGlobalParams(){
        List<Parameter> aParameters = new ArrayList<Parameter>();
        
        //可以添加多个header或参数
        ParameterBuilder aParameterBuilder = new ParameterBuilder();
        aParameterBuilder
                //参数类型支持header, cookie, body, query etc
                .parameterType("header")
                //参数名
                .name("camundaToken")
                //默认值
                .defaultValue("camundaToken#000")
                .description("header中camundaToken字段")
                //指定参数值的类型
                .modelRef(new ModelRef("string"))
                //非必需，这里是全局配置，然而在登陆的时候是不用验证的
                .required(false).build();


        aParameters.add(aParameterBuilder.build());
        return aParameters;
    }


    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<SwaggerResource>();
        resources.add(swaggerResource("legends-process-engine", "/v2/api-docs", "1.0"));
        resources.add(swaggerResource("camunda","/swagger.json","1.0"));
        return resources;
    }

    private SwaggerResource swaggerResource(String name,String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}
