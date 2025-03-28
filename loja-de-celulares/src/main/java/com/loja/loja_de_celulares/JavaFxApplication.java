package com.loja.loja_de_celulares;

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
        // Carrega o FXML e usa o Spring para injetar os controladores
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login/Tela-Login.fxml"));
        loader.setControllerFactory(springContext::getBean); // Injeção de dependências do Spring
        Parent root = loader.load();

        // Configuração da janela
        primaryStage.setTitle("Loja de Celulares - Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
