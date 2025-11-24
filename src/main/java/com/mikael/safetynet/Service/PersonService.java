package com.mikael.safetynet.Service;

import com.mikael.safetynet.model.Person;
import com.mikael.safetynet.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> allPerson() throws IOException {
        return personRepository.getAll()
                .stream().toList();
    }

    public Person getByName(String firstName, String lastName) throws IOException {
        return personRepository.getByName(firstName, lastName);
    }

    public Person create(Person person) {
        return personRepository.save(person);
    }

    public Person update(Person person) throws IOException {
        return personRepository.update(person);
    }

    public void delete(String firstName, String lastName) throws IOException {
        personRepository.remove(firstName, lastName);
    }
}