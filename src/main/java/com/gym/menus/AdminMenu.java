package com.gym.menus;

import com.gym.services.MembershipService;
import com.gym.dao.UserDAO;
import com.gym.models.User;

import java.util.List;
import java.util.Scanner;
/**
 * populates a menu for admin users
 *
 */
public class AdminMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final MembershipService membershipService = new MembershipService();
    private static final UserDAO userDAO = new UserDAO();

    public static void show(int adminId) {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. View all users");
            System.out.println("2. Delete user by ID");
            System.out.println("3. View all memberships");
            System.out.println("4. View total revenue");
            System.out.println("5. Logout");

            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    viewAllUsers();
                    break;
                case "2":
                    deleteUserById();
                    break;
                case "3":
                    membershipService.viewAllMemberships();
                    break;
                case "4":
                    membershipService.viewRevenue();
                    break;
                case "5":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
    /**
     * Displays all users
     */
    private static void viewAllUsers() {
        List<User> users = userDAO.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            for (User user : users) {
                System.out.printf("ID: %d | Username: %s | Role: %s | Email: %s | Phone: %s | Address: %s%n",
                        user.getId(), user.getUsername(), user.getRole(),
                        user.getEmail(), user.getPhoneNumber(), user.getAddress());
            }
        }
    }
    /**
     * Deletes a user by their userId
     */
    private static void deleteUserById() {
        System.out.print("Enter the user ID to delete: ");
        try {
            int userId = Integer.parseInt(scanner.nextLine());
            if (userDAO.deleteUserById(userId)) {
                System.out.println("User deleted successfully.");
            } else {
                System.out.println("Failed to delete user.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid user ID.");
        }
    }
}
