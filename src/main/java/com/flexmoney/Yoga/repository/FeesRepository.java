package com.flexmoney.Yoga.repository;

import com.flexmoney.Yoga.entities.Fees;
import com.flexmoney.Yoga.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface FeesRepository extends CrudRepository<Fees, Long> {
    public Fees findByUser(User user);
}
