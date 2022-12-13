package com.flexmoney.Yoga.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Long Id;

    @Column(name = "startTime")
    String startTime;

    @Column(name = "endTime")
    String endTime;

    public Batch(String startTime, String endTime)
    {
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
