package com.gym.menus;

import com.gym.services.MembershipService;
import com.gym.services.WorkoutClassService;
import java.util.Scanner;
/**
 * Displays a menu for trainers
 */
public class TrainerMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final MembershipService membershipService = new MembershipService();
    private static final WorkoutClassService workoutClassService = new WorkoutClassService();

    public static void show(int trainerId) {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Trainer Menu ---");
            System.out.println("1. Add a class");
            System.out.println("2. Update a class");
            System.out.println("3. Delete a class");
            System.out.println("4. View my classes");
            System.out.println("5. Purchase membership");
            System.out.println("6. Logout");

            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    workoutClassService.addClass(trainerId);
                    break;
                case "2":
                    workoutClassService.updateClass();
                    break;
                case "3":
                    workoutClassService.deleteClass();
                    break;
                case "4":
                    workoutClassService.viewMyClasses(trainerId);
                    break;
                case "5":
                    membershipService.purchaseMembership(trainerId);
                    break;
                case "6":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
