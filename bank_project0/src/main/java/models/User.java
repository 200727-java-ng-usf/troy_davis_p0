package models;

import java.util.ArrayList;

public class User {
    private Account userAccount;
    private ArrayList<Account> userAccountsList;

    public User(){
        this.userAccount = new Account(0);
        this.userAccountsList = new ArrayList<>();
        userAccountsList.add(this.userAccount);
    }

}
