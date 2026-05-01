package model;

import javafx.beans.property.SimpleStringProperty;

public class RandomSearchResult {

    private final SimpleStringProperty value;
    private final SimpleStringProperty index;
    private final SimpleStringProperty attempts;
    private final SimpleStringProperty maxAttempts;

    public RandomSearchResult(String value, String index, String attempts, String maxAttempts) {
        this.value = new SimpleStringProperty(value);
        this.index = new SimpleStringProperty(index);
        this.attempts = new SimpleStringProperty(attempts);
        this.maxAttempts = new SimpleStringProperty(maxAttempts);
    }

    public String getValue() {
        return value.get();
    }

    public String getIndex() {
        return index.get();
    }

    public String getAttempts() {
        return attempts.get();
    }

    public String getMaxAttempts() {
        return maxAttempts.get();
    }
}
