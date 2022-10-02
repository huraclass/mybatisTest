package com.example.mybatistest.controller;

import com.example.mybatistest.domain.Person;
import com.example.mybatistest.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
@RequiredArgsConstructor
public class Controller {
    private final PersonService personService;

    @GetMapping("/persons")
    public ResponseEntity<Person> AllPersons() {
        return new ResponseEntity(personService.getAllPersons(), HttpStatus.OK);
    }

    @GetMapping("/person/*")
    public Person findByID() {
        Person person = new Person().builder()
                .id(1)
                .age(17)
                .name("marin")
                .build();
        Person man = personService.getPerson(person);
        return man;
    }
}
