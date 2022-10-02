package com.example.mybatistest.service;

import com.example.mybatistest.domain.Person;
import com.example.mybatistest.mybatis.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final Mapper mapper;

    public List<Person> getAllPersons() {
        List<Person> list = mapper.findAll();
        return list;
    }

    public Person getPerson(Person person) {
        Person man = mapper.findById(person);
        return man;
    }
}
