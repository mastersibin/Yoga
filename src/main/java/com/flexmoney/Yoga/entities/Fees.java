package com.flexmoney.Yoga.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.mapping.Join;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Fees {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Long Id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user;

    @Column(name = "month")
    Integer month;

    @Column(name = "year")
    Integer year;

    public Fees(User user, Integer month, Integer year)
    {
        this.user = user;
        this.month = month;
        this.year = year;
    }
}
