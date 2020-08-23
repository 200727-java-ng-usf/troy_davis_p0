package com.revature.bankProject0.services;

import com.revature.bankProject0.exceptions.InvalidRequestException;
import com.revature.bankProject0.models.Account;
import com.revature.bankProject0.models.Transaction;
import com.revature.bankProject0.models.User;
import com.revature.bankProject0.repositories.TransactionRepository;

import java.util.Set;

import static com.revature.bankProject0.AppDriver.app;

public class TransactionService {
    private TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository repo){
        LogService.log("Instantiating " + this.getClass().toString());
        transactionRepository = repo;
    }

    public void createDepositTransaction(Transaction transaction){
        if (!isTransactionValid(transaction)){
            LogService.log("Invalid transaction Details provided!");
            throw new InvalidRequestException("Invalid user fields provided during Deposit Transaction creation");
        }
        transactionRepository.createNewTransaction(transaction);
    }
    public void createWithdrawalTransaction(User user, Account account, Double transactionAmount){

    }

    public void getTransactionsForAccountAndUser(User user, Set<Account> account){
        if (user != null && account != null) {
            for (Account account1: account){
                app.addToAccountTransactions(transactionRepository.findTransactionsByUserAndAccount(user.getId(), account1.getId()));
            }
        }
    }

    public boolean isTransactionValid(Transaction transaction){
        if (transaction == null){
            return false;
        }
        if (transaction.getAccountBalance() == null || transaction.getTransactionAmount() == null ||
            transaction.getAccountNumber() == null || transaction.getDateStamp() == null ||
            transaction.getPrimaryAccountOwner() == null || transaction.getTransactionType() == null){
            return false;
        }
        if (transaction.getTransactionAmount() > transaction.getAccountBalance()){
            System.out.println("Sorry no Over-Drafting!");
            return false;
        }


        return true;
    }

}
