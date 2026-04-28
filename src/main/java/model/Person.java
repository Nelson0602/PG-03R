package model;

public abstract class Person {
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getHeigth() {
        return heigth;
    }

    public double getWeight() {
        return weight;
    }

    public Person(String id, String name, int age, double heigth, double weight) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.heigth = heigth;
        this.weight = weight;
    }

    private String id;
    private String name;
    private int age;
    private double heigth;
    private double  weight;



    public abstract String getRoleDescription();



    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", heigth='" + heigth + '\'' +
                ", weight='" + weight + '\'' +
                '}';
    }
}
