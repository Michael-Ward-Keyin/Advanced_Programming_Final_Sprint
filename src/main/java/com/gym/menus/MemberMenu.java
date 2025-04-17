package com.gym.menus;

import com.gym.services.MembershipService;
import com.gym.services.WorkoutClassService;
import java.util.Scanner;
/**
 * Displays the menu for members
 */
public class MemberMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final MembershipService membershipService = new MembershipService();
    private static final WorkoutClassService workoutClassService = new WorkoutClassService();

    public static void show(int memberId) {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Member Menu ---");
            System.out.println("1. Browse classes");
            System.out.println("2. View my membership expenses");
            System.out.println("3. Purchase membership");
            System.out.println("4. Logout");

            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    workoutClassService.viewAllClasses();
                    break;
                case "2":
                    membershipService.viewMyMemberships(memberId);
                    break;
                case "3":
                    membershipService.purchaseMembership(memberId);
                    break;
                case "4":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
