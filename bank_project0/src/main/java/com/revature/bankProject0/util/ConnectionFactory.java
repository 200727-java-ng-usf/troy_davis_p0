package com.revature.bankProject0.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static ConnectionFactory connFactory = new ConnectionFactory();

    private ConnectionFactory(){
        super();
    }

    public static ConnectionFactory getInstance(){
        return connFactory;
    }

    public Connection getConnection(){
        Connection conn = null;
        try {

            // Force the JVM to load the PostGreSQL JDBC driver
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(
                    "jdbc:postgresql://java-ng-usf-200727.cyd04jk1n1lv.us-east-1.rds.amazonaws.com:5432/postgres",
                    "FAKE",
                    "FAKE");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        if (conn == null) {
            throw new RuntimeException("Failed to establish connection.");
        }

        return conn;
    }


}
