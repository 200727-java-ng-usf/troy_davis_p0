package com.revature.bankProject0.services;

import com.revature.bankProject0.exceptions.InvalidRequestException;
import com.revature.bankProject0.models.Account;
import com.revature.bankProject0.models.User;
import com.revature.bankProject0.repositories.AccountRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.revature.bankProject0.AppDriver.app;

public class AccountService {
    private AccountRepository accountRepository;

    public AccountService(AccountRepository repo){
        LogService.log("Instantiating " + this.getClass().toString());
        accountRepository = repo;
    }

    public void addSecondaryAccountOwner(){

    }



    public void deleteAccount(){

    }

    public void register(Account newAccount){
        if (!isAccountValid(newAccount)){
            LogService.log("Invalid Account Details Provided");
            throw new InvalidRequestException("Invalid user fields provided during account creation");
        }

//        Optional<Account> existingAccount = accountRepository.findAccountByAccountId(newAccount.getId());
//        if (existingAccount.isPresent()){
//            System.out.println("You may only have one of each account");
//            app.getRouterService().route("/createAccount");
//        }
        accountRepository.save(newAccount);
        app.addToUserAccounts(newAccount);
    }

    public void getAccountsForUser(User user){
        Set<Optional<Account>> accounts = new HashSet<>();
        if (user != null) {
            app.setUserAccounts(accountRepository.findAccountByUserId(user.getId()));
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
