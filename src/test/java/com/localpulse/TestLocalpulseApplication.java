package com.localpulse;

import org.springframework.boot.SpringApplication;

public class TestLocalpulseApplication {

	public static void main(String[] args) {
		SpringApplication.from(LocalpulseApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
