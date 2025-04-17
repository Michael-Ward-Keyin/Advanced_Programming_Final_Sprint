package com.gym.dao;

import com.gym.models.WorkoutClass;
import com.gym.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Handles operations related to workout classes
 */
public class WorkoutClassDAO {
    /**
     * Adds a class
     * @param wc
     * @return
     */
    public boolean addClass(WorkoutClass wc) {
        String sql = "INSERT INTO workout_classes (class_type, class_description, trainer_id) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, wc.getClassType());
            stmt.setString(2, wc.getClassDescription());
            stmt.setInt(3, wc.getTrainerId());

            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Updates a class by using its class Id
     * @param classId
     * @param newDescription
     * @return
     */
    public boolean updateClass(int classId, String newDescription) {
        String sql = "UPDATE workout_classes SET class_description = ? WHERE class_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newDescription);
            stmt.setInt(2, classId);

            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Deletes class by classId
     * @param classId
     * @return
     */
    public boolean deleteClass(int classId) {
        String sql = "DELETE FROM workout_classes WHERE class_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, classId);
            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Gets all classes
     * @return
     */
    public List<WorkoutClass> getAllClasses() {
        List<WorkoutClass> list = new ArrayList<>();
        String sql = "SELECT * FROM workout_classes";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                WorkoutClass wc = new WorkoutClass();
                wc.setClassId(rs.getInt("class_id"));
                wc.setClassType(rs.getString("class_type"));
                wc.setClassDescription(rs.getString("class_description"));
                wc.setTrainerId(rs.getInt("trainer_id"));
                list.add(wc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    /**
     * Gets all classes of a specific trainer
     * @param trainerId
     * @return
     */
    public List<WorkoutClass> getClassesByTrainerId(int trainerId) {
        List<WorkoutClass> list = new ArrayList<>();
        String sql = "SELECT * FROM workout_classes WHERE trainer_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, trainerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                WorkoutClass wc = new WorkoutClass();
                wc.setClassId(rs.getInt("class_id"));
                wc.setClassType(rs.getString("class_type"));
                wc.setClassDescription(rs.getString("class_description"));
                wc.setTrainerId(rs.getInt("trainer_id"));
                list.add(wc);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
