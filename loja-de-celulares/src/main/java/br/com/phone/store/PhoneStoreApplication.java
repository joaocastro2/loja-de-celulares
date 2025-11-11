package br.com.phone.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for launching the Phone Store Spring Boot application.
 *
 * <p>This class bootstraps the Spring context and starts the embedded server.
 * It serves as the entry point for the entire application.</p>
 */
@SpringBootApplication
public class PhoneStoreApplication {

    /**
     * Main method that starts the Spring Boot application.
     *
     * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        // Start the Spring context
        var context = SpringApplication.run(PhoneStoreApplication.class, args);
    }
}
