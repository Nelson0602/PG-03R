package controller;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.MillerRabinResult;
import model.Probabilistic;
import util.BigIntegerSpinnerValueFactory;
import model.PainterMillerRabin;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;

public class MainController {

    @FXML private Spinner<BigInteger> spParams;
    @FXML private Canvas canvasBin;
    @FXML private Button btnMillerRabin;
    @FXML private TextField txfBigInteger;
    @FXML private Button btnClean;
    @FXML private Button btnCleanField;
    @FXML private ListView<String> listViewOperations;
    @FXML private TableView<MillerRabinResult> tableResults;
    @FXML private TableColumn<MillerRabinResult, String> colNumber;
    @FXML private TableColumn<MillerRabinResult, String> colResult;

    // Valores para el Spinner
    private final BigInteger min = new BigInteger("1");
    private final BigInteger max = new BigInteger("999999999999999999");
    private final BigInteger initial = new BigInteger("1000000000000");
    private final BigInteger step = new BigInteger("1");
    private final SecureRandom random = new SecureRandom();

    // Instancia del pintor
    private PainterMillerRabin painter;

    @FXML
    public void initialize() {
        // Inicializamos el pintor pasándole el canvas inyectado por FXML
        painter = new PainterMillerRabin(canvasBin);

        spParams.setValueFactory(new BigIntegerSpinnerValueFactory(min, max, initial, step));

        spParams.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                txfBigInteger.setText(newVal.toString());
            }
        });

        txfBigInteger.textProperty().addListener((obs, oldVal, newVal) -> {
            painter.clear(); // Usamos el pintor para limpiar
        });

        setupTableColumns();

        btnMillerRabin.setOnAction(e -> runMillerRabin());
        btnClean.setOnAction(e -> reset());
        btnCleanField.setOnAction(e -> txfBigInteger.setText(""));
    }

    private void runMillerRabin() {
        String input = txfBigInteger.getText().trim();

        if (input.isEmpty()) {
            showAlert("Error", "Debe ingresar un número");
            return;
        }

        try {
            Probabilistic p = new Probabilistic();
            String result = p.millerRabin(input);
            boolean isPrime = result.contains("probably prime");

            // 1. Agregar al ListView (Historial completo)
            listViewOperations.getItems().add(input + " → " + (isPrime ? "✔ Primo" : "✘ No primo"));

            // 2. FILTRO: Solo agregar al TableView si es primo
            if (isPrime) {
                tableResults.getItems().add(
                        new MillerRabinResult(input, "Probablemente Primo")
                );
            }

            // 3. Dibujar usando el objeto painter
            painter.drawResult(input, isPrime);

        } catch (Exception e) {
            showAlert("Error", "Número inválido");
        }
    }

    private void setupTableColumns() {
        colNumber.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getNumber()));

        colResult.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getResult()));

        // Configuración de colores para la columna de resultados
        colResult.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item);
                    setStyle("-fx-background-color: #b6ffb3; -fx-text-fill: black;");
                }
            }
        });

        colNumber.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item);
                    setStyle("-fx-font-weight: bold;");
                }
            }
        });
    }

    @FXML
    private void generarAleatorio() {
        BigInteger rand = new BigInteger(50, random);
        txfBigInteger.setText(rand.toString());
    }

    private void reset() {
        listViewOperations.getItems().clear();
        tableResults.getItems().clear();
        painter.clear(); // Limpieza delegada al pintor
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static class HelloApplication extends Application {
        @Override
        public void start(Stage stage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
        }
    }
}