package com.mikael.safetynet.controller;
import com.mikael.safetynet.model.Person;
import com.mikael.safetynet.Service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> all() throws IOException {
        logger.info("GET /person");
        try {
            return personService.allPerson();
        } catch (Exception e) {
            logger.error("GET /person - erreur", e);
            throw e;
        }
    }

    @GetMapping("/{firstName}/{lastName}")
    public Person getByName(@PathVariable String firstName, @PathVariable String lastName) throws IOException {
        logger.info("GET /person/{}/{}", firstName, lastName);
        try {
            return personService.getByName(firstName, lastName);
        } catch (Exception e) {
            logger.error("GET /person/{}/{} - erreur", firstName, lastName, e);
            throw e;
        }
    }

    @PostMapping
    public Person addPerson(@RequestBody Person person) {
        logger.info("POST /person - firstName={} lastName={}", person.getFirstName(), person.getLastName());
        try {
            return personService.create(person);
        } catch (Exception e) {
            logger.error("POST /person - erreur", e);
            throw e;
        }
    }

    @PutMapping
    public Person updatePerson(@RequestBody Person person) throws IOException {
        logger.info("PUT /person - firstName={} lastName={}", person.getFirstName(), person.getLastName());
        try {
            return personService.update(person);
        } catch (Exception e) {
            logger.error("PUT /person - erreur", e);
            throw e;
        }
    }

    @DeleteMapping("/{firstName}/{lastName}")
    public void deletePerson(@PathVariable String firstName, @PathVariable String lastName) throws IOException {
        logger.info("DELETE /person/{}/{}", firstName, lastName);
        try {
            personService.delete(firstName, lastName);
        } catch (Exception e) {
            logger.error("DELETE /person/{}/{} - erreur",
                    firstName, lastName, e);
            throw e;
        }
    }
}
