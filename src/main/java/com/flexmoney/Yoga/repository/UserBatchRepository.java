package com.flexmoney.Yoga.repository;

import com.flexmoney.Yoga.entities.User;
import com.flexmoney.Yoga.entities.UserBatch;
import org.springframework.data.repository.CrudRepository;

public interface UserBatchRepository extends CrudRepository<UserBatch, Long> {
    UserBatch findByUser(User user);
}
