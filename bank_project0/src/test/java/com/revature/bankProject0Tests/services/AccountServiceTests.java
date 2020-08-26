package com.revature.bankProject0Tests.services;

import com.revature.bankProject0.exceptions.InvalidRequestException;
import com.revature.bankProject0.models.Account;
import com.revature.bankProject0.models.AccountType;
import com.revature.bankProject0.models.Role;
import com.revature.bankProject0.models.User;
import com.revature.bankProject0.repositories.AccountRepository;
import com.revature.bankProject0.services.AccountService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.revature.bankProject0.AppDriver.app;

public class AccountServiceTests {
    private String msg;

    private AccountService sut;

    private AccountRepository mockAccountRepo = Mockito.mock(AccountRepository.class);

    Set<Account> mockAccounts = new HashSet<>();

    @Before
    public void setup(){
        sut = new AccountService(mockAccountRepo);
        mockAccounts.add(new Account(2,1000.0d, AccountType.CHECKING,2));
        mockAccounts.add(new Account(1,100.0d, AccountType.SAVINGS,2));
        mockAccounts.add(new Account(2,1000.0d, AccountType.CHECKING,3));
        mockAccounts.add(new Account(1,100.0d, AccountType.SAVINGS,3));
    }

    @After
    public void teardown(){
        sut = null;

        mockAccounts.removeAll(mockAccounts);

        msg = null;
    }

    @Test(expected = InvalidRequestException.class)
    public void registerWithBadCredentials(){
        sut.register(new Account());
    }





}
