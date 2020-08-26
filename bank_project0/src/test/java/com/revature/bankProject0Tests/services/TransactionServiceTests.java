package com.revature.bankProject0Tests.services;

import com.revature.bankProject0.exceptions.InvalidRequestException;
import com.revature.bankProject0.models.Account;
import com.revature.bankProject0.models.AccountType;
import com.revature.bankProject0.models.Transaction;
import com.revature.bankProject0.models.TransactionType;
import com.revature.bankProject0.repositories.AccountRepository;
import com.revature.bankProject0.repositories.TransactionRepository;
import com.revature.bankProject0.repositories.UserRepository;
import com.revature.bankProject0.services.AccountService;
import com.revature.bankProject0.services.TransactionService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Set;

public class TransactionServiceTests {
    private String msg;

    private TransactionService sut;

    private TransactionRepository mockTransactionRepo = Mockito.mock(TransactionRepository.class);
    private AccountRepository mockAccountRepo = Mockito.mock(AccountRepository.class);

    Set<Transaction> mockTransactions = new HashSet<>();

    @Before
    public void setup(){
        sut = new TransactionService(mockTransactionRepo, mockAccountRepo);
       mockTransactions.add(new Transaction(1,2,0.00d,100.0d, TransactionType.DEPOSIT));
       mockTransactions.add(new Transaction(1,2,1000.00d,100.0d, TransactionType.WITHDRAWAL));

    }

    @After
    public void tearDown(){
        sut = null;

        mockTransactions.removeAll(mockTransactions);

        msg = null;
    }

    @Test(expected = InvalidRequestException.class)
    public void createDepositWithBadFields(){
        sut.createDepositTransaction(new Transaction());
    }

    @Test(expected = InvalidRequestException.class)
    public void createWithdrawalWithBadFields(){
        sut.createWithdrawalTransaction(new Transaction());
    }
}
