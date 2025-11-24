package com.mikael.safetynet.Model;

import com.mikael.safetynet.model.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonTest {
        @Test
        public void testPerson() {
            Person person = new Person();
            person.setFirstName("John");
            person.setLastName("Boyd");
            person.setAddress("1509 Culver St");
            person.setCity("Culver");
            person.setZip("97451");
            person.setPhone("841-874-6512");
            person.setEmail("jaboyd@email.com");

            assertEquals("John", person.getFirstName());
            assertEquals("Boyd", person.getLastName());
            assertEquals("1509 Culver St", person.getAddress());
            assertEquals("Culver", person.getCity());
            assertEquals("97451", person.getZip());
            assertEquals("841-874-6512", person.getPhone());
            assertEquals("jaboyd@email.com", person.getEmail());
        }
    }
