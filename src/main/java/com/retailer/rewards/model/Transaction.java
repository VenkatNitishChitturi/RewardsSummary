package com.retailer.rewards.model;

import java.util.Date;

public class Transaction {
    private String id;
    private String itemName;
    private double amount;
    private Date date;
    private String customerId;

    public Transaction(String id, String itemName, double amount, Date date, String customerId) {
        this.id = id;
        this.itemName = itemName;
        this.amount = amount;
        this.date = date;
        this.customerId = customerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
