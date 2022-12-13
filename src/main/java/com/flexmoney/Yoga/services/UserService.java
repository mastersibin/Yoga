package com.flexmoney.Yoga.services;

import com.flexmoney.Yoga.entities.User;
import com.flexmoney.Yoga.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findUserByName(String name)
    {
        return userRepository.findUserByName(name);
    }

    public User saveUser(User user)
    {
        userRepository.save(user);
        return findUserByName(user.getName());
    }
}
