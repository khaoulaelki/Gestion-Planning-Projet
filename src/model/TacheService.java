package model;

import basedonnee.Database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TacheService {

    public static List<Tache> getTachesParProjet(int idProjet) {
        List<Tache> taches = new ArrayList<>();
        String sql = "SELECT * FROM tache WHERE id_projet = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProjet);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Tache t = new Tache(
                    rs.getInt("id"),
                    rs.getString("titre"),
                    rs.getString("description"),
                    rs.getDate("date_echeance"),
                    rs.getString("statut"),
                    rs.getString("responsable"),
                    rs.getInt("id_projet")
                );
                taches.add(t);
            }

        } catch (SQLException e) {
            System.out.println("❌ Erreur lecture taches : " + e.getMessage());
        }

        return taches;
    }
}
