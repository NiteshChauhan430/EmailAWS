package com.example.EmailServices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@ComponentScan(basePackages = "com.*")
@SpringBootApplication
@EnableScheduling
public class EmailServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailServicesApplication.class, args);
	}

	@Scheduled(fixedRate = 3000)
	public void fixedRateSch() {
		EmailSender.sendMail();
		SmsSender.sendSMS();
	}

}
