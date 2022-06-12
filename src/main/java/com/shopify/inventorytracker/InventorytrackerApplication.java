package com.shopify.inventorytracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class InventorytrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventorytrackerApplication.class, args);
	}

}
