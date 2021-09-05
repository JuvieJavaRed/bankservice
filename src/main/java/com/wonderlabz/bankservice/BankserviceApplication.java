package com.wonderlabz.bankservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@ComponentScan("com.wonderlabz.bankservice")
public class BankserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankserviceApplication.class, args);
	}

}
