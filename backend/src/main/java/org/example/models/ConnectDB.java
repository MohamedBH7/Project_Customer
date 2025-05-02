package org.example.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:sqlite:mydb.db";
        return DriverManager.getConnection(url);
    }
}