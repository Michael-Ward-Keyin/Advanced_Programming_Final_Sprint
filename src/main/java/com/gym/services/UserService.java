package com.gym.services;

import com.gym.dao.UserDAO;
import com.gym.models.*;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Scanner;
/**
 * Handles registration and login logic
 */
public class UserService {
    private final UserDAO userDAO = new UserDAO();
    private final Scanner scanner = new Scanner(System.in);
    /**
     * registers a new user
     */
    public void registerUser() {
        System.out.println("\n=== Register ===");

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Phone Number: ");
        String phone = scanner.nextLine();

        System.out.print("Address: ");
        String address = scanner.nextLine();

        System.out.print("Role (Admin, Trainer, Member): ");
        String role = scanner.nextLine();

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        User user;
        switch (role.toLowerCase()) {
            case "admin":
                user = new Admin(username, hashedPassword, email, phone, address);
                break;
            case "trainer":
                user = new Trainer(username, hashedPassword, email, phone, address);
                break;
            case "member":
                user = new Member(username, hashedPassword, email, phone, address);
                break;
            default:
                System.out.println("Invalid role.");
                return;
        }

        if (userDAO.registerUser(user)) {
            System.out.println("Registration successful!");
        } else {
            System.out.println("Registration failed.");
        }
    }
    /**
     * Allows a user to login
     * @return
     */
    public User login() {
        System.out.println("\n=== Login ===");

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        User user = userDAO.findByUsername(username);

        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            System.out.println("Login successful!");
            return user;
        } else {
            System.out.println("Invalid credentials.");
            return null;
        }
    }
}
