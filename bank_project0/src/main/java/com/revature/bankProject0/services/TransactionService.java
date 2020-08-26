package com.revature.bankProject0.services;

import com.revature.bankProject0.exceptions.InvalidRequestException;
import com.revature.bankProject0.models.Transaction;
import com.revature.bankProject0.repositories.AccountRepository;

public class TransactionService {

    private AccountRepository accountRepository;
    public TransactionService(AccountRepository accountRepository){
        LogService.log("Instantiating " + this.getClass().toString());
        this.accountRepository = accountRepository;
    }

    public void createDepositTransaction(Transaction transaction){
        if (!isTransactionValid(transaction)){
            LogService.log("Invalid transaction Details provided!");
            throw new InvalidRequestException("Invalid user fields provided during Deposit Transaction creation");
        }
        accountRepository.updateAccountBalance(transaction.getAccountNumber(), transaction.getEndingBalance());

    }
    public void createWithdrawalTransaction(Transaction transaction){
        if (!isTransactionValid(transaction)){
            LogService.log("Invalid transaction Details provided!");
            throw new InvalidRequestException("Invalid user fields provided during Deposit Transaction creation");
        }
        accountRepository.updateAccountBalance(transaction.getAccountNumber(),transaction.getEndingBalance());

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
