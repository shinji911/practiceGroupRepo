package com.awsome.MotoInvEdge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MotoInvEdgeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MotoInvEdgeApplication.class, args);
	}

}
