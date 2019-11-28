package com.reactive.service;

import com.reactive.model.Person;
import com.reactive.repository.PersonRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Flux<Person> findPersons() {
        return personRepository.findAll();
    }

    public Mono<Person> cretePerson(Person person){
        return personRepository.save(person);
    }

    public void deletePerson(Person person) {
        personRepository.delete(person).subscribe();
    }

    public Mono<Person> findPerson(Integer id) {
        return personRepository.findById(id);
    }
}
