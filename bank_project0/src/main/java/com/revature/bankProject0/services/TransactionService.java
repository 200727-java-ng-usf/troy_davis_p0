package com.revature.bankProject0.services;

import com.revature.bankProject0.models.Account;
import com.revature.bankProject0.models.User;
import com.revature.bankProject0.repositories.TransactionRepository;

import java.util.Set;

public class TransactionService {
    private TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository repo){
        LogService.log("Instantiating " + this.getClass().toString());
        transactionRepository = repo;
    }

    public void createDeposit(User user, Account account){

    }
    public void createWithdrawal(User user, Account account){

    }


}
