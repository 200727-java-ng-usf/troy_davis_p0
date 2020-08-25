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
import java.util.stream.Collectors;

import static com.revature.bankProject0.AppDriver.app;

/**
 * A repository to interact with the database and perform account services
 */
public class AccountRepository {
    private String baseAccountPrimaryOwnerQuery = "SELECT * FROM project_zero.account a " +
                                "JOIN project_zero.bank_users bu " +
                                "ON a.account_primary_owner_id = bu.id " +
                                "JOIN project_zero.account_types act " +
                                "ON a.account_type_id = act.id ";

    private String baseInsert = "INSERT into project_zero.account ";


    public AccountRepository(){
        LogService.log("Instantiating " + this.getClass().toString());
    }

    public Set<Account> findAccountByUserId(Integer userId){
        Set<Account> account = new HashSet<>();

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
           String sql = baseAccountPrimaryOwnerQuery + "WHERE a.account_primary_owner_id = ?";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1,userId);

            ResultSet rs = psmt.executeQuery();

            Account account1 = new Account();


            account = mapResultSet(rs);

        }catch (SQLException e){
            LogService.logErr(e.toString());
        }

        return account;
    }


    public Optional<Account> findAccountByAccountId(Integer accountId){
        Optional<Account> account = Optional.empty();

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = baseAccountPrimaryOwnerQuery + "WHERE id = ?";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1,accountId);

            ResultSet rs = psmt.executeQuery();

            Account account1 = new Account();

            account = mapResultSet(rs).stream().findFirst();

        }catch (SQLException e){
            LogService.logErr(e.toString());
        }
        return account;
    }




    public Optional<Account> save(Account newAccount){
        Optional<Account> optionalAccount = Optional.empty();

        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = baseInsert +
                    "(account_balance, account_primary_owner_id, account_secondary_owner_id, account_type_id) " +
                    "VALUES(?, ?, 0, ?); ";

            PreparedStatement preparedStatement = conn.prepareStatement(sql, new String[] {"id"});

            preparedStatement.setDouble(1,newAccount.getAccountBalance());
            preparedStatement.setInt(2,newAccount.getPrimaryOwner());
            preparedStatement.setInt(3,newAccount.getAccountType().ordinal() + 1);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted != 0){
                ResultSet rs = preparedStatement.getGeneratedKeys();
                while (rs.next()){
                    newAccount.setId(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            LogService.logErr(e.toString());
        }


        return optionalAccount;
    }

    public boolean updateAccount(Account newAccount){

        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "UPDATE project_zero.account\n" +
                         "SET account_balance=?, account_primary_owner_id=?, account_secondary_owner_id=?, account_type_id=?\n" +
                         "WHERE id=?;\n";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, new String[]{"id"});
            preparedStatement.setDouble(1,newAccount.getAccountBalance());

            preparedStatement.setInt(2,newAccount.getPrimaryOwner());

            preparedStatement.setInt(3,newAccount.getSecondaryOwner());

            preparedStatement.setInt(4,newAccount.getAccountType().ordinal()+1);

            preparedStatement.setInt(5,newAccount.getId());

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted !=0){
                return true;
            }

        } catch (SQLException e) {
            LogService.logErr(e.toString());
        }
        return false;
    }

    public boolean updateAccountBalance(Integer accountId, Integer ownerId, Double newBalance){

        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "UPDATE project_zero.account\n" +
                    "SET account_balance= ?\n" +
                    "WHERE id= ? and account_primary_owner_id= ?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, new String[]{"id"});
            preparedStatement.setDouble(1,newBalance);
            preparedStatement.setInt(2,accountId);
            preparedStatement.setInt(3,ownerId);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted !=0){
                return true;
            }

        } catch (SQLException e) {
            LogService.logErr(e.toString());
        }
        return false;
    }

    public Set<Account> getAccountByIdAndType(Integer accountId, AccountType accountType){
        Set<Account> accountSet = new HashSet<>();
        try (Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = baseInsert + "WHERE a.id=? AND a.account_type_id =?;";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1,accountId);
            psmt.setInt(2,accountType.ordinal()+1);
            ResultSet rs = psmt.executeQuery();
            accountSet = mapResultSet(rs).stream().collect(Collectors.toSet());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountSet;
    }
    public Set<Account> getAccountByUserIdAndType(Integer userId, AccountType accountType){
        Set<Account> accountSet = new HashSet<>();
        try (Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = baseInsert + "WHERE bu.id=? AND a.account_type_id =?;";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1,userId);
            psmt.setInt(2,accountType.ordinal()+1);
            ResultSet rs = psmt.executeQuery();
            accountSet = mapResultSet(rs).stream().collect(Collectors.toSet());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountSet;
    }

    public boolean deleteBankAccountById(Integer accountId){
        try (Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "DELETE FROM project_zero.account\n" +
                         "WHERE id=?;";
            PreparedStatement psmt = conn.prepareStatement(sql,new String[] {"id"});
            psmt.setInt(1,accountId);
            //get number of affected rows
            int rowsInserted = psmt.executeUpdate();
            if (rowsInserted !=0){
                return true;
            }
        } catch (SQLException e) {
            LogService.logErr(e.toString());
        }
        return false;
    }







    private Set<Account> mapResultSet(ResultSet rs) throws SQLException{
        Set<Account> accounts = new HashSet<>();

        while (rs.next()){
            Account temp = new Account();
            temp.setId(rs.getInt("id"));
            temp.setAccountBalance(((double) rs.getInt("account_balance")));
            temp.setPrimaryOwner(rs.getInt("account_primary_owner_id"));
            temp.setSecondaryOwner(rs.getInt("account_secondary_owner_id"));
            //temp.setRole(Role.getByName(rs.getString("name")));
            temp.setAccountType(AccountType.getByName(rs.getString("name")));
            LogService.log("Retrieved Account: " + temp.toString());
            accounts.add(temp);
        }

        return accounts;
    }

}
