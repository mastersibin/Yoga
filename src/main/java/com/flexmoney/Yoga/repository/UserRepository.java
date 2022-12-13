package com.flexmoney.Yoga.repository;

import com.flexmoney.Yoga.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByName(String name);
}
