package com.mikael.safetynet.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mikael.safetynet.model.Person;
import com.mikael.safetynet.util.DataReaderUtil;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public class PersonRepository {

    private final List<Person> persons;

    public PersonRepository() throws IOException {
        this(new DataReaderUtil(new ObjectMapper()));
    }

    public PersonRepository(DataReaderUtil reader) throws IOException {
        this.persons = reader.getAllPersons();
    }

    public List<Person> getAll() throws IOException {
        return persons;
    }

    public Person getByName(String firstName, String lastName) throws IOException {
        return persons.stream()
                .filter(persons
                        -> persons.getFirstName().equals(firstName)
                        && persons.getLastName().equals(lastName))
                .findFirst().orElseThrow(() -> new RuntimeException(
                "Person not found: " + firstName + " " + lastName));
    }

    public Person save(Person person) {
        persons.add(person);
        return person;
    }

    public Person update(Person person) throws IOException {
        Person personFound = getByName(person.getFirstName(), person.getLastName());
        personFound.setAddress(person.getAddress());
        personFound.setCity(person.getCity());
        personFound.setZip(person.getZip());
        personFound.setPhone(person.getPhone());
        personFound.setEmail(person.getEmail());
        return person;
    }

    public void remove(String fistName, String lastName) throws IOException {
        Person person = getByName(fistName, lastName);
        persons.remove(person);
    }
}
