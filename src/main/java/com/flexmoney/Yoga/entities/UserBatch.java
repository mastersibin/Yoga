package com.flexmoney.Yoga.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
public class UserBatch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "batch_id", referencedColumnName = "id")
    Batch batch;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "next_batch_id", referencedColumnName = "id")
    Batch nextBatch;

    public UserBatch(User user, Batch batch, Batch nextBatch)
    {
        this.user = user;
        this.batch = batch;
        this.nextBatch = nextBatch;
    }
}
