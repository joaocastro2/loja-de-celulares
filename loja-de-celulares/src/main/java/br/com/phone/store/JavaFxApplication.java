package br.com.phone.store;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Setter;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class JavaFxApplication extends javafx.application.Application {

    @Setter
    private static ApplicationContext springContext;

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Load the FXML and use Spring to inject the controllers
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login/Login-Screen.fxml"));
        loader.setControllerFactory(springContext::getBean); // Injeção de dependências do Spring
        Parent root = loader.load();

        // Window configuration
        primaryStage.setTitle("phone-store - Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
