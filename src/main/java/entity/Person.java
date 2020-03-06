package entity;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    public Person(String name) {this.name = name;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public static List<Person> getInitialPersonList() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Peter Pan"));
        return persons;
    }
}
