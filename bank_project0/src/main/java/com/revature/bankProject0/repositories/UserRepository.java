package com.revature.bankProject0.repositories;

import com.revature.bankProject0.db.UserDB;
import com.revature.bankProject0.models.User;
import com.revature.bankProject0.services.LogService;
import com.revature.bankProject0.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserRepository {



    public UserRepository(){
        LogService.log("Instantiating " + this.getClass().toString());
    }

    public Optional<User> findUsersByCredentials(String userName, String password){

        Optional<User> user = Optional.empty();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()){

            String sql = "SELECT * FROM revabooks.app_users WHERE username = ? AND password = ?";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1,userName);
            psmt.setString(2,password);

            ResultSet rs = psmt.executeQuery();

            User appUser = new User();

            while (rs.next()){
                appUser.setId(rs.getInt("id"));
                appUser.setUserName(rs.getString("username"));
                appUser.setPassWord(rs.getString("password"));
                appUser.setFirstName(rs.getString("first_name"));
                appUser.setLastName(rs.getString("last_name"));
            }

            user = Optional.of(appUser);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return user;
    }

    public Optional<User> findUserByUserName(String username){
        Optional<User> user = Optional.empty();
        try (Connection conn = ConnectionFactory.getInstance().getConnection()){

            String sql = "SELECT * FROM revabooks.app_users WHERE username = ?";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1,username);
            ResultSet rs = psmt.executeQuery();

            User appUser = new User();

            while (rs.next()){
                appUser.setId(rs.getInt("id"));
                appUser.setUserName(rs.getString("username"));
                appUser.setPassWord(rs.getString("password"));
                appUser.setFirstName(rs.getString("first_name"));
                appUser.setLastName(rs.getString("last_name"));
            }

            user = Optional.of(appUser);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return user;
    }

    public Optional<User> save(User newUser){
        Optional<User> user = Optional.empty();
        try (Connection conn = ConnectionFactory.getInstance().getConnection()){

            String sql = "insert into app_users (username , password , first_name , last_name , email , role_id ) \n" +
                    "values ('?','?','?','?','?',?)"


                    ;
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1,newUser.getUserName());
            psmt.setString(2,newUser.getPassWord());
            psmt.setString(3,newUser.getFirstName());
            psmt.setString(4,newUser.getLastName());
            psmt.setString(5,"null");
            psmt.setString(6,"4");
            ResultSet rs = psmt.executeQuery();

            User appUser = newUser;

            user = Optional.of(appUser);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }




        return user;
    }
}
