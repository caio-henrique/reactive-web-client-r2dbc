package com.reactive.controller;


import com.reactive.model.Person;
import com.reactive.service.PersonService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public Flux<Person> findPersons() {

        final Flux<Person> persons = personService.findPersons();
        persons.delayElements(Duration.ofMillis(1000)).log().subscribe();

        return persons;
    }

    @PostMapping
    public Mono<Person> cretePerson(@RequestBody Person person){
        return personService.cretePerson(person);
    }

    @DeleteMapping
    public void deletePerson(@RequestBody Person person)  {
        personService.deletePerson(person);
    }

    @GetMapping("/{id}")
    public Mono<Person> findPerson(@PathVariable Integer id)  {
        return personService.findPerson(id);
    }
}