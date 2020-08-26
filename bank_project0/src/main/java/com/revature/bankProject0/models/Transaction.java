package com.revature.bankProject0.models;

import java.util.Date;
import java.util.Objects;

public class Transaction {
    private Integer id;

    private Date dateStamp;
    private Integer primaryAccountOwner;
    private Integer accountNumber;
    private Double accountBalance;
    private Double endingBalance;
    private Double transactionAmount;
    private TransactionType transactionType;

    public Transaction() {
        super();
    }

    public Transaction(Integer accountNumber,Integer primaryAccountOwner, Double accountBalance, Double transactionAmount, TransactionType transactionType) {
        this.accountNumber = accountNumber;
        this.primaryAccountOwner = primaryAccountOwner;
        this.accountBalance = accountBalance;
        this.transactionAmount = transactionAmount;
        this.transactionType = transactionType;
    }

    public Transaction(Integer accountNumber,Integer primaryAccountOwner, Double accountBalance, Double transactionAmount, TransactionType transactionType, Date date) {
        this(accountNumber,primaryAccountOwner, accountBalance, transactionAmount, transactionType);
        this.dateStamp = date;
    }

    public Transaction(Integer id,Integer accountNumber, Integer primaryAccountOwner, Double accountBalance, Double transactionAmount, TransactionType transactionType, Date date) {
        this(accountNumber,primaryAccountOwner, accountBalance, transactionAmount, transactionType, date);
        this.id = id;
    }

    public Transaction(Transaction copy){
        this(copy.id, copy.accountNumber, copy.primaryAccountOwner, copy.accountBalance, copy.transactionAmount, copy.transactionType, copy.dateStamp);
    }

    public Double getEndingBalance() {
        return endingBalance;
    }

    public void setEndingBalance(Double endingBalance) {
        this.endingBalance = endingBalance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateStamp() {
        return dateStamp;
    }

    public void setDateStamp(Date dateStamp) {
        this.dateStamp = dateStamp;
    }

    public Integer getPrimaryAccountOwner() {
        return primaryAccountOwner;
    }

    public void setPrimaryAccountOwner(Integer primaryAccountOwner) {
        this.primaryAccountOwner = primaryAccountOwner;
    }

    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getDateStamp(), that.getDateStamp()) &&
                Objects.equals(getPrimaryAccountOwner(), that.getPrimaryAccountOwner()) &&
                Objects.equals(getAccountNumber(), that.getAccountNumber()) &&
                Objects.equals(getAccountBalance(), that.getAccountBalance()) &&
                Objects.equals(getEndingBalance(), that.getEndingBalance()) &&
                Objects.equals(getTransactionAmount(), that.getTransactionAmount()) &&
                getTransactionType() == that.getTransactionType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDateStamp(), getPrimaryAccountOwner(), getAccountNumber(), getAccountBalance(), getEndingBalance(), getTransactionAmount(), getTransactionType());
    }

    @Override
    public String toString() {
        return "\n"+"Transaction: " +
                " id: " + id +
                " dateStamp: " + dateStamp +
                " accountNumber: " + accountNumber +
                " transactionAmount: " + transactionAmount +
                " transactionType: " + transactionType + "\n";
    }
}

