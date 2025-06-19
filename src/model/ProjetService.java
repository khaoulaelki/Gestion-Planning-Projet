package model;

import basedonnee.Database; 

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjetService {

    public static List<Projet> getTousLesProjets() {
        List<Projet> projets = new ArrayList<>();
        String sql = "SELECT * FROM projet";

        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Projet p = new Projet(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getDate("date_debut"),
                    rs.getDate("date_fin"),
                    rs.getString("statut")
                );
                projets.add(p);
            }

        } catch (SQLException e) {
            System.out.println("❌ Erreur récupération projets : " + e.getMessage());
        }

        return projets;
    }
}
