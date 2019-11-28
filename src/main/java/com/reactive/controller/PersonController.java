package com.reactive.controller;


import com.reactive.model.Person;
import com.reactive.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/person")
public class PersonController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);

    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping
    public Flux<Person> findPersons() {

        Flux<Person> persons = personRepository.findAll();
        return persons;
    }

    @PostMapping
    public void cretePerson(@RequestBody Person person){
         personRepository.save(person).subscribe();
    }
}
