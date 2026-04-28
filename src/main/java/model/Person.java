package model;

public abstract class Person {
    private String id;
    private String name;
    private String age;
    private String heigth;
    private String  weight;

    public Person(String id, String name, String age, String heigth, String weight) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.heigth = heigth;
        this.weight = weight;
    }

    public abstract String getRoleDescription();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHeigth() {
        return heigth;
    }

    public void setHeigth(String heigth) {
        this.heigth = heigth;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
