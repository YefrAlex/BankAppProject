package de.telran_yefralex.BankAppProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BankAppProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAppProjectApplication.class, args);
	}

}
