package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    // Ajouter un membre
    public static boolean ajouterMembre(User membre) {
        String sql = "INSERT INTO membre (nom, mot_de_passe, role) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, membre.getUsername());
            stmt.setString(2, membre.getPassword());
            stmt.setString(3, membre.getRole());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Erreur lors de l'ajout : " + e.getMessage());
            return false;
        }
    }

    // Vérifier si un membre existe par son nom
    public static boolean verifierExistence(String nom) {
        String sql = "SELECT COUNT(*) FROM membre WHERE nom = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nom);
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
    public static List<User> getTousLesMembres() {
        List<User> membres = new ArrayList<>();
        String sql = "SELECT * FROM membre";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                User m = new User(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("mot_de_passe"),
                        rs.getString("role")
                );
                membres.add(m);
            }

        } catch (SQLException e) {
            System.out.println("❌ Erreur lecture membres : " + e.getMessage());
        }

        return membres;
    }

    // Alias pour interface (si besoin)
    public static List<User> getMembres() {
        return getTousLesMembres();
    }

    // Trouver un membre par son nom
    public static User findByNom(String nom) {
        String sql = "SELECT * FROM membre WHERE nom = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nom);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("mot_de_passe"),
                        rs.getString("role")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Erreur findByNom : " + e.getMessage());
        }

        return null;
    }

    // Modifier un membre existant
    public static boolean updateMembre(User membre) {
        String sql = "UPDATE membre SET nom = ?, mot_de_passe = ?, role = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, membre.getUsername());
            stmt.setString(2, membre.getPassword());
            stmt.setString(3, membre.getRole());
            stmt.setInt(4, membre.getId());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Erreur modification membre : " + e.getMessage());
            return false;
        }
    }

    // Obtenir les membres d'un projet (sans les chefs)
    public static List<User> getTousLesMembresSansChefs() {
        List<User> membres = new ArrayList<>();
        String sql = "SELECT * FROM membre WHERE role = 'membre'";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                User u = new User(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("mot_de_passe"),
                        rs.getString("role")
                );
                membres.add(u);
            }

        } catch (SQLException e) {
            System.out.println("❌ Erreur getTousLesMembresSansChefs : " + e.getMessage());
        }

        return membres;
    }


    // Supprimer un membre (optionnel)
    public static boolean deleteMembre(int id) {
        String sql = "DELETE FROM membre WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("❌ Erreur suppression membre : " + e.getMessage());
            return false;
        }
    }
    
    public static String findByNomId(int id) {
        String sql = "SELECT nom FROM membre WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getString("nom");
        } catch (SQLException e) {
            System.out.println("❌ Erreur findByNomId : " + e.getMessage());
        }
        return "Inconnu";
    }


}
