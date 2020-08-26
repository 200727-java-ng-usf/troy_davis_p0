package com.revature.bankProject0.services;

import com.revature.bankProject0.exceptions.InvalidRequestException;
import com.revature.bankProject0.models.Account;
import com.revature.bankProject0.models.AccountType;
import com.revature.bankProject0.models.User;
import com.revature.bankProject0.repositories.AccountRepository;

import java.util.Optional;

import static com.revature.bankProject0.AppDriver.app;

public class AccountService {
    private AccountRepository accountRepository;

    public AccountService(AccountRepository repo){
        LogService.log("Instantiating " + this.getClass().toString());
        accountRepository = repo;
    }


    public void register(Account newAccount){
        if (!isAccountValid(newAccount)){
            LogService.log("Invalid Account Details Provided");
            throw new InvalidRequestException("Invalid user fields provided during account creation");
        }
        accountRepository.save(newAccount);
        app.addToUserAccounts(newAccount);
    }

    public void getAccountsForUser(User user){
        if (user != null) {
            app.setUserAccounts(accountRepository.findAccountByUserId(user.getId()));
        }
    }
    public void getAccountsForUserByType(User user, AccountType accountType){
        if (user != null) {
            app.setUserAccounts(accountRepository.getAccountByUserIdAndType(user.getId(),accountType));
        }
    }

    public boolean isAccountValid(Account accountInQuestion){
        if (accountInQuestion == null){
            return false;
        }
        if (accountInQuestion.getAccountType() == null){
            return false;
        }
        if (accountInQuestion.getPrimaryOwner() == null){
            return false;
        }
        return true;
    }
}
