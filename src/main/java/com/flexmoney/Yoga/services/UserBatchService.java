package com.flexmoney.Yoga.services;

import com.flexmoney.Yoga.entities.UserBatch;
import com.flexmoney.Yoga.repository.UserBatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBatchService {

    @Autowired
    UserBatchRepository userBatchRepository;

    public UserBatch saveUserBatch(UserBatch userBatch)
    {
        return userBatchRepository.save(userBatch);
    }

}
