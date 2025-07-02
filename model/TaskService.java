package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskService {

    public static boolean ajouterTache(Task tache) {
        String sql = "INSERT INTO tache (titre, statut, date_echeance, priorite, id_projet, id_membre) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tache.getTitre());
            stmt.setString(2, tache.getStatut());
            stmt.setDate(3, Date.valueOf(tache.getDateEcheance()));
            stmt.setString(4, tache.getPriorite());
            stmt.setInt(5, tache.getIdProjet());
            stmt.setInt(6, tache.getIdMembre());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Erreur ajout tâche : " + e.getMessage());
            return false;
        }
    }

    public static List<Task> getTachesParProjet(int idProjet) {
        List<Task> taches = new ArrayList<>();
        String sql = "SELECT * FROM tache WHERE id_projet = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProjet);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String titre = rs.getString("titre");
                String statut = rs.getString("statut");
                String date = rs.getDate("date_echeance").toString();
                String priorite = rs.getString("priorite");
                int idMembre = rs.getInt("id_membre");

                taches.add(new Task(id, titre, statut, date, priorite, idProjet, idMembre));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return taches;
    }

    public static void updateStatut(int taskId, String statut) {
        String sql = "UPDATE tache SET statut = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, statut);
            stmt.setInt(2, taskId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static List<Task> getTachesForUser(String username) {
        List<Task> taches = new ArrayList<>();
        String sql = "SELECT t.*, m.nom FROM tache t " +
                     "JOIN membre m ON t.id_membre = m.id " +
                     "WHERE m.nom = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String titre = rs.getString("titre");
                String statut = rs.getString("statut");
                String date = rs.getDate("date_echeance").toString();
                String priorite = rs.getString("priorite");
                int idProjet = rs.getInt("id_projet");
                int idMembre = rs.getInt("id_membre");

                taches.add(new Task(id, titre, statut, date, priorite, idProjet, idMembre));
            }

        } catch (SQLException e) {
            System.out.println("❌ Erreur getTachesForUser : " + e.getMessage());
        }

        return taches;
    }

}