package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlanningService {

    public static List<Task> getPlanningByMembreId(int idMembre) {
        List<Task> planning = new ArrayList<>();
        String sql = "SELECT * FROM tache WHERE id_membre = ? ORDER BY date_echeance ASC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idMembre);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String titre = rs.getString("titre");
                String statut = rs.getString("statut");
                String date = rs.getDate("date_echeance").toString();
                String priorite = rs.getString("priorite");
                int idProjet = rs.getInt("id_projet");

                planning.add(new Task(id, titre, statut, date, priorite, idProjet, idMembre));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return planning;
    }
}
