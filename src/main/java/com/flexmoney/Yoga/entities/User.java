package com.flexmoney.Yoga.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "age")
    Integer age;

    public User(String name, Integer age)
    {
        this.name = name;
        this.age = age;
    }
}
