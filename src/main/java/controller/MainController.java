package controller;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.RandomSearchResult;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import model.MillerRabinResult;
import model.Probabilistic;
import util.BigIntegerSpinnerValueFactory;
import model.PainterMillerRabin;
import java.math.BigInteger;
import java.security.SecureRandom;
import model.LinkedList;
import model.LinkedListResult;
import model.ListException;
import model.DoublyLinkedList;
import model.DoublyLinkedListResult;
import model.Employee;
import javafx.scene.control.Slider;

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
    @FXML private Button btnGenerateMR;
    @FXML private Slider sliderSearchRange;
    @FXML private Button btnCleanRS;
    @FXML private Canvas canvasRandomSearch;
    @FXML private TextField lblArrayContext;
    @FXML private Label lblSearchStatus;
    @FXML private TextField txfSearchValue;
    @FXML private TextField txfMaxAttempts;
    @FXML private Button btnGenerateRS;
    @FXML private Button btnRunRandomSearch;
    @FXML private ListView<String> listViewRSLog;
    @FXML private TableView<RandomSearchResult> tableRandomResults;
    @FXML private TableColumn<RandomSearchResult, String> colRSValue;
    @FXML private TableColumn<RandomSearchResult, String> colRSIndex;
    @FXML private TableColumn<RandomSearchResult, String> colRSAttempts;
    @FXML private TableColumn<RandomSearchResult, String> colRSMaxAttempts;
    @FXML private Canvas canvasLinkedList;
    @FXML private TextField txfListRepresentation;
    @FXML private TableView<LinkedListResult> tableListData;
    @FXML private TableColumn<LinkedListResult, String> colListPos;
    @FXML private TableColumn<LinkedListResult, String> colListElement;
    @FXML private TableColumn<LinkedListResult, String> colListInsertion;
    @FXML private TextField txfListValue;
    @FXML private Button btnAddFirst;
    @FXML private Button btnAddLast;
    @FXML private Button btnSearchList;
    @FXML private Button btnRemoveList;
    @FXML private ListView<String> listViewListLog;
    @FXML private Canvas canvasDoublyList;
    @FXML private TextField txfDoublyRepresentation;
    @FXML private Label lblDoublyStatus;
    @FXML private TableView<DoublyLinkedListResult> tableDoublyData;
    @FXML private TableColumn<DoublyLinkedListResult, String> colDId;
    @FXML private TableColumn<DoublyLinkedListResult, String> colDName;
    @FXML private TableColumn<DoublyLinkedListResult, String> colDPosition;
    @FXML private TableColumn<DoublyLinkedListResult, String> colDHireDate;
    @FXML private Label lblLinkedListStatus;
    @FXML private Button btnCleanList;
    @FXML private TextField txfEmpId;
    @FXML private TextField txfEmpName;
    @FXML private ComboBox<String> cbJobPosition;
    @FXML private DatePicker dpHireDate;
    @FXML private Button btnDAdd;
    @FXML private Button btnDSearch;
    @FXML private Button btnDCleanForm;
    @FXML private Button btnDFirst;
    @FXML private Button btnDLast;
    @FXML private Button btnDPrev;
    @FXML private Button btnDNext;
    @FXML private Button btnDRemove;
    @FXML private Button btnDRemoveFirst;
    @FXML private Button btnDRemoveLast;
    @FXML private ListView<String> listViewDoublyLog;


    // Valores para el Spinner
    private final BigInteger min = new BigInteger("1");
    private final BigInteger max = new BigInteger("999999999999999999");
    private final BigInteger initial = new BigInteger("1000000000000");
    private final BigInteger step = new BigInteger("1");
    private final SecureRandom random = new SecureRandom();
    private int[] randomSearchArray;
    private LinkedList<String> linkedList;
    private java.util.ArrayList<String> linkedInsertionInfo;
    private DoublyLinkedList<Employee> doublyEmployeeList;

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
        btnGenerateMR.setOnAction(e -> generarAleatorio());
        btnClean.setOnAction(e -> reset());
        btnCleanField.setOnAction(e -> txfBigInteger.setText(""));

        setupRandomSearchTable();
        setupRandomSearch();

        setupDoublyLinkedListTable();
        setupDoublyLinkedList();

        setupLinkedListTable();
        setupLinkedList();

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

    private void setupRandomSearchTable() {
        colRSValue.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getValue()));

        colRSIndex.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getIndex()));

        colRSAttempts.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getAttempts()));

        colRSMaxAttempts.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getMaxAttempts()));
    }

    private void setupRandomSearch() {
        sliderSearchRange.setMin(0);
        sliderSearchRange.setMax(100);
        sliderSearchRange.setValue(20);
        sliderSearchRange.setMajorTickUnit(5);
        sliderSearchRange.setMinorTickCount(0);
        sliderSearchRange.setShowTickLabels(true);
        sliderSearchRange.setShowTickMarks(true);
        sliderSearchRange.setSnapToTicks(true);

        txfMaxAttempts.setText("20");

        btnGenerateRS.setOnAction(e -> generateRandomSearchArray());
        btnRunRandomSearch.setOnAction(e -> runRandomSearch());
        btnCleanRS.setOnAction(e -> cleanRandomSearch());

        generateRandomSearchArray();
    }

    private void generateRandomSearchArray() {
        int size = (int) sliderSearchRange.getValue();
        randomSearchArray = new int[size];

        if (size < 10) {
            size = 10;
        }

        for (int i = 0; i < randomSearchArray.length; i++) {
            int value;
            boolean repeated;

            do {
                value = random.nextInt(size * 2) + 1;
                repeated = false;

                for (int j = 0; j < i; j++) {
                    if (randomSearchArray[j] == value) {
                        repeated = true;
                        break;
                    }
                }
            } while (repeated);

            randomSearchArray[i] = value;
        }

        int randomIndex = random.nextInt(randomSearchArray.length);
        txfSearchValue.setText(String.valueOf(randomSearchArray[randomIndex]));

        tableRandomResults.getItems().clear();
        listViewRSLog.getItems().clear();
        lblSearchStatus.setVisible(false);

        updateArrayContext();
        drawRandomArray(-1, -1);
    }

    private void cleanRandomSearch() {
        tableRandomResults.getItems().clear();
        listViewRSLog.getItems().clear();
        lblSearchStatus.setVisible(false);
        drawRandomArray(-1, -1);
    }

    private void runRandomSearch() {
        if (randomSearchArray == null || randomSearchArray.length == 0) {
            showAlert("Error", "Debe generar un arreglo primero");
            return;
        }

        try {
            int value = Integer.parseInt(txfSearchValue.getText().trim());
            int attempts = Integer.parseInt(txfMaxAttempts.getText().trim());

            if (attempts <= 0) {
                showAlert("Error", "El número de intentos debe ser mayor que cero");
                return;
            }

            Probabilistic p = new Probabilistic();
            int[] result = p.randomSearch(randomSearchArray, value, attempts);

            String indexText = result[0] == -1 ? "No encontrado" : String.valueOf(result[0]);

            tableRandomResults.getItems().add(
                    new RandomSearchResult(
                            String.valueOf(value),
                            indexText,
                            String.valueOf(result[1]),
                            String.valueOf(attempts)
                    )
            );

            if (result[0] != -1) {
                lblSearchStatus.setText("VALOR ENCONTRADO EN EL ÍNDICE " + result[0]);
                lblSearchStatus.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
                listViewRSLog.getItems().add("Item [" + value + "] encontrado en el índice " + result[0]);
                listViewRSLog.getItems().add("Intentos realizados: " + result[1]);
            } else {
                lblSearchStatus.setText("VALOR NO ENCONTRADO");
                lblSearchStatus.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
                listViewRSLog.getItems().add("Item [" + value + "] no encontrado");
                listViewRSLog.getItems().add("Intentos realizados: " + result[1]);
            }

            lblSearchStatus.setVisible(true);
            drawRandomArray(value, result[0]);

        } catch (NumberFormatException e) {
            showAlert("Error", "El valor y los intentos deben ser números enteros");
        }
    }

    private void updateArrayContext() {
        StringBuilder sb = new StringBuilder("[");
        int limit = Math.min(randomSearchArray.length, 12);

        for (int i = 0; i < limit; i++) {
            sb.append(randomSearchArray[i]);

            if (i < limit - 1) {
                sb.append(", ");
            }
        }

        if (randomSearchArray.length > limit) {
            sb.append(", ...");
        }

        sb.append("] (n=").append(randomSearchArray.length).append(")");

        lblArrayContext.setText(sb.toString());
    }

    private void drawRandomArray(int value, int foundIndex) {
        GraphicsContext gc = canvasRandomSearch.getGraphicsContext2D();
        gc.clearRect(0, 0, canvasRandomSearch.getWidth(), canvasRandomSearch.getHeight());

        gc.setFill(Color.web("#f2f2f2"));
        gc.fillRect(0, 0, canvasRandomSearch.getWidth(), canvasRandomSearch.getHeight());

        double boxWidth = 60;
        double boxHeight = 58;
        double gap = 0;
        double y = 150;

        int visible = (int) ((canvasRandomSearch.getWidth() - 40) / (boxWidth + gap));
        int start = 0;

        if (foundIndex >= visible) {
            start = foundIndex - visible / 2;

            if (start + visible > randomSearchArray.length) {
                start = randomSearchArray.length - visible;
            }
        }

        if (start < 0) {
            start = 0;
        }

        int end = Math.min(randomSearchArray.length, start + visible);
        double totalWidth = (end - start) * boxWidth;
        double x = (canvasRandomSearch.getWidth() - totalWidth) / 2;

        gc.setFont(new Font("System", 13));
        gc.setFill(Color.BLACK);

        if (value != -1) {
            gc.fillText("Buscando valor: " + value, canvasRandomSearch.getWidth() / 2 - 65, 50);
        } else {
            gc.fillText("Buscando valor: -1", canvasRandomSearch.getWidth() / 2 - 65, 50);
        }

        if (foundIndex != -1) {
            gc.setFill(Color.GREEN);
            gc.fillText("¡VALOR ENCONTRADO EN EL ÍNDICE " + foundIndex + "!", canvasRandomSearch.getWidth() / 2 - 120, 100);
        }

        for (int i = start; i < end; i++) {
            if (i == foundIndex) {
                gc.setFill(Color.LIGHTGREEN);
            } else {
                gc.setFill(Color.WHITE);
            }

            gc.fillRect(x, y, boxWidth, boxHeight);
            gc.setStroke(Color.GRAY);
            gc.setLineWidth(2);
            gc.strokeRect(x, y, boxWidth, boxHeight);

            gc.setFill(Color.BLACK);
            gc.fillText(String.valueOf(randomSearchArray[i]), x + 22, y + 35);

            gc.setFill(Color.GRAY);
            gc.fillText("[" + i + "]", x + 18, y + 78);

            x += boxWidth + gap;
        }
    }

    private void setupLinkedListTable() {
        colListPos.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getPosition()));

        colListElement.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getElement()));

        colListInsertion.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getInsertion()));
    }

    private void setupLinkedList() {
        linkedList = new LinkedList<>();
        linkedInsertionInfo = new java.util.ArrayList<>();

        btnAddFirst.setOnAction(e -> addLinkedListFirst());
        btnAddLast.setOnAction(e -> addLinkedListLast());
        btnSearchList.setOnAction(e -> searchLinkedListValue());
        btnRemoveList.setOnAction(e -> removeLinkedListValue());
        btnCleanList.setOnAction(e -> cleanLinkedList());
        txfListValue.setOnAction(e -> addLinkedListLast());

        addInitialLinkedListValues();
        refreshLinkedListView();
    }

    private void addInitialLinkedListValues() {
        linkedList.addFirst("10");
        linkedInsertionInfo.add(0, "Inicio");
        listViewListLog.getItems().add("addFirst(10) HEAD → [10] → ...");

        linkedList.addLast("20");
        linkedInsertionInfo.add("Final");
        listViewListLog.getItems().add("addLast(20) → ... → [20] → NULL");

        linkedList.addFirst("30");
        linkedInsertionInfo.add(0, "Inicio");
        listViewListLog.getItems().add("addFirst(30) HEAD → [30] → ...");

        linkedList.addLast("40");
        linkedInsertionInfo.add("Final");
        listViewListLog.getItems().add("addLast(40) → ... → [40] → NULL");

        linkedList.addFirst("50");
        linkedInsertionInfo.add(0, "Inicio");
        listViewListLog.getItems().add("addFirst(50) HEAD → [50] → ...");

        lblLinkedListStatus.setText("Insertado al inicio: 50");
    }

    private void addLinkedListFirst() {
        String value = txfListValue.getText().trim();

        if (value.isEmpty()) {
            showAlert("Error", "Debe ingresar un valor");
            return;
        }

        linkedList.addFirst(value);
        linkedInsertionInfo.add(0, "Inicio");
        listViewListLog.getItems().add("addFirst(" + value + ") HEAD → [" + value + "] → ...");
        lblLinkedListStatus.setText("Insertado al inicio: " + value);
        txfListValue.clear();
        refreshLinkedListView();
    }

    private void addLinkedListLast() {
        String value = txfListValue.getText().trim();

        if (value.isEmpty()) {
            showAlert("Error", "Debe ingresar un valor");
            return;
        }

        linkedList.addLast(value);
        linkedInsertionInfo.add("Final");
        listViewListLog.getItems().add("addLast(" + value + ") → ... → [" + value + "] → NULL");
        lblLinkedListStatus.setText("Insertado al final: " + value);
        txfListValue.clear();
        refreshLinkedListView();
    }

    private void searchLinkedListValue() {
        String value = txfListValue.getText().trim();

        if (value.isEmpty()) {
            showAlert("Error", "Debe ingresar un valor para buscar");
            return;
        }

        int index = linkedList.indexOf(value);

        if (index == -1) {
            listViewListLog.getItems().add("search(" + value + ") no encontrado");
            lblLinkedListStatus.setText("No encontrado: " + value);
        } else {
            listViewListLog.getItems().add("search(" + value + ") encontrado en posición " + index);
            listViewListLog.getItems().add("Anterior: " + linkedList.getPrev(value));
            listViewListLog.getItems().add("Siguiente: " + linkedList.getNext(value));
            lblLinkedListStatus.setText("Encontrado en posición " + index + ": " + value);
        }

        drawLinkedList(value);
    }

    private void removeLinkedListValue() {
        String value = txfListValue.getText().trim();

        if (value.isEmpty()) {
            showAlert("Error", "Debe ingresar un valor para eliminar");
            return;
        }

        int index = linkedList.indexOf(value);

        if (index == -1) {
            listViewListLog.getItems().add("remove(" + value + ") no existe");
            lblLinkedListStatus.setText("No se pudo eliminar: " + value);
            return;
        }

        try {
            linkedList.remove(value);
            linkedInsertionInfo.remove(index - 1);
            listViewListLog.getItems().add("remove(" + value + ") eliminado");
            lblLinkedListStatus.setText("Eliminado: " + value);
            txfListValue.clear();
            refreshLinkedListView();
        } catch (ListException e) {
            showAlert("Error", e.getMessage());
        }
    }

    private void cleanLinkedList() {
        linkedList.clear();
        linkedInsertionInfo.clear();
        tableListData.getItems().clear();
        listViewListLog.getItems().clear();
        txfListRepresentation.clear();
        lblLinkedListStatus.setText("Lista vacía");
        drawLinkedList("");
    }

    private void refreshLinkedListView() {
        txfListRepresentation.setText(linkedList.toString());
        tableListData.getItems().clear();

        for (int i = 1; i <= linkedList.size(); i++) {
            String insertion = "";

            if (i - 1 < linkedInsertionInfo.size()) {
                insertion = linkedInsertionInfo.get(i - 1);
            }

            tableListData.getItems().add(
                    new LinkedListResult(
                            String.valueOf(i),
                            linkedList.get(i),
                            insertion
                    )
            );
        }

        drawLinkedList("");
    }

    private void drawLinkedList(String selectedValue) {
        GraphicsContext gc = canvasLinkedList.getGraphicsContext2D();
        gc.clearRect(0, 0, canvasLinkedList.getWidth(), canvasLinkedList.getHeight());

        gc.setFill(Color.web("#f2f2f2"));
        gc.fillRect(0, 0, canvasLinkedList.getWidth(), canvasLinkedList.getHeight());

        double x = 35;
        double y = 80;
        double width = 80;
        double height = 50;
        double gap = 50;

        gc.setFont(new Font("System", 14));

        if (linkedList.isEmpty()) {
            gc.setFill(Color.BLACK);
            gc.fillText("HEAD → NULL", 35, 80);
            return;
        }

        for (int i = 1; i <= linkedList.size(); i++) {
            String value = linkedList.get(i);

            if (value.equals(selectedValue)) {
                gc.setFill(Color.LIGHTGREEN);
            } else {
                gc.setFill(Color.web("#1F3868"));
            }

            gc.fillRect(x, y, width, height);

            gc.setFill(Color.WHITE);
            gc.fillText(value, x + 30, y + 30);

            if (i < linkedList.size()) {
                double startX = x + width;
                double endX = x + width + gap;

                gc.setStroke(Color.web("#E8A020"));
                gc.setLineWidth(2);
                gc.strokeLine(startX, y + height / 2, endX, y + height / 2);
                gc.strokeLine(endX - 8, y + height / 2 - 5, endX, y + height / 2);
                gc.strokeLine(endX - 8, y + height / 2 + 5, endX, y + height / 2);
            } else {
                gc.setFill(Color.WHITE);
                gc.fillText("null", x + width + 5, y + 30);
            }

            x += width + gap;
        }
    }

    private void setupDoublyLinkedListTable() {
        colDId.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getId()));

        colDName.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));

        colDPosition.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getPosition()));

        colDHireDate.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getHireDate()));
    }

    private void setupDoublyLinkedList() {
        doublyEmployeeList = new DoublyLinkedList<>();

        btnDAdd.setOnAction(e -> addDoublyEmployee());
        btnDSearch.setOnAction(e -> searchDoublyEmployee());
        btnDCleanForm.setOnAction(e -> clearDoublyForm());
        btnDFirst.setOnAction(e -> showFirstDoublyEmployee());
        btnDLast.setOnAction(e -> showLastDoublyEmployee());
        btnDPrev.setOnAction(e -> showPrevDoublyEmployee());
        btnDNext.setOnAction(e -> showNextDoublyEmployee());
        btnDRemove.setOnAction(e -> removeDoublyEmployee());
        btnDRemoveFirst.setOnAction(e -> removeFirstDoublyEmployee());
        btnDRemoveLast.setOnAction(e -> removeLastDoublyEmployee());

        cbJobPosition.getSelectionModel().selectFirst();

        addInitialDoublyEmployees();
        refreshDoublyLinkedListView("");
    }

    private void addInitialDoublyEmployees() {
        doublyEmployeeList.add(new Employee("1", "Carlos", 0, 0, 0, "Informático/a", "2024-01-10"));
        doublyEmployeeList.add(new Employee("2", "María", 0, 0, 0, "Doctor/a", "2023-06-15"));
        doublyEmployeeList.add(new Employee("3", "José", 0, 0, 0, "Docente", "2022-03-20"));
    }

    private void addDoublyEmployee() {
        String id = txfEmpId.getText().trim();
        String name = txfEmpName.getText().trim();
        String position = cbJobPosition.getValue();
        String hireDate = dpHireDate.getValue() == null ? "" : dpHireDate.getValue().toString();

        if (id.isEmpty() || name.isEmpty() || position == null || hireDate.isEmpty()) {
            showAlert("Error", "Debe completar todos los datos del empleado");
            return;
        }

        if (findEmployeeById(id) != null) {
            showAlert("Error", "Ya existe un empleado con ese id");
            return;
        }

        Employee employee = new Employee(id, name, 0, 0, 0, position, hireDate);
        doublyEmployeeList.add(employee);

        listViewDoublyLog.getItems().add("Se agregó el empleado [" + id + "] " + name);
        lblDoublyStatus.setText("Insertado item: " + id);

        clearDoublyForm();
        refreshDoublyLinkedListView(id);
    }

    private void searchDoublyEmployee() {
        String id = txfEmpId.getText().trim();

        if (id.isEmpty()) {
            showAlert("Error", "Debe ingresar el id del empleado");
            return;
        }

        Employee employee = findEmployeeById(id);

        if (employee == null) {
            listViewDoublyLog.getItems().add("No se encontró el empleado con id [" + id + "]");
            lblDoublyStatus.setText("Empleado no encontrado: " + id);
            refreshDoublyLinkedListView("");
            return;
        }

        int index = doublyEmployeeList.indexOf(employee);

        listViewDoublyLog.getItems().add("Empleado encontrado en la posición " + index);
        listViewDoublyLog.getItems().add("Id: " + employee.getId() + ", Nombre: " + employee.getName());
        lblDoublyStatus.setText("Empleado encontrado: " + id);

        refreshDoublyLinkedListView(id);
    }

    private void showFirstDoublyEmployee() {
        try {
            Employee employee = doublyEmployeeList.getFirst();
            listViewDoublyLog.getItems().add("Primero: " + employee.getId() + " - " + employee.getName());
            lblDoublyStatus.setText("Primero: " + employee.getId());
            refreshDoublyLinkedListView(employee.getId());
        } catch (ListException e) {
            showAlert("Error", e.getMessage());
        }
    }

    private void showLastDoublyEmployee() {
        try {
            Employee employee = doublyEmployeeList.getLast();
            listViewDoublyLog.getItems().add("Último: " + employee.getId() + " - " + employee.getName());
            lblDoublyStatus.setText("Último: " + employee.getId());
            refreshDoublyLinkedListView(employee.getId());
        } catch (ListException e) {
            showAlert("Error", e.getMessage());
        }
    }

    private void showPrevDoublyEmployee() {
        String id = txfEmpId.getText().trim();

        if (id.isEmpty()) {
            showAlert("Error", "Debe ingresar el id del empleado");
            return;
        }

        Employee employee = findEmployeeById(id);

        if (employee == null) {
            listViewDoublyLog.getItems().add("No existe el empleado con id [" + id + "]");
            return;
        }

        Employee prev = doublyEmployeeList.getPrev(employee);

        if (prev == null) {
            listViewDoublyLog.getItems().add("El empleado [" + id + "] no tiene anterior");
            lblDoublyStatus.setText("Sin anterior: " + id);
            return;
        }

        listViewDoublyLog.getItems().add("Anterior de [" + id + "]: " + prev.getId() + " - " + prev.getName());
        lblDoublyStatus.setText("Anterior: " + prev.getId());
        refreshDoublyLinkedListView(prev.getId());
    }

    private void showNextDoublyEmployee() {
        String id = txfEmpId.getText().trim();

        if (id.isEmpty()) {
            showAlert("Error", "Debe ingresar el id del empleado");
            return;
        }

        Employee employee = findEmployeeById(id);

        if (employee == null) {
            listViewDoublyLog.getItems().add("No existe el empleado con id [" + id + "]");
            return;
        }

        Employee next = doublyEmployeeList.getNext(employee);

        if (next == null) {
            listViewDoublyLog.getItems().add("El empleado [" + id + "] no tiene siguiente");
            lblDoublyStatus.setText("Sin siguiente: " + id);
            return;
        }

        listViewDoublyLog.getItems().add("Siguiente de [" + id + "]: " + next.getId() + " - " + next.getName());
        lblDoublyStatus.setText("Siguiente: " + next.getId());
        refreshDoublyLinkedListView(next.getId());
    }

    private void removeDoublyEmployee() {
        String id = txfEmpId.getText().trim();

        if (id.isEmpty()) {
            showAlert("Error", "Debe ingresar el id del empleado");
            return;
        }

        Employee employee = findEmployeeById(id);

        if (employee == null) {
            listViewDoublyLog.getItems().add("No se puede eliminar. No existe el empleado con id [" + id + "]");
            return;
        }

        try {
            doublyEmployeeList.remove(employee);
            listViewDoublyLog.getItems().add("Se eliminó el empleado [" + id + "]");
            lblDoublyStatus.setText("Eliminado: " + id);
            clearDoublyForm();
            refreshDoublyLinkedListView("");
        } catch (ListException e) {
            showAlert("Error", e.getMessage());
        }
    }

    private void removeFirstDoublyEmployee() {
        try {
            Employee employee = doublyEmployeeList.removeFirst();
            listViewDoublyLog.getItems().add("Se eliminó el primero: " + employee.getId() + " - " + employee.getName());
            lblDoublyStatus.setText("Eliminado primero: " + employee.getId());
            refreshDoublyLinkedListView("");
        } catch (ListException e) {
            showAlert("Error", e.getMessage());
        }
    }

    private void removeLastDoublyEmployee() {
        try {
            Employee employee = doublyEmployeeList.removeLast();
            listViewDoublyLog.getItems().add("Se eliminó el último: " + employee.getId() + " - " + employee.getName());
            lblDoublyStatus.setText("Eliminado último: " + employee.getId());
            refreshDoublyLinkedListView("");
        } catch (ListException e) {
            showAlert("Error", e.getMessage());
        }
    }

    private void clearDoublyForm() {
        txfEmpId.clear();
        txfEmpName.clear();
        cbJobPosition.getSelectionModel().selectFirst();
        dpHireDate.setValue(null);
    }

    private Employee findEmployeeById(String id) {
        for (int i = 1; i <= doublyEmployeeList.size(); i++) {
            Employee employee = doublyEmployeeList.get(i);

            if (employee.getId().equals(id)) {
                return employee;
            }
        }

        return null;
    }

    private void refreshDoublyLinkedListView(String selectedId) {
        tableDoublyData.getItems().clear();

        for (int i = 1; i <= doublyEmployeeList.size(); i++) {
            Employee employee = doublyEmployeeList.get(i);

            tableDoublyData.getItems().add(
                    new DoublyLinkedListResult(
                            employee.getId(),
                            employee.getName(),
                            employee.getJobPosition(),
                            employee.getHireDate()
                    )
            );
        }

        txfDoublyRepresentation.setText(doublyEmployeeList.toString());
        drawDoublyLinkedList(selectedId);
    }

    private void drawDoublyLinkedList(String selectedId) {
        GraphicsContext gc = canvasDoublyList.getGraphicsContext2D();
        gc.clearRect(0, 0, canvasDoublyList.getWidth(), canvasDoublyList.getHeight());

        double x = 30;
        double y = 90;
        double width = 90;
        double height = 45;
        double gap = 55;

        gc.setFont(new Font("System", 13));
        gc.setFill(Color.BLACK);
        gc.fillText("HEAD", x, y - 20);

        if (doublyEmployeeList.isEmpty()) {
            gc.fillText("NULL", x + 80, y - 20);
            return;
        }

        for (int i = 1; i <= doublyEmployeeList.size(); i++) {
            Employee employee = doublyEmployeeList.get(i);

            if (employee.getId().equals(selectedId)) {
                gc.setFill(Color.LIGHTGREEN);
            } else {
                gc.setFill(Color.web("#1F3868"));
            }

            gc.fillRect(x, y, width, height);
            gc.setStroke(Color.BLACK);
            gc.strokeRect(x, y, width, height);

            gc.setFill(Color.WHITE);
            gc.fillText(employee.getId(), x + 10, y + 18);
            gc.fillText(employee.getName(), x + 10, y + 35);

            gc.setFill(Color.BLACK);
            gc.fillText(String.valueOf(i), x + 38, y + 68);

            if (i < doublyEmployeeList.size()) {
                double startX = x + width;
                double endX = x + width + gap;

                gc.strokeLine(startX, y + 15, endX, y + 15);
                gc.strokeLine(endX - 8, y + 10, endX, y + 15);
                gc.strokeLine(endX - 8, y + 20, endX, y + 15);

                gc.strokeLine(endX, y + 32, startX, y + 32);
                gc.strokeLine(startX + 8, y + 27, startX, y + 32);
                gc.strokeLine(startX + 8, y + 37, startX, y + 32);
            } else {
                gc.strokeLine(x + width, y + height / 2, x + width + 35, y + height / 2);
                gc.fillText("NULL", x + width + 45, y + 28);
            }

            x += width + gap;
        }
    }


}