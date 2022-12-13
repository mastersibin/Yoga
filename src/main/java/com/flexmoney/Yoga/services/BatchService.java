package com.flexmoney.Yoga.services;

import com.flexmoney.Yoga.entities.Batch;
import com.flexmoney.Yoga.entities.User;
import com.flexmoney.Yoga.repository.BatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BatchService {

    @Autowired
    private BatchRepository batchRepository;

    public Batch findById(Long Id)
    {
        Optional<Batch> batch = batchRepository.findById(Id);
        if (batch.isEmpty())
            return null;
        return (Batch) batch.get();
    }



}
