package com.revature.bankProject0.services;

import com.revature.bankProject0.exceptions.AuthenticationException;
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
        Optional<Account> existingUserAccount = accountRepository.findAccountByUserId(newAccount.getPrimaryOwner());
        if (existingUserAccount.isPresent()) {
            LogService.logErr("Sorry, you may only have one bank account per user!");
            throw new InvalidRequestException("Sorry, you may only have one bank account per user!");
        }
        accountRepository.save(newAccount);
        app.setCurrentAccount(newAccount);
    }

    public void getAccountsForUser(User user){
        if (user != null) {
            app.setCurrentAccount(accountRepository.findAccountByUserId(user.getId())
                                .orElseThrow(AuthenticationException::new));
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
