package ru.ds.education.currency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
@EnableAutoConfiguration
public class CurrencyCbrAdapterApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyCbrAdapterApplication.class, args);
	}
}