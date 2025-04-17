package com.gym.services;

import com.gym.dao.WorkoutClassDAO;
import com.gym.models.WorkoutClass;

import java.util.List;
import java.util.Scanner;
/**
 * Provides logic for workoutclasses
 */
public class WorkoutClassService {
    private final WorkoutClassDAO dao = new WorkoutClassDAO();
    private final Scanner scanner = new Scanner(System.in);
    /**
     * adds a class for trainers
     * @param trainerId
     */
    public void addClass(int trainerId) {
        System.out.print("Enter class type: ");
        String type = scanner.nextLine();

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        WorkoutClass wc = new WorkoutClass(type, description, trainerId);
        if (dao.addClass(wc)) {
            System.out.println("Class added successfully.");
        } else {
            System.out.println("Failed to add class.");
        }
    }
    /**
     * updates information for a trainers class
     */
    public void updateClass() {
        System.out.print("Enter class ID to update: ");
        int classId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter new description: ");
        String desc = scanner.nextLine();

        if (dao.updateClass(classId, desc)) {
            System.out.println("Class updated.");
        } else {
            System.out.println("Update failed.");
        }
    }
    /**
     * Deletes a class
     */
    public void deleteClass() {
        System.out.print("Enter class ID to delete: ");
        int classId = scanner.nextInt();
        scanner.nextLine();

        if (dao.deleteClass(classId)) {
            System.out.println("Class deleted.");
        } else {
            System.out.println("Delete failed.");
        }
    }
    /**
     * Displays available classes
     */
    public void viewAllClasses() {
        List<WorkoutClass> list = dao.getAllClasses();
        for (WorkoutClass wc : list) {
            System.out.printf("ID: %d | Type: %s | Description: %s | Trainer ID: %d%n",
                wc.getClassId(), wc.getClassType(), wc.getClassDescription(), wc.getTrainerId());
        }
    }
    /**
     * Displays all classes of a specific trainer
     * @param trainerId
     */
    public void viewMyClasses(int trainerId) {
        List<WorkoutClass> list = dao.getClassesByTrainerId(trainerId);
        for (WorkoutClass wc : list) {
            System.out.printf("ID: %d | Type: %s | Description: %s%n",
                wc.getClassId(), wc.getClassType(), wc.getClassDescription());
        }
    }
}
