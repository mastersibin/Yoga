package com.flexmoney.Yoga.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EnrollDTO {
    String name;
    Integer age;
    Long batch_id;
    Integer month;
    Integer year;
}
