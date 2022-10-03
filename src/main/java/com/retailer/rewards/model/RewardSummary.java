package com.retailer.rewards.model;

import java.util.*;

public class RewardSummary {
    private String customerId;
    private int totalRewardPoints;
    private HashMap<Integer, Integer> rewardPointsPerMonth = new HashMap<>();

    public RewardSummary(Customer customer) {
        this.customerId = customer.getCustomerId();
        getAllRewardsData(customer);
    }

    private void getAllRewardsData(Customer customer) {
        List<Transaction> transactions = customer.getTransactions();
        for (Transaction t : transactions) {
            Double amount = t.getAmount();
            int diff, points = 0;
            if (amount > 100.0) {
                diff = (int) (amount - 100.0);
                points = diff * 2;
            } else {
                diff = (int) (amount - 50.0);
                points = diff;
            }
            Date date = t.getDate();
            int month = date.getMonth()+1;
            if(rewardPointsPerMonth.containsKey(month)){
                int oldPoints = rewardPointsPerMonth.get(month);
                rewardPointsPerMonth.put(month, oldPoints+points);
            }else {
                rewardPointsPerMonth.put(month, points);
            }
            totalRewardPoints += points;
        }
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public int getTotalRewardPoints() {
        return totalRewardPoints;
    }

    public void setTotalRewardPoints(int totalRewardPoints) {
        this.totalRewardPoints = totalRewardPoints;
    }

    public HashMap<Integer, Integer> getRewardPointsPerMonth() {
        return rewardPointsPerMonth;
    }

    public void setRewardPointsPerMonth(HashMap<Integer, Integer> rewardPointsPerMonth) {
        this.rewardPointsPerMonth = rewardPointsPerMonth;
    }
}
