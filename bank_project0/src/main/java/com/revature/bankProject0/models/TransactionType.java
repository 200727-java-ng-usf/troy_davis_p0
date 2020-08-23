package com.revature.bankProject0.models;

public enum TransactionType {
    DEPOSIT("DEPOSIT"),
    WITHDRAWAL("WITHDRAWAL"),
    VIEW_BALANCE("VIEW_BALANCE");

    private String tranactionTypeName;

    TransactionType(String name){
        this.tranactionTypeName = name;
    }

    public static TransactionType getByName(String name){
        for (TransactionType transactionType : TransactionType.values()){
            if (transactionType.tranactionTypeName.equals(name)){
                return transactionType;
            }
        }
        return VIEW_BALANCE;
    }


    @Override
    public String toString() {
        return tranactionTypeName;
    }
}
