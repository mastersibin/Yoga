package com.flexmoney.Yoga.services;

import com.flexmoney.Yoga.entities.Fees;
import com.flexmoney.Yoga.entities.User;
import com.flexmoney.Yoga.repository.FeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeesService {

    @Autowired
    FeesRepository feesRepository;

    public Fees saveFees(Fees fees)
    {
        return feesRepository.save(fees);
    }

    public Integer getFees(User user, Integer month, Integer year)
    {
        Fees fees =  feesRepository.findByUser(user);
        if (year < fees.getYear() || (year == fees.getYear() && month < fees.getMonth()))
            return 0;
        return (year - fees.getYear())*6000 + (month-fees.getMonth())*500;
    }

    public Fees getFeesByUser(User user)
    {
        return feesRepository.findByUser(user);
    }
}
