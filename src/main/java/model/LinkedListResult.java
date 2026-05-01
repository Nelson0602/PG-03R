package model;

import javafx.beans.property.SimpleStringProperty;

public class LinkedListResult {

    private final SimpleStringProperty position;
    private final SimpleStringProperty element;
    private final SimpleStringProperty insertion;

    public LinkedListResult(String position, String element, String insertion) {
        this.position = new SimpleStringProperty(position);
        this.element = new SimpleStringProperty(element);
        this.insertion = new SimpleStringProperty(insertion);
    }

    public String getPosition() {
        return position.get();
    }

    public String getElement() {
        return element.get();
    }

    public String getInsertion() {
        return insertion.get();
    }
}
