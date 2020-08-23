package com.revature.bankProject0.models;

import javax.swing.*;
import java.util.Objects;

public class Account {
    private Integer id;
    private Double accountBalance;
    private AccountType accountType;
    private Integer primaryOwner;
    private Integer secondaryOwner;

    public Account(){
        super();
    }

    public Account(AccountType accountType, Integer primaryOwner){
        this.accountType = accountType;
        this.primaryOwner = primaryOwner;
    }
    public Account(Double accountBalance, AccountType accountType, Integer primaryOwner){
        this(accountType,primaryOwner);
        this.accountBalance = accountBalance;
    }
    public Account(Integer id,Double accountBalance, AccountType accountType,Integer primaryOwner){
        this(accountBalance,accountType,primaryOwner);
        this.id = id;
    }
    public Account(Integer id,Double accountBalance, AccountType accountType, Integer primaryOwner, Integer secondaryOwner){
        this(id,accountBalance,accountType,primaryOwner);
        this.secondaryOwner = secondaryOwner;
    }
    public Account(Account copy){
        this(copy.id, copy.accountBalance, copy.accountType, copy.primaryOwner, copy.secondaryOwner);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Integer getPrimaryOwner() {
        return primaryOwner;
    }

    public void setPrimaryOwner(Integer primaryOwner) {
        this.primaryOwner = primaryOwner;
    }

    public Integer getSecondaryOwner() {
        return secondaryOwner;
    }

    public void setSecondaryOwner(Integer secondaryOwner) {
        this.secondaryOwner = secondaryOwner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return Objects.equals(getId(), account.getId()) &&
                Objects.equals(getAccountBalance(), account.getAccountBalance()) &&
                getAccountType() == account.getAccountType() &&
                Objects.equals(getPrimaryOwner(), account.getPrimaryOwner()) &&
                Objects.equals(getSecondaryOwner(), account.getSecondaryOwner());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAccountBalance(), getAccountType(), getPrimaryOwner(), getSecondaryOwner());
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountBalance=" + accountBalance +
                ", accountType=" + accountType +
                ", primaryOwner=" + primaryOwner +
                ", secondaryOwner=" + secondaryOwner +
                '}';
    }
}
