package com.retailer.rewards.service;

import com.retailer.rewards.model.Transaction;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    private static List<Transaction> transactions = new ArrayList<>();

    private static TransactionService transactionService = new TransactionService();

    public static TransactionService getTransactionService(){
        if(transactionService!=null) return transactionService;
        else return new TransactionService();
    }

    private TransactionService() {
        try {
            try {
                setup();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<Transaction> getCustomerTransactions(String customerId){
        return transactions.stream().filter(x-> x.getCustomerId().equals(customerId)).collect(Collectors.toList());
    }

    private void setup() throws IOException, ParseException {
        File file = new File(TransactionService.class
                .getClassLoader().getResource("transaction_data.txt").getPath());
        BufferedReader br
                = new BufferedReader(new FileReader(file));

        String st;
        while ((st = br.readLine()) != null) {
            String[] transactionData = st.split(" ");
            SimpleDateFormat formatter1=new SimpleDateFormat("MM/dd/yyyy");
            Date date = formatter1.parse(transactionData[3]);
            Transaction transaction = new Transaction(transactionData[0], transactionData[1],
                    Double.parseDouble(transactionData[2]), date, transactionData[4]);
            transactions.add(transaction);
        }
    }
}
