package com.example.mybatistest.service;

import com.example.mybatistest.domain.Person;
import com.example.mybatistest.mybatis.Mapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonServiceTest {
    @Autowired
    PersonService service;

    @Test
    void selectTest() {
        Person person = Person.builder().id(1).age(17).name("marin").build();
        Person man = service.getPerson(person);
        System.out.println("man = " + man);
    }
}