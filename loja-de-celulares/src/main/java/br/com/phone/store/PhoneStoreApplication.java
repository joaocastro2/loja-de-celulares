package br.com.phone.store;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PhoneStoreApplication {

	public static void main(String[] args) {
		// Inicia o contexto do Spring
		var context = SpringApplication.run(PhoneStoreApplication.class, args);

		// Passa o contexto para o JavaFX
		JavaFxApplication.setSpringContext(context);

		// Inicia a aplicação JavaFX
		Application.launch(JavaFxApplication.class, args);
	}
}
