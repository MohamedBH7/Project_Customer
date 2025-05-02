package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.example.dao.CustomerDAO;
import org.example.models.ConnectDB;
import org.example.models.Customer;

import com.google.gson.Gson;

import static spark.Spark.before;
import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.options;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.put;

public class WebService {
    public static void main(String[] args) {
        // Initialize database
        initializeDatabase();
        
        // Configure Spark
        port(4567);
        enableCORS();
        Gson gson = new Gson();

        // Define API endpoints
        get("/api/customers", (req, res) -> {
            try {
                return CustomerDAO.getAllCustomers();
            } catch (SQLException e) {
                res.status(500);
                return errorResponse("Database error: " + e.getMessage());
            }
        }, gson::toJson);

        get("/api/customers/:id", (req, res) -> {
            try {
                int id = Integer.parseInt(req.params("id"));
                Customer customer = CustomerDAO.getCustomerById(id);
                if (customer != null) {
                    return customer;
                }
                res.status(404);
                return errorResponse("Customer not found");
            } catch (NumberFormatException e) {
                res.status(400);
                return errorResponse("Invalid ID format");
            } catch (SQLException e) {
                res.status(500);
                return errorResponse("Database error: " + e.getMessage());
            }
        }, gson::toJson);

        post("/api/customers", (req, res) -> {
            try {
                Customer customer = gson.fromJson(req.body(), Customer.class);
                int id = CustomerDAO.createCustomer(customer);
                res.status(201);
                return successResponse("id", id);
            } catch (SQLException e) {
                res.status(500);
                return errorResponse("Database error: " + e.getMessage());
            } catch (Exception e) {
                res.status(400);
                return errorResponse("Invalid data: " + e.getMessage());
            }
        }, gson::toJson);

        put("/api/customers/:id", (req, res) -> {
            try {
                int id = Integer.parseInt(req.params("id"));
                Customer customer = gson.fromJson(req.body(), Customer.class);
                customer.setId(id);
                boolean success = CustomerDAO.updateCustomer(customer);
                if (success) {
                    return successResponse("message", "Customer updated");
                }
                res.status(404);
                return errorResponse("Customer not found");
            } catch (NumberFormatException e) {
                res.status(400);
                return errorResponse("Invalid ID format");
            } catch (SQLException e) {
                res.status(500);
                return errorResponse("Database error: " + e.getMessage());
            }
        }, gson::toJson);

        delete("/api/customers/:id", (req, res) -> {
            try {
                int id = Integer.parseInt(req.params("id"));
                boolean success = CustomerDAO.deleteCustomer(id);
                if (success) {
                    return successResponse("message", "Customer deleted");
                }
                res.status(404);
                return errorResponse("Customer not found");
            } catch (NumberFormatException e) {
                res.status(400);
                return errorResponse("Invalid ID format");
            } catch (SQLException e) {
                res.status(500);
                return errorResponse("Database error: " + e.getMessage());
            }
        }, gson::toJson);
    }

    private static void initializeDatabase() {
        String sql = "CREATE TABLE IF NOT EXISTS Customers (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT, phone TEXT, email TEXT," +
                "location TEXT, address TEXT, postcode TEXT)";

        try (Connection conn = ConnectDB.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.println("Database initialization failed: " + e.getMessage());
        }
    }

    private static void enableCORS() {
        options("/*", (req, res) -> {
            String headers = req.headers("Access-Control-Request-Headers");
            if (headers != null) res.header("Access-Control-Allow-Headers", headers);
            
            String methods = req.headers("Access-Control-Request-Method");
            if (methods != null) res.header("Access-Control-Allow-Methods", methods);
            
            return "OK";
        });

        before((req, res) -> {
            res.header("Access-Control-Allow-Origin", "*");
            res.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
            res.header("Access-Control-Allow-Headers", "Content-Type");
        });
    }

    private static Map<String, Object> successResponse(String key, Object value) {
        Map<String, Object> response = new HashMap<>();
        response.put(key, value);
        return response;
    }

    private static Map<String, String> errorResponse(String message) {
        Map<String, String> response = new HashMap<>();
        response.put("error", message);
        return response;
    }
}