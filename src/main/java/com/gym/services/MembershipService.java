package com.gym.services;

import com.gym.dao.MembershipDAO;
import com.gym.models.Membership;

import java.util.List;
import java.util.Scanner;
/**
 * handles membership logic
 */
public class MembershipService {
    private final MembershipDAO dao = new MembershipDAO();
    private final Scanner scanner = new Scanner(System.in);
    /**
     * Purchases a membership by userid
     * @param userId
     */
    public void purchaseMembership(int userId) {
        System.out.print("Enter membership type: ");
        String type = scanner.nextLine();

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        System.out.print("Enter cost: ");
        double cost = scanner.nextDouble();
        scanner.nextLine();

        Membership m = new Membership(type, description, cost, userId);
        if (dao.purchaseMembership(m)) {
            System.out.println("Membership purchased successfully.");
        } else {
            System.out.println("Failed to purchase membership.");
        }
    }
    /**
     * displays all memberships
     */
    public void viewAllMemberships() {
        List<Membership> list = dao.getAllMemberships();
        for (Membership m : list) {
            System.out.printf("ID: %d | Type: %s | Cost: $%.2f | Member ID: %d%n",
                m.getMembershipId(), m.getMembershipType(), m.getMembershipCost(), m.getMemberId());
        }
    }
    /**
     * displays total revenue
     */
    public void viewRevenue() {
        double total = dao.calculateTotalRevenue();
        System.out.printf("Total Revenue: $%.2f%n", total);
    }
    /**
     * Displays memberships costs
     * @param userId
     */
    public void viewMyMemberships(int userId) {
        List<Membership> list = dao.getMembershipsByUserId(userId);
        double total = 0;
        for (Membership m : list) {
            System.out.printf("ID: %d | Type: %s | Cost: $%.2f%n",
                m.getMembershipId(), m.getMembershipType(), m.getMembershipCost());
            total += m.getMembershipCost();
        }
        System.out.printf("Total spent on memberships: $%.2f%n", total);
    }
}
