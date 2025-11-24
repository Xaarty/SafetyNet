package com.mikael.safetynet.Service;

import com.mikael.safetynet.model.Person;
import com.mikael.safetynet.repository.PersonRepository;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PersonServiceTest {
    PersonRepository mockRepo = mock(PersonRepository.class);
    PersonService service = new PersonService(mockRepo);

    @Test
    public void allPerson() throws IOException {
        List<Person> people = List.of(new Person());
        when(mockRepo.getAll()).thenReturn(people);

        List<Person> result = service.allPerson();

        assertEquals(1, result.size());
        verify(mockRepo).getAll();
    }

    @Test
    public void getByName() throws IOException {
        Person person = new Person();
        when(mockRepo.getByName("Jane", "Boyd")).thenReturn(person);

        Person result = service.getByName("Jane", "Boyd");

        assertEquals(person, result);
        verify(mockRepo).getByName("Jane", "Boyd");
    }

    @Test
    public void create() {
        Person person = new Person();
        when(mockRepo.save(person)).thenReturn(person);

        Person result = service.create(person);

        assertEquals(person, result);
        verify(mockRepo).save(person);
    }

    @Test
    public void update() throws IOException {
        Person person = new Person();
        when(mockRepo.update(person)).thenReturn(person);

        Person result = service.update(person);

        assertEquals(person, result);
        verify(mockRepo).update(person);
    }

    @Test
    public void delete() throws IOException {
        service.delete("Jane", "Boyd");
        verify(mockRepo).remove("Jane", "Boyd");
    }
}
