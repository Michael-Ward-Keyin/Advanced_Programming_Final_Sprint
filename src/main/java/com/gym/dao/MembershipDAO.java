package com.gym.dao;

import com.gym.models.Membership;
import com.gym.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Handles membership related operations
 */
public class MembershipDAO {
    /**
     * Lets a user purchase a membership
     * @param m
     * @return
     */
    public boolean purchaseMembership(Membership m) {
        String sql = "INSERT INTO memberships (membership_type, membership_description, membership_cost, member_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, m.getMembershipType());
            stmt.setString(2, m.getMembershipDescription());
            stmt.setDouble(3, m.getMembershipCost());
            stmt.setInt(4, m.getMemberId());

            return stmt.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Accesses all memberships
     * @return
     */
    public List<Membership> getAllMemberships() {
        List<Membership> list = new ArrayList<>();
        String sql = "SELECT * FROM memberships";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Membership m = new Membership();
                m.setMembershipId(rs.getInt("membership_id"));
                m.setMembershipType(rs.getString("membership_type"));
                m.setMembershipDescription(rs.getString("membership_description"));
                m.setMembershipCost(rs.getDouble("membership_cost"));
                m.setMemberId(rs.getInt("member_id"));
                list.add(m);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    /**
     * Checks total revenue
     * @return
     */
    public double calculateTotalRevenue() {
        String sql = "SELECT SUM(membership_cost) FROM memberships";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getDouble(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0.0;
    }
    /**
     * Finds a member by their Id
     * @param userId
     * @return
     */
    public List<Membership> getMembershipsByUserId(int userId) {
        List<Membership> list = new ArrayList<>();
        String sql = "SELECT * FROM memberships WHERE member_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Membership m = new Membership();
                m.setMembershipId(rs.getInt("membership_id"));
                m.setMembershipType(rs.getString("membership_type"));
                m.setMembershipDescription(rs.getString("membership_description"));
                m.setMembershipCost(rs.getDouble("membership_cost"));
                m.setMemberId(rs.getInt("member_id"));
                list.add(m);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
