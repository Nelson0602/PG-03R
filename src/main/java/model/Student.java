package model;

public class Student extends Person{
    private String carne;


    public Student(String id, String name, int age, double heigth, double weight, String carne) {
        super(id, name, age, heigth, weight);
        this.carne = carne;
    }

    @Override
    public String getRoleDescription() {
        return "Estudiante, carne: " + carne;
    }

    @Override
    public String toString() {
        return super.toString()+  "\n"+ getRoleDescription();
    }
}
