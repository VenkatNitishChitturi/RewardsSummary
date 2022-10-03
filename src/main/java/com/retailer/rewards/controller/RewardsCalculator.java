package com.retailer.rewards.controller;

import com.retailer.rewards.model.Customer;
import com.retailer.rewards.model.RewardSummary;
import com.retailer.rewards.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;

@RestController
public class RewardsCalculator {

    @Autowired
    CustomerService customerService;

    @GetMapping(value="", produces = MediaType.TEXT_PLAIN_VALUE)
    public String sayHello() {

        return "Hello Welcome to rewards calculator!";
    }

    @GetMapping("/getCustomer/{customerId}")
    @ResponseBody
    public ResponseEntity<Customer> getCustomerById(@PathVariable String customerId) {
        Customer customer = null;
        try {
            customer = customerService.getCustomerById(customerId);
        } catch (IOException e) {
            ResponseEntity.badRequest();
        }
        if(customer==null) return (ResponseEntity<Customer>) ResponseEntity.badRequest();
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/getCustomerRewardSummary/{customerId}")
    @ResponseBody
    public ResponseEntity<RewardSummary> getCustomerRewardSummary(@PathVariable String customerId) {
        Customer customer = null;
        try {
            customer = customerService.getCustomerById(customerId);
        } catch (IOException e) {
            ResponseEntity.badRequest();
        }
        if(customer==null) return (ResponseEntity<RewardSummary>) ResponseEntity.badRequest();
        RewardSummary rewardSummary = new RewardSummary(customer);
        return ResponseEntity.ok(rewardSummary);
    }

    @GetMapping("/getCustomerRewardSummaryMonthly/{customerId}")
    @ResponseBody
    public ResponseEntity<HashMap> getCustomerRewardSummaryMonthly(@PathVariable String customerId) {
        Customer customer = null;
        try {
            customer = customerService.getCustomerById(customerId);
        } catch (IOException e) {
            ResponseEntity.badRequest();
        }
        if(customer==null) return (ResponseEntity<HashMap>) ResponseEntity.badRequest();
        RewardSummary rewardSummary = new RewardSummary(customer);
        HashMap rewardsMonthly = rewardSummary.getRewardPointsPerMonth();
        return ResponseEntity.ok(rewardsMonthly);
    }

}
