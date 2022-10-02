package com.example.mybatistest.domain;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person {
    private int id;
    private int age;
    private String name;
}
