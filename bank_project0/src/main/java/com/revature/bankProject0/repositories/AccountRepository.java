package com.revature.bankProject0.repositories;

import com.revature.bankProject0.models.Account;
import com.revature.bankProject0.models.AccountType;
import com.revature.bankProject0.services.LogService;
import com.revature.bankProject0.services.UserService;
import com.revature.bankProject0.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.revature.bankProject0.AppDriver.app;

/**
 * A repository to interact with the database and perform account services
 */
public class AccountRepository {
    private String baseAccountPrimaryOwnerQuery = "SELECT * FROM project_zero.account a" +
                                "JOIN project_zero.bank_users bu" +
                                "ON a.account_primary_owner_id = bu.id";

    public AccountRepository(){
        LogService.log("Instantiating " + this.getClass().toString());
    }

    public Optional<Account> findAccountByUserId(Integer userId){
        Optional<Account> account = Optional.empty();

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
           String sql = baseAccountPrimaryOwnerQuery + "WHERE account_primary_owner_id = ?";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1,userId);

            ResultSet rs = psmt.executeQuery();

            Account account1 = new Account();




        }catch (SQLException e){
            LogService.log(e.getStackTrace());
        }




        return account;
    }

    private Set<Account> mapResultSet(ResultSet rs) throws SQLException{
        Set<Account> accounts = new HashSet<>();

        while (rs.next()){
            Account temp = new Account();
            temp.setId(rs.getInt("id"));
            temp.setAccountBalance(((double) rs.getInt("account_balance")));
            temp.setAccountType(AccountType.getByName(rs.getString("name")));
            temp.setPrimaryOwner(app.);
        }

        return accounts;
    }

}
