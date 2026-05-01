package model;

public class Student extends Person {

    private String carne;

    public Student(String id, String name, int age, double height, double weight, String carne) {
        super(id, name, age, height, weight);
        this.carne = carne;
    }

    public Student(String id, String name, double weight, String age, double height, String carne) {
        super(id, name, Integer.parseInt(age), height, weight);
        this.carne = carne;
    }

    @Override
    public String getRoleDescription() {
        return "Estudiante, carné: " + carne;
    }

    public String getCarne() {
        return carne;
    }

    public void setCarne(String carne) {
        this.carne = carne;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
