package com.mikael.safetynet.Model;

import com.mikael.safetynet.model.Person;
import com.mikael.safetynet.model.Persons;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonsTest {

    @Test
    public void testPersons() {
        Person person = new Person();
        person.setFirstName("Boyd");
        Persons persons = new Persons();
        persons.setPersons(List.of(person));

        Assertions.assertNotNull(persons.getPersons());
        assertEquals("Boyd", persons.getPersons().getFirst().getFirstName());
    }
}
