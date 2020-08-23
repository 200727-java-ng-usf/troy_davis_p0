package com.revature.bankProject0.models;

public enum AccountType {
    CHECKING("CHECKING"),
    SAVINGS("SAVINGS"),
    LOCKED("LOCKED"),
    CLOSED("CLOSED"),
    FLAGGED("FLAGGED");

    private String accountTypeName;

    AccountType(String name){
        this.accountTypeName = name;
    }

    public static AccountType getByName(String name){

        for (AccountType accountType : AccountType.values()){
            if (accountType.accountTypeName.equals(name)){
                return accountType;
            }
        }
        return LOCKED;
    }


    @Override
    public String toString() {
        return accountTypeName;
    }
}
