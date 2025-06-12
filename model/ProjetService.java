package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjetService {

	public static boolean ajouterProjet(Projet projet) {
	    String sql = "INSERT INTO projet (nom, description, date_debut, date_fin, statut) VALUES (?, ?, ?, ?, ?)";

	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, projet.getNom());
	        stmt.setString(2, projet.getDescription());
	        stmt.setDate(3, new java.sql.Date(projet.getDateDebut().getTime()));
	        stmt.setDate(4, new java.sql.Date(projet.getDateFin().getTime()));
	        stmt.setString(5, projet.getStatut());

	        stmt.executeUpdate();
	        return true;

	    } catch (SQLException e) {
	        System.out.println("❌ Erreur ajout projet : " + e.getMessage());
	        return false;
	    }
	}


    // Vérifier si un projet existe déjà
    public static boolean verifierExistenceProjet(String nom) {
        String sql = "SELECT COUNT(*) FROM projet WHERE nom = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nom);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            System.out.println("❌ Erreur vérification projet : " + e.getMessage());
        }

        return false;
    }

    // Récupérer tous les projets
    public static List<Projet> getTousLesProjets() {
        List<Projet> projets = new ArrayList<>();
        String sql = "SELECT * FROM projet";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Projet p = new Projet(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("description"),
                    rs.getDate("date_debut"),
                    rs.getDate("date_fin"),
                    rs.getString("statut")
                );
                projets.add(p);
            }

        } catch (SQLException e) {
            System.out.println("❌ Erreur lecture projets : " + e.getMessage());
        }

        return projets;
    }

    // Récupérer un projet par son ID
    public static Projet getProjetParId(int id) {
        String sql = "SELECT * FROM projet WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Projet(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("description"),  // correction ici
                    rs.getDate("date_debut"),
                    rs.getDate("date_fin"),
                    rs.getString("statut")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Erreur récupération projet : " + e.getMessage());
        }

        return null;
    }


    // Mettre à jour un projet
    public static boolean updateProjet(Projet projet) {
        String sql = "UPDATE projet SET nom = ?, description = ?, date_debut = ?, date_fin = ?, statut = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, projet.getNom());
            stmt.setString(2, projet.getDescription()); // description ajoutée ici
            stmt.setDate(3, new java.sql.Date(projet.getDateDebut().getTime()));
            stmt.setDate(4, new java.sql.Date(projet.getDateFin().getTime()));
            stmt.setString(5, projet.getStatut());
            stmt.setInt(6, projet.getId());

            stmt.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("❌ Erreur modification projet : " + e.getMessage());
            return false;
        }
    }
    public static int ajouterProjetEtRetournerId(Projet projet) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO projet (nom, description, date_debut, date_fin, statut) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, projet.getNom());
            ps.setString(2, projet.getDescription());
            ps.setDate(3, projet.getDateDebut());
            ps.setDate(4, projet.getDateFin());
            ps.setString(5, projet.getStatut());
            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1); // retourne l’ID du projet
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // erreur
    }


}
