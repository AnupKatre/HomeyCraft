package com.stackroute.buyeruserservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@SpringBootApplication
@EnableEurekaClient
//@EnableSwagger2WebMvc
public class BuyerUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuyerUserServiceApplication.class, args);
	}

}
