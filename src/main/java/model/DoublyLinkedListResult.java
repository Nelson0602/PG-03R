package model;

import javafx.beans.property.SimpleStringProperty;

public class DoublyLinkedListResult {

    private final SimpleStringProperty id;
    private final SimpleStringProperty name;
    private final SimpleStringProperty position;
    private final SimpleStringProperty hireDate;

    public DoublyLinkedListResult(String id, String name, String position, String hireDate) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.position = new SimpleStringProperty(position);
        this.hireDate = new SimpleStringProperty(hireDate);
    }

    public String getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public String getPosition() {
        return position.get();
    }

    public String getHireDate() {
        return hireDate.get();
    }
}
