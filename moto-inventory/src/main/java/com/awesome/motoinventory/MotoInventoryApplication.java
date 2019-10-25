package com.awesome.motoinventory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MotoInventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MotoInventoryApplication.class, args);
	}

}
