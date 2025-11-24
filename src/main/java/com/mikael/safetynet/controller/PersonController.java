package com.mikael.safetynet.controller;
import com.mikael.safetynet.model.Person;
import com.mikael.safetynet.Service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> all() throws IOException {
        return personService.allPerson();
    }

    @GetMapping("/{firstName}/{lastName}")
    public Person getByName(@PathVariable String firstName, @PathVariable String lastName) throws IOException {
        return personService.getByName(firstName, lastName);
    }

    @PostMapping
    public Person addPerson(@RequestBody Person person) {
        return personService.create(person);
    }

    @PutMapping
    public Person updatePerson(@RequestBody Person person) throws IOException {
        return personService.update(person);
    }

    @DeleteMapping("/{firstName}/{lastName}")
    public void deletePerson(@PathVariable String firstName, @PathVariable String lastName) throws IOException {
        personService.delete(firstName, lastName);
    }
}
