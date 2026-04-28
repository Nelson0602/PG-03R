package model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        star2(stage);
    }

    private void star2(Stage stage) throws IOException {
        // Corregido: Ruta completa del recurso según la estructura del proyecto
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/org/example/pg03r/main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1100, 720);
        
        // Corregido: Ruta completa del recurso CSS
        scene.getStylesheets().add(HelloApplication.class.getResource("/org/example/pg03r/styles.css").toExternalForm());
        
        stage.setTitle("PG-03 IF-3001 Algoritmos y Estructuras de Datos");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
