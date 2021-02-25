package com.nagarro.servicecatalogue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ServiceCatalogueApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceCatalogueApplication.class, args);
	}

}
