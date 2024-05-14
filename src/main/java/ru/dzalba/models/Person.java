package ru.dzalba.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class Person {

    private int person_id;
    @NotEmpty(message = "Name should not be empty")
    private String name;
    @Min(value = 0, message = "Year of birth should be greater than 0")
    private int yearOfBirth;

    public Person(int person_id, String name, int yearOfBirth) {
        this.person_id = person_id;
        this.name = name;
        this.yearOfBirth = yearOfBirth;
    }

    public Person() {
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
