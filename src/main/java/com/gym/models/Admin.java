package com.gym.models;
/**
 * Represents an admin user
 * takes from user
 */
public class Admin extends User {
    public Admin() {
        this.role = "Admin";
    }

    public Admin(String username, String password, String email, String phoneNumber, String address) {
        super(username, password, email, phoneNumber, address, "Admin");
    }
}
