package models;

public class Account {
    private int balance;

    public Account(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public boolean makeDeposit(double depositAmount){
        //only perform if balance is greater than zero
        if (depositAmount > 0.00d){
            this.balance += depositAmount;
            return true;
        }else{
            //must have entered a negative number!
            return false;
        }
    }
    public boolean makeWithdrawal(double withdrawalAmount){
        //only perform if greater than zero and account is greater than amount passed
        if (this.balance > 0 && withdrawalAmount > 0){
            if (this.balance >= withdrawalAmount){
                this.balance -= withdrawalAmount;
                return true;
            }

        }
        return false;
    }
}
