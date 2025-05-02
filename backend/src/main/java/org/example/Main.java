package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.example.models.ConnectDB;

public class Main {
    public static void main(String[] args) {
        try (Connection connection = ConnectDB.getConnection();
             Statement statement = connection.createStatement()) {

            // Create table
            String createTable = "CREATE TABLE IF NOT EXISTS Customers (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT, phone TEXT, email TEXT," +
                    "location TEXT, address TEXT, postcode TEXT)";
            statement.executeUpdate(createTable);

            // Insert data
            statement.executeUpdate("INSERT INTO Customers (name, phone, email, location, address, postcode) " +
                    "VALUES ('John Doe', '1234567890', 'john@example.com', 'New York', '123 Main St', '10001')");
            statement.executeUpdate("INSERT INTO Customers (name, phone, email, location, address, postcode) " +
                    "VALUES ('Alice Smith', '0987654321', 'alice@example.com', 'Los Angeles', '456 Elm St', '90001')");
            System.out.println("Data Inserted");

            // Read data
            try (ResultSet rs = statement.executeQuery("SELECT * FROM Customers")) {
                System.out.println("ID\tName\tPhone\tEmail\tLocation\tAddress\tPostcode");
                while (rs.next()) {
                    System.out.printf("%d\t%s\t%s\t%s\t%s\t%s\t%s%n",
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("phone"),
                            rs.getString("email"),
                            rs.getString("location"),
                            rs.getString("address"),
                            rs.getString("postcode"));
                }
            }

            // Update data
            statement.executeUpdate("UPDATE Customers SET name = 'John Updated' WHERE id = 1");
            System.out.println("Data Updated");

            // Verify update
            try (ResultSet rs = statement.executeQuery("SELECT * FROM Customers")) {
                System.out.println("ID\tName\tPhone\tEmail\tLocation\tAddress\tPostcode");
                while (rs.next()) {
                    System.out.printf("%d\t%s\t%s\t%s\t%s\t%s\t%s%n",
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("phone"),
                            rs.getString("email"),
                            rs.getString("location"),
                            rs.getString("address"),
                            rs.getString("postcode"));
                }
            }

            // Delete data
            statement.executeUpdate("DELETE FROM Customers WHERE id = 2");
            System.out.println("Data Deleted");

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }
}