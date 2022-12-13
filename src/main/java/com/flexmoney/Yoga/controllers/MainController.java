package com.flexmoney.Yoga.controllers;

import com.flexmoney.Yoga.dtos.*;
import com.flexmoney.Yoga.entities.Batch;
import com.flexmoney.Yoga.entities.Fees;
import com.flexmoney.Yoga.entities.User;
import com.flexmoney.Yoga.entities.UserBatch;
import com.flexmoney.Yoga.services.BatchService;
import com.flexmoney.Yoga.services.FeesService;
import com.flexmoney.Yoga.services.UserBatchService;
import com.flexmoney.Yoga.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public String enroll(@RequestBody EnrollDTO enrollDTO)
    {
        System.out.printf(enrollDTO.getBatch_id().toString());
        if (enrollDTO.getAge() < 18 && enrollDTO.getAge() > 65)
            return "Age Not Accepted";
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

    @PostMapping(value = "/completePayment")
    public String payFees(@RequestBody PayFeesDTO payFeesDTO)
    {
        User user = userService.findUserByName(payFeesDTO.getName());
        if (user == null)
            return "User Doesn't Exist";
        Fees prevFees = feesService.getFeesByUser(user);
        if (payFeesDTO.getYear() < prevFees.getYear() || (payFeesDTO.getYear() == prevFees.getYear() && payFeesDTO.getMonth() <= prevFees.getMonth()))
            return "Already Paid";
        prevFees.setMonth(payFeesDTO.getMonth());
        prevFees.setYear(payFeesDTO.getYear());
        if (feesService.saveFees(prevFees) != null)
            return "Paid";
        else
            return "Not Paid";
    }

    @PostMapping(value = "/calculateFees")
    public Integer calculateFees(@RequestBody CalculateFeesDTO calculateFeesDTO)
    {
        User user = userService.findUserByName(calculateFeesDTO.getName());
        if (user == null)
            return 0;
        return Integer.max(feesService.getFees(user, calculateFeesDTO.getMonth(), calculateFeesDTO.getYear()), 0);

    }

    @PostMapping(value = "/allowed")
    public String allowed(@RequestBody AllowedDTO allowedDTO)
    {
        User user = userService.findUserByName(allowedDTO.getName());
        if (user == null)
            return "User doesn't exist";
        Fees prevFees = feesService.getFeesByUser(user);
        if (allowedDTO.getYear() < prevFees.getYear() || (allowedDTO.getYear() == prevFees.getYear() && allowedDTO.getMonth() < prevFees.getMonth()))
            return "Invalid Date";
        if (allowedDTO.getMonth()-prevFees.getMonth() <= 1)
        {
            UserBatch userBatch = userBatchService.findByUser(user);
            Batch batch = userBatch.getBatch();
            return "Batch Timings: "+batch.getStartTime()+" - "+batch.getEndTime();
        }
        else
            return "Not Allowed";
    }

    @PostMapping(value = "setNextBatch")
    public String setNextBatch(@RequestBody NextBatchDTO nextBatchDTO)
    {
        User user = userService.findUserByName(nextBatchDTO.getName());
        if (user == null)
            return "User doesn't exist";
        UserBatch userBatch = userBatchService.findByUser(user);
        Batch nextBatch = batchService.findById(nextBatchDTO.getBatch_id());
        userBatch.setNextBatch(nextBatch);
        if (userBatchService.saveUserBatch(userBatch) == null)
            return "Failed";
        return "Success";
    }

}
