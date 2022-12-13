package com.flexmoney.Yoga.controllers;

import com.flexmoney.Yoga.dtos.CalculateFeesDTO;
import com.flexmoney.Yoga.dtos.EnrollDTO;
import com.flexmoney.Yoga.dtos.PayFeesDTO;
import com.flexmoney.Yoga.entities.Batch;
import com.flexmoney.Yoga.entities.Fees;
import com.flexmoney.Yoga.entities.User;
import com.flexmoney.Yoga.entities.UserBatch;
import com.flexmoney.Yoga.services.BatchService;
import com.flexmoney.Yoga.services.FeesService;
import com.flexmoney.Yoga.services.UserBatchService;
import com.flexmoney.Yoga.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class MainController {

    @Autowired
    UserService userService;

    @Autowired
    FeesService feesService;

    @Autowired
    UserBatchService userBatchService;

    @Autowired
    BatchService batchService;

    @PostMapping(value = "/enroll")
    public String enroll(EnrollDTO enrollDTO)
    {
        if (userService.findUserByName(enrollDTO.getName()) == null)
        {
            User user = new User(enrollDTO.getName(), enrollDTO.getAge());
            user = userService.saveUser(user);
            Fees fees = new Fees(user, enrollDTO.getMonth(), enrollDTO.getYear());
            feesService.saveFees(fees);
            Batch batch = batchService.findById(enrollDTO.getBatch_id());
            userBatchService.saveUserBatch(new UserBatch(user, batch, null));
            return "Enrolled";
        }
        return "Not Enrolled";
    }

    @PostMapping(value = "/payFees")
    public String payFees(PayFeesDTO payFeesDTO)
    {
        User user = userService.findUserByName(payFeesDTO.getName());
        if (user == null)
            return "User Doesn't Exist";
        Fees prevFees = feesService.getFeesByUser(user);
        if (payFeesDTO.getYear() < prevFees.getYear() || (payFeesDTO.getYear() == prevFees.getYear() && payFeesDTO.getMonth() < prevFees.getMonth()))
            return "Already Paid";
        Fees fees = new Fees(user, payFeesDTO.getMonth(), payFeesDTO.getYear());
        if (feesService.saveFees(fees) != null)
            return "Paid";
        else
            return "Not Paid";
    }

    @PostMapping(value = "/calculateFees")
    public Integer calculateFees(CalculateFeesDTO calculateFeesDTO)
    {
        User user = userService.findUserByName(calculateFeesDTO.getName());
        if (user == null)
            return 0;
        return feesService.getFees(user, calculateFeesDTO.getMonth(), calculateFeesDTO.getYear());

    }

}
