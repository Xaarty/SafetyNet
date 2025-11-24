package com.mikael.safetynet.Repository;

import com.mikael.safetynet.model.Person;
import com.mikael.safetynet.repository.PersonRepository;
import com.mikael.safetynet.util.DataReaderUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PersonRepositoryTest {

    @Test
    public void getAll() throws IOException {
        Person person = new Person(); person.setFirstName("John"); person.setLastName("Boyd");
        DataReaderUtil reader = Mockito.mock(DataReaderUtil.class);
        Mockito.when(reader.getAllPersons()).thenReturn(List.of(person));

        PersonRepository repository = new PersonRepository(reader);
        List<Person> result = repository.getAll();

        assertEquals(1, result.size());
        assertEquals("John", result.getFirst().getFirstName());
    }

    @Test
    public void getByName() throws IOException {
        Person person = new Person(); person.setFirstName("John"); person.setLastName("Boyd");
        DataReaderUtil reader = Mockito.mock(DataReaderUtil.class);
        Mockito.when(reader.getAllPersons()).thenReturn(List.of(person));

        PersonRepository repository = new PersonRepository(reader);
        Person result = repository.getByName("John", "Boyd");

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
    }

    @Test
    public void save() throws IOException {
        Person person = new Person(); person.setFirstName("John"); person.setLastName("Boyd");
        DataReaderUtil reader = Mockito.mock(DataReaderUtil.class);
        Mockito.when(reader.getAllPersons()).thenReturn(new ArrayList<>());

        PersonRepository repository = new PersonRepository(reader);
        repository.save(person);

        assertEquals(1, repository.getAll().size());
    }

    @Test
    public void update() throws IOException {
        Person original = new Person(); original.setFirstName("John"); original.setLastName("Boyd"); original.setCity("1509 Culver St");
        DataReaderUtil reader = Mockito.mock(DataReaderUtil.class);
        Mockito.when(reader.getAllPersons()).thenReturn(new ArrayList<>(List.of(original)));

        PersonRepository repository = new PersonRepository(reader);
        Person updated = new Person(); updated.setFirstName("John"); updated.setLastName("Boyd"); updated.setCity("1508 Culver St");

        repository.update(updated);

        assertEquals("1508 Culver St", repository.getByName("John", "Boyd").getCity());
    }

    @Test
    public void remove() throws IOException {
        Person person = new Person(); person.setFirstName("John"); person.setLastName("Boyd");
        DataReaderUtil reader = Mockito.mock(DataReaderUtil.class);
        Mockito.when(reader.getAllPersons()).thenReturn(new ArrayList<>(List.of(person)));

        PersonRepository repository = new PersonRepository(reader);
        repository.remove("John", "Boyd");

        assertEquals(0, repository.getAll().size());
    }
}