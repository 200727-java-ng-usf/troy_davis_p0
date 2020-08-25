package com.revature.bankProject0.repositories;

import com.revature.bankProject0.models.Account;
import com.revature.bankProject0.models.AccountType;
import com.revature.bankProject0.models.Transaction;
import com.revature.bankProject0.models.TransactionType;
import com.revature.bankProject0.services.LogService;
import com.revature.bankProject0.util.ConnectionFactory;

import java.sql.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class TransactionRepository {
    private String baseTransactionQuery = "SELECT * FROM project_zero.bank_transaction bt " +
                                          "JOIN project_zero.bank_users bu " +
                                          "ON bt.owner_id = bu.id " +
                                          "JOIN project_zero.transaction_types tti " +
                                          "ON bt.transaction_type_id = tti.id " +
                                          "JOIN project_zero.account a " +
                                          "ON bt.account_id = a.id ";


    private String baseInsert = "INSERT INTO project_zero.bank_transaction\n" +
            "(account_balance, account_id, owner_id, transaction_type_id, transaction_amount, verified, executed, account_ending_balance)\n" +
            "VALUES(?, ?, ?, ?, ?, false, false,?);";

    public TransactionRepository(){
        LogService.log("Instantiating " + this.getClass().toString());
    }



    public Optional<Transaction> createNewTransaction(Transaction transaction){
        Optional<Transaction> optionalTransaction = Optional.empty();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()){

            PreparedStatement preparedStatement = conn.prepareStatement(baseInsert, new String[] {"id"});

            //preparedStatement.setDate(1, (Date) transaction.getDateStamp());
            preparedStatement.setDouble(1,transaction.getAccountBalance());
            preparedStatement.setInt(2,transaction.getAccountNumber());
            preparedStatement.setInt(3,transaction.getPrimaryAccountOwner());
            preparedStatement.setInt(4,transaction.getTransactionType().ordinal() +1);
            preparedStatement.setDouble(5,transaction.getTransactionAmount());
            preparedStatement.setDouble(6,transaction.getEndingBalance());

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted != 0){
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                while (resultSet.next()){
                    transaction.setId(resultSet.getInt(1));
                }
            }
            String sql = "UPDATE project_zero.account\n" +
                    "SET account_balance= ?\n" +
                    "WHERE id= ? and account_primary_owner_id= ? and account_secondary_owner_id= ? ;";
            PreparedStatement preparedStatement2 = conn.prepareStatement(sql, new String[]{"id"});
            if (transaction.getTransactionType().equals(TransactionType.DEPOSIT)){

            }else if(transaction.getTransactionType().equals(TransactionType.WITHDRAWAL)){

            }
            preparedStatement2.setDouble(1,transaction.getAccountBalance());

        } catch (SQLException e) {
            LogService.logErr(e.toString());
        }

        return optionalTransaction;
    }



    public Set<Transaction> findTransactionsByUserAndAccount(Integer userId, Integer accountId){
        Set<Transaction> accounts = new HashSet<>();

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = baseTransactionQuery + "WHERE bt.owner_id = ? AND bt.account_id = ? ORDER BY transaction_date ;";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1,userId);
            psmt.setInt(2,accountId);
            ResultSet rs = psmt.executeQuery();
            accounts = mapResultSet(rs);
        }catch (SQLException e){
            LogService.logErr(e.toString());
        }

        return accounts;
    }

    public Set<Transaction> findTransactionsByAccountId(Integer accountId){
        Set<Transaction> accounts = new HashSet<>();

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = baseTransactionQuery + "WHERE bt.account_id = ? ORDER BY transaction_date ;";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1,accountId);
            ResultSet rs = psmt.executeQuery();
            accounts = mapResultSet(rs);
        }catch (SQLException e){
            LogService.logErr(e.toString());
        }

        return accounts;
    }

    public Set<Transaction> findTransactionsByAccountAndAccountType(Integer accountId, AccountType accountType){
        Set<Transaction> accounts = new HashSet<>();

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = baseTransactionQuery + "WHERE bt.account_id = ? " +
                    "AND a.account_type_id = ? " +
                    "ORDER BY transaction_date ;";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1,accountId);
            psmt.setInt(2,accountType.ordinal()+1);
            ResultSet rs = psmt.executeQuery();
            Account account1 = new Account();
            accounts = mapResultSet(rs);
        }catch (SQLException e){
            LogService.logErr(e.toString());
        }

        return accounts;
    }

    public Set<Transaction> findTransactionsByUserAndAccountAndAccountType(Integer userId, Integer accountId, AccountType accountType){
        Set<Transaction> accounts = new HashSet<>();

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = baseTransactionQuery + "WHERE bt.owner_id = ? " +
                                                "AND bt.account_id = ? " +
                                                "AND a.account_type_id = ? " +
                                                "ORDER BY transaction_date ;";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1,userId);
            psmt.setInt(2,accountId);
            psmt.setInt(3,accountType.ordinal()+1);
            ResultSet rs = psmt.executeQuery();
            Account account1 = new Account();
            accounts = mapResultSet(rs);
        }catch (SQLException e){
            LogService.logErr(e.toString());
        }

        return accounts;
    }

    public boolean deleteTransactionById(Integer transactionId){
        try (Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "DELETE FROM project_zero.bank_transaction\n" +
                         "WHERE id=?;\n";
            PreparedStatement psmt = conn.prepareStatement(sql,new String[] {"id"});
            psmt.setInt(1,transactionId);
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


    private Set<Transaction> mapResultSet(ResultSet rs) throws SQLException{
        Set<Transaction> transactions = new HashSet<>();

        while (rs.next()){
            Transaction temp = new Transaction();
            temp.setId(rs.getInt("id"));
            temp.setDateStamp(rs.getTimestamp("transaction_date"));
            temp.setAccountNumber(rs.getInt("account_id"));
            temp.setPrimaryAccountOwner(rs.getInt("owner_id"));
            temp.setTransactionType(TransactionType.getByName(rs.getString("name")));
            temp.setTransactionAmount(rs.getDouble("transaction_amount"));
            LogService.log("Retrieved Transaction: " + temp.toString());
            transactions.add(temp);
        }


        return transactions;
    }
}
