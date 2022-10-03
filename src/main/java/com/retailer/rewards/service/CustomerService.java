package com.retailer.rewards.service;

import com.retailer.rewards.model.Customer;
import com.retailer.rewards.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private static List<Customer> customers = new ArrayList<>();

    public CustomerService() {
        try {
            setup();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void setup() throws IOException {
        File file = new File(CustomerService.class.getClassLoader().getResource("customer_data.txt").getPath());
        BufferedReader br
                = new BufferedReader(new FileReader(file));

        String st;
        while ((st = br.readLine()) != null) {
            String[] customerData = st.split(" ");
            List<Transaction> transactions = getAllTransactionsForCustomer(customerData[0]);
            Customer customer = new Customer(customerData[0], customerData[1],
                    customerData[2], customerData[3], customerData[4], transactions);
            customers.add(customer);
        }
    }

    private List<Transaction> getAllTransactionsForCustomer(String customerId) {
        TransactionService transactionService = TransactionService.getTransactionService();
        return transactionService.getCustomerTransactions(customerId);
    }

    public List<Customer> getAllCustomers() {
        return customers;
    }

    public Customer getCustomerById(String customerId) throws IOException {
        List<Customer> customers = getAllCustomers().stream().filter(x -> x.getCustomerId().equals(customerId)).collect(Collectors.toList());
        if (customers.size() == 0) return null;
        return customers.get(0);
    }
}
