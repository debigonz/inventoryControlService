package com.inventory.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsInventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsInventoryServiceApplication.class, args);
	}

}
