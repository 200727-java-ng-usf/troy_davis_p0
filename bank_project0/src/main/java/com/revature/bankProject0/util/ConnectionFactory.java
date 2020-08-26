package com.revature.bankProject0.util;

import com.revature.bankProject0.services.LogService;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private Properties props = new Properties();
    private static ConnectionFactory connFactory = new ConnectionFactory();

    private ConnectionFactory(){
        try{
            props.load(new FileReader("src/main/resources/application.properties"));
        } catch (FileNotFoundException e) {
            LogService.logErr(e.toString());
        } catch (IOException e) {
            LogService.logErr(e.toString());
        }
    }

    public static ConnectionFactory getInstance(){
        return connFactory;
    }

    public Connection getConnection(){
        Connection conn = null;
        try {

            // Force the JVM to load the PostGreSQL JDBC driver
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(props.getProperty("url"),
                                                props.getProperty("username"),
                                                props.getProperty("password"));
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        if (conn == null) {
            throw new RuntimeException("Failed to establish connection.");
        }
        return conn;
    }


}
