package com.example.hospitalalert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class HospitalAlertApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalAlertApiApplication.class, args);
	}

}
