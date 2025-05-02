package org.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.example.models.ConnectDB;
import org.example.models.Customer;

public class CustomerDAO {

    // Create
    public static int createCustomer(Customer customer) throws SQLException {
        String sql = "INSERT INTO Customers (name, phone, email, location, address, postcode) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
    
            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getPhone());
            pstmt.setString(3, customer.getEmail());
            pstmt.setString(4, customer.getLocation());
            pstmt.setString(5, customer.getAddress());
            pstmt.setString(6, customer.getPostcode());
    
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) return rs.getInt(1);
                }
            }
            return -1;
        }
    }
    // Read All
    public static List<Customer> getAllCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM Customers";

        try (Connection conn = ConnectDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                customers.add(new Customer(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("phone"),
                    rs.getString("email"),
                    rs.getString("location"),
                    rs.getString("address"),
                    rs.getString("postcode")
                ));
            }
        }
        return customers;
    }

    // Read Single
    public static Customer getCustomerById(int id) throws SQLException {
        String sql = "SELECT * FROM Customers WHERE id = ?";
        
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Customer(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("location"),
                        rs.getString("address"),
                        rs.getString("postcode")
                    );
                }
            }
        }
        return null;
    }

    // Update
    public static boolean updateCustomer(Customer customer) throws SQLException {
        String sql = "UPDATE Customers SET " +
                     "name = ?, phone = ?, email = ?, " +
                     "location = ?, address = ?, postcode = ? " +
                     "WHERE id = ?";

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getPhone());
            pstmt.setString(3, customer.getEmail());
            pstmt.setString(4, customer.getLocation());
            pstmt.setString(5, customer.getAddress());
            pstmt.setString(6, customer.getPostcode());
            pstmt.setInt(7, customer.getId());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    // Delete
    public static boolean deleteCustomer(int id) throws SQLException {
        String sql = "DELETE FROM Customers WHERE id = ?";

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }
}