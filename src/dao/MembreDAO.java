package dao;

import basedonnee.Database;
import model.Membre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MembreDAO {

    // Ajouter un membre
    public static boolean ajouterMembre(Membre membre) {
        String sql = "INSERT INTO membre (nom, prenom, age) VALUES (?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, membre.getNom());
            stmt.setString(2, membre.getPrenom());
            stmt.setInt(3, membre.getAge());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Erreur lors de l'ajout : " + e.getMessage());
            return false;
        }
    }

    // Vérifier si un membre existe (par nom et prénom)
    public static boolean verifierExistence(String nom, String prenom) {
        String sql = "SELECT COUNT(*) FROM membre WHERE nom = ? AND prenom = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nom);
            stmt.setString(2, prenom);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            System.out.println("❌ Erreur vérification : " + e.getMessage());
        }

        return false;
    }

    // Lister tous les membres
    public static List<Membre> getTousLesMembres() {
        List<Membre> membres = new ArrayList<>();
        String sql = "SELECT * FROM membre";

        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Membre m = new Membre(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getInt("age")
                );
                membres.add(m);
            }

        } catch (SQLException e) {
            System.out.println("❌ Erreur lecture membres : " + e.getMessage());
        }

        return membres;
    }

    // Modifier un membre existant
    public static boolean updateMembre(Membre membre) {
        String sql = "UPDATE membre SET nom = ?, prenom = ?, age = ? WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, membre.getNom());
            stmt.setString(2, membre.getPrenom());
            stmt.setInt(3, membre.getAge());
            stmt.setInt(4, membre.getId());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Erreur modification membre : " + e.getMessage());
            return false;
        }
    }
}
