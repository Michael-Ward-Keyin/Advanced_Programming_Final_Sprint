package com.gym;

import com.gym.models.User;
import com.gym.services.UserService;
import com.gym.menus.AdminMenu;
import com.gym.menus.TrainerMenu;
import com.gym.menus.MemberMenu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();
        boolean running = true;

        System.out.println(" Welcome to the Gym Management System!");

        while (running) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    userService.registerUser();
                    break;

                case "2":
                    User user = userService.login();
                    if (user != null) {
                        System.out.printf(" Welcome, %s! (%s)%n", user.getUsername(), user.getRole());
                        switch (user.getRole().toLowerCase()) {
                            case "admin":
                                AdminMenu.show(user.getId());
                                break;
                            case "trainer":
                                TrainerMenu.show(user.getId());
                                break;
                            case "member":
                                MemberMenu.show(user.getId());
                                break;
                            default:
                                System.out.println(" Unknown role");
                        }
                    }
                    break;

                case "3":
                    running = false;
                    System.out.println(" Goodbye");
                    break;

                default:
                    System.out.println("Invalid choice");
            }
        }

        scanner.close();
    }
}
