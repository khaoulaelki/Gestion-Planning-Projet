package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskService {

    public static List<Task> getTachesForUser(String username) {
        List<Task> taches = new ArrayList<>();
        String sql = "SELECT t.* FROM tache t JOIN membre m ON t.id_membre = m.id WHERE m.nom = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String titre = rs.getString("titre");
                String etat = rs.getString("etat");
                String date = rs.getDate("date_echeance").toString();
                int priorite = rs.getInt("priorite");

                taches.add(new Task(id, titre, etat, date, priorite));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return taches;
    }

    public static void updateEtat(int taskId, String etat) {
        String sql = "UPDATE tache SET etat = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, etat);
            stmt.setInt(2, taskId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}