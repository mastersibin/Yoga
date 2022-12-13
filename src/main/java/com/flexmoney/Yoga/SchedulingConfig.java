package com.flexmoney.Yoga;

import com.flexmoney.Yoga.repository.UserBatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulingConfig {

    @Autowired
    private UserBatchRepository userBatchRepository;

    @Scheduled(cron = "0 0 0 1 * ?")
    public void updatingUserBatch()
    {
        System.out.println("Cron executed");
        userBatchRepository.setNextBatch();
    }
}
