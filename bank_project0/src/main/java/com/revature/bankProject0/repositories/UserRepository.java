package com.revature.bankProject0.repositories;

import com.revature.bankProject0.models.Role;
import com.revature.bankProject0.models.User;
import com.revature.bankProject0.services.LogService;
import com.revature.bankProject0.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Class to interact with the database on tables concerning users
 */
public class UserRepository {


    private String baseQuery = "SELECT * FROM project_zero.bank_users bu " +
                                "JOIN project_zero.user_roles ur " +
                                "ON bu.role_id = ur.id ";

    public UserRepository(){
        LogService.log("Instantiating " + this.getClass().toString());
    }


    public Optional<User> findUsersByCredentials(String userName, String password){
        Optional<User> user = Optional.empty();
        try (Connection conn = ConnectionFactory.getInstance().getConnection()){

            String sql = baseQuery + "WHERE username = ? AND password = ?";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1,userName);
            psmt.setString(2,password);

            ResultSet rs = psmt.executeQuery();

            user = mapResultSet(rs).stream().findFirst();

        } catch (SQLException e) {
            LogService.logErr(e.toString());
        }

        return user;
    }

    public Optional<User> findUserByUserName(String username){
        Optional<User> user = Optional.empty();
        try (Connection conn = ConnectionFactory.getInstance().getConnection()){

            String sql = baseQuery + "WHERE username = ?";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1,username);
            ResultSet rs = psmt.executeQuery();

            user = mapResultSet(rs).stream().findFirst();

        } catch (SQLException e) {
            LogService.logErr(e.toString());
        }
        return user;
    }

    public Optional<User> findUserByEmail(String email) {

        Optional<User> _user = Optional.empty();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = baseQuery + "WHERE email = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            _user = mapResultSet(rs).stream().findFirst();

        } catch (SQLException e) {
            LogService.logErr(e.toString());
        }

        return _user;

    }

    public Optional<User> save(User newUser){
        Optional<User> user = Optional.empty();
        try (Connection conn = ConnectionFactory.getInstance().getConnection()){

            String sql = "insert into project_zero.bank_users (username , password , first_name , last_name , email , role_id ) " +
                    "values (?,?,?,?,?,?) "
                    ;
            PreparedStatement psmt = conn.prepareStatement(sql,new String[] {"id"});
            psmt.setString(1,newUser.getUserName());
            psmt.setString(2,newUser.getPassWord());
            psmt.setString(3,newUser.getFirstName());
            psmt.setString(4,newUser.getLastName());
            psmt.setString(5,newUser.getEmail());
            psmt.setInt(6,newUser.getRole().ordinal() + 1);

            //get number of affected rows
            int rowsInserted = psmt.executeUpdate();
            if (rowsInserted !=0){
                ResultSet rs = psmt.getGeneratedKeys();
                while (rs.next()){
                    //get the generated primary key, the user id number
                    newUser.setId(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            LogService.logErr(e.toString());
        }
        return Optional.of(newUser);
    }

    private Set<User> mapResultSet(ResultSet rs) throws SQLException {

        Set<User> users = new HashSet<>();
        while (rs.next()) {
            User temp = new User();
            temp.setId(rs.getInt("id"));
            temp.setFirstName(rs.getString("first_name"));
            temp.setLastName(rs.getString("last_name"));
            temp.setUserName(rs.getString("username"));
            temp.setPassWord(rs.getString("password"));
            temp.setEmail(rs.getString("email"));
            temp.setRole(Role.getByName(rs.getString("name")));
            LogService.log("Retrieved User: " + temp.toString());
            users.add(temp);
        }
        return users;
    }
}
