package com.sales;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
@EnableEurekaClient
public class SalesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalesServiceApplication.class, args);
	}
	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.sales"))
				.build();
				//.apiInfo(apidetails());
	}
	
//	private ApiInfo apidetails() {
//		return new ApiInfo("Sales Service API",
//				"API for Sales Management", 
//				"1.0", 
//				"free to use",
//				new Contact("Avinash", "http://localhost:8082/sales", "a@test.com"),
//				"API Licenese",
//				"http://localhost:8082/sales",
//				Collections.emptyList());
//	}

}
