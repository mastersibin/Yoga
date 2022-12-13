package com.flexmoney.Yoga;

import com.flexmoney.Yoga.entities.Batch;
import com.flexmoney.Yoga.entities.Fees;
import com.flexmoney.Yoga.repository.BatchRepository;
import com.flexmoney.Yoga.repository.FeesRepository;
import com.flexmoney.Yoga.repository.UserBatchRepository;
import com.flexmoney.Yoga.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

public class StartupClass {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserBatchRepository userBatchRepository;

    @Autowired
    FeesRepository feesRepository;

    @Autowired
    BatchRepository batchRepository;

    @PostConstruct
    public void addBatches()
    {
        if (batchRepository.count() == 0)
        {
            batchRepository.save(new Batch("6:00 AM", "7:00 AM"));
            batchRepository.save(new Batch("7:00 AM", "8:00 AM"));
            batchRepository.save(new Batch("8:00 AM", "9:00 AM"));
            batchRepository.save(new Batch("5:00 PM", "6:00 PM"));
        }
    }
}
