package com.reactive.controller;


import com.reactive.model.Person;
import com.reactive.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);

    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/json")
    public Flux<Person> findPersonsJson() {

        Flux<Person> persons = personRepository.findAll();

//        persons.map(person -> person.setFirstName(person.getFirstName().toLowerCase()));

//        persons.flatMap(person -> new Person(person.getId(), person.getFirstName().toLowerCase(), person.getLastName().toLowerCase()));

        persons.delayElements(Duration.ofMillis(1000))
                .subscribe(person -> LOGGER.info("Client subscribes: {}", person));

        return persons;
    }
}
