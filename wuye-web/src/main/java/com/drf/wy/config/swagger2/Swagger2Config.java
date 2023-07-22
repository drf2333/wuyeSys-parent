package com.drf.wy.config.swagger2;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Auther：杜瑞丰
 * @Date：2023/7/21 14:06
 * @Project_Name:wuyeSys-parent
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean//设置接口框架的基本信息
    public Docket createRestApi(){

        return new Docket(DocumentationType.SWAGGER_2)
                //接口描述信息
                .apiInfo(apiInfo())
                .select()
                //扫描哪些包创建接口文档
                .apis(RequestHandlerSelectors.basePackage("com.drf.wy"))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo (){
        return new ApiInfoBuilder()
                .title("接口文档")
                .description("物业系统接口文档")
                .contact(new Contact("杜瑞丰","http://127.0.0.1:8888/doc.html","389391481@qq.com"))
                .version("3.1")
                .build();
    }
}
