package com.flexmoney.Yoga.repository;

import com.flexmoney.Yoga.entities.Batch;
import com.flexmoney.Yoga.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface BatchRepository extends CrudRepository<Batch, Long> {
    Batch findByUser(User user);
}
