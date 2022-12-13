package com.flexmoney.Yoga.repository;

import com.flexmoney.Yoga.entities.User;
import com.flexmoney.Yoga.entities.UserBatch;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UserBatchRepository extends CrudRepository<UserBatch, Long> {
    UserBatch findByUser(User user);
    @Transactional
    @Modifying
    @Query(value = "update UserBatch set batch = nextBatch, nextBatch = NULL where nextBatch is not NULL")
    void setNextBatch();
}
