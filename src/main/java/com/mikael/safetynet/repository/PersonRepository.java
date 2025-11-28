package com.mikael.safetynet.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mikael.safetynet.model.Person;
import com.mikael.safetynet.util.DataReaderUtil;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public class PersonRepository {

    private final DataReaderUtil reader;
    private final List<Person> persons;

    public PersonRepository( DataReaderUtil reader) throws IOException {
        this.reader = reader;
        this.persons = reader.getAllPersons();
    }

    public List<Person> getAll() {
        return persons;
    }

    public Person getByName(String firstName, String lastName) {
        return persons.stream()
                .filter(persons
                        -> persons.getFirstName().equals(firstName)
                        && persons.getLastName().equals(lastName))
                .findFirst().orElseThrow(() -> new RuntimeException(
                "Person not found: " + firstName + " " + lastName));
    }

    public Person save(Person person) {
        persons.add(person);
        reader.writeAllPersons(persons);
        return person;
    }

    public Person update(Person person) {
        Person personFound = getByName(person.getFirstName(), person.getLastName());
        personFound.setAddress(person.getAddress());
        personFound.setCity(person.getCity());
        personFound.setZip(person.getZip());
        personFound.setPhone(person.getPhone());
        personFound.setEmail(person.getEmail());

        reader.writeAllPersons(persons);
        return person;
    }

    public void remove(String fistName, String lastName) {
        Person person = getByName(fistName, lastName);
        persons.remove(person);
        reader.writeAllPersons(persons);
    }
}
