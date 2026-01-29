package fitness;

public class Person {
    private String name;
    private String surname;
    private int birthYear;

    public Person(String name, String surname, int birthYear) {
        this.name = name;
        this.surname = surname;
        this.birthYear = birthYear;
    }

    public String getFullName() {
        return name + " " + surname;
    }
}
