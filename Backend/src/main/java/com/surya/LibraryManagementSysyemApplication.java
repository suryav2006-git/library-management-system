package com.surya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class LibraryManagementSysyemApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementSysyemApplication.class, args);
	}

}
