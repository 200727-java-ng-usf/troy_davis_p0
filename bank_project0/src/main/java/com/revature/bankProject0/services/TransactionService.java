package com.revature.bankProject0.services;

import com.revature.bankProject0.exceptions.InvalidRequestException;
import com.revature.bankProject0.models.Account;
import com.revature.bankProject0.models.Transaction;
import com.revature.bankProject0.models.User;
import com.revature.bankProject0.repositories.AccountRepository;
import com.revature.bankProject0.repositories.TransactionRepository;

import java.util.Set;

import static com.revature.bankProject0.AppDriver.app;

public class TransactionService {
    private TransactionRepository transactionRepository;
    private AccountRepository accountRepository;
    public TransactionService(TransactionRepository repo, AccountRepository accountRepository){
        LogService.log("Instantiating " + this.getClass().toString());
        this.transactionRepository = repo;
        this.accountRepository = accountRepository;
    }

    public void createDepositTransaction(Transaction transaction){
        if (!isTransactionValid(transaction)){
            LogService.log("Invalid transaction Details provided!");
            throw new InvalidRequestException("Invalid user fields provided during Deposit Transaction creation");
        }
        transactionRepository.createNewTransaction(transaction);
        accountRepository.updateAccountBalance(transaction.getAccountNumber(),
                                               transaction.getPrimaryAccountOwner(),
                                               transaction.getEndingBalance());
    }
    public void createWithdrawalTransaction(Transaction transaction){
        if (!isTransactionValid(transaction)){
            LogService.log("Invalid transaction Details provided!");
            throw new InvalidRequestException("Invalid user fields provided during Deposit Transaction creation");
        }
        transactionRepository.createNewTransaction(transaction);
        accountRepository.updateAccountBalance(transaction.getAccountNumber(),
                transaction.getPrimaryAccountOwner(),
                transaction.getEndingBalance());
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
            transaction.getAccountNumber() == null ||
            transaction.getPrimaryAccountOwner() == null || transaction.getTransactionType() == null){
            return false;
        }


        return true;
    }

}
