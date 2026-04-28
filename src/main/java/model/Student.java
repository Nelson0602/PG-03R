package model;

public class Student extends Person{
    public String carne;

    public Student(String id, String name, double weight, String age, double heigth, String carne) {
        super(id, name, weight, age, heigth);
        this.carne = carne;
    }

    @Override
    public String getRoleDescription() {
        return "";
    }

    @Override
    public String toString() {
            return super.toString() + "\n" + getRoleDescription()
        }
    }
}
