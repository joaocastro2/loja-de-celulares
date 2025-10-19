package br.com.phone.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PhoneStoreApplication {

	public static void main(String[] args) {
		// Start the Spring context
		var context = SpringApplication.run(PhoneStoreApplication.class, args);
	}
}
