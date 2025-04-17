package com.gym.models;
/**
 * Represents a Trainer
 * takes from user
 *
 */

public class Trainer extends User {
    public Trainer() {
        this.role = "Trainer";
    }

    public Trainer(String username, String password, String email, String phoneNumber, String address) {
        super(username, password, email, phoneNumber, address, "Trainer");
    }
}
