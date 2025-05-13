package dao;

import model.Member;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {

    public void addMember(Member member) {
        String sql = "INSERT INTO members(name, role, age) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, member.getName());
            stmt.setString(2, member.getRole());
            stmt.setInt(3, member.getAge());
            stmt.executeUpdate();

            // Set the generated ID to the member
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    member.setId(generatedKeys.getInt(1));
                }
            }

            System.out.println("[DB] Member added to database.");
        } catch (SQLException e) {
            System.out.println("[DB] Error: " + e.getMessage());
        }
    }

    public List<Member> getAllMembers() {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT id, name, role, age FROM members";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Member member = new Member(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("role"),
                    rs.getInt("age")
                );
                members.add(member);
            }

        } catch (SQLException e) {
            System.out.println("[DB] Error loading members: " + e.getMessage());
        }

        return members;
    }
}
