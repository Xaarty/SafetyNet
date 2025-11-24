package com.mikael.safetynet.model;

import java.util.List;

public class Persons {
    private List<Person> persons;

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public String toString() {
        return "Persons{" +
                "persons=" + persons +
                '}';
    }
}
