public class Student {

    public String surname;
    private int id;
    private String name;

    public Student() {
    }

    public Student(int id, String name, String surname) {
        this.surname = surname;
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
