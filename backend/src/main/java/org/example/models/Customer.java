package org.example.models;

public class Customer {
    private int id;
    private String name;
    private String phone;
    private String email;
    private String location;
    private String address;
    private String postcode;

    // Constructors
    public Customer() {}

    public Customer(int id, String name, String phone, String email, String location, String address, String postcode) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.location = location;
        this.address = address;
        this.postcode = postcode;
    }

    // Getters and Setters (Required for JSON serialization)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getPostcode() { return postcode; }
    public void setPostcode(String postcode) { this.postcode = postcode; }
}