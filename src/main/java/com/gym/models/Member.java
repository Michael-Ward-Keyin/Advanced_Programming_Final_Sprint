package com.gym.models;
/**
 * Represents a gym member
 * takes from user
 */
public class Member extends User {
    public Member() {
        this.role = "Member";
    }

    public Member(String username, String password, String email, String phoneNumber, String address) {
        super(username, password, email, phoneNumber, address, "Member");
    }
}
