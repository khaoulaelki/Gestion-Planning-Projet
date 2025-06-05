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

            stmt.setInt(1, idMembre);   //Remplace le ? de la requête SQL par la vraie valeur idMembre
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {  //Parcourt chaque ligne du résultat retourné par la base de données
                int id = rs.getInt("id");
                String titre = rs.getString("titre");
                String etat = rs.getString("etat");
                String date = rs.getDate("date_echeance").toString();
                int priorite = rs.getInt("priorite");

                planning.add(new Task(id, titre, etat, date, priorite)); //Crée un nouvel objet Task avec ces données, et l’ajoute à la liste planning
            }

        } catch (SQLException e) {
            e.printStackTrace();   //Si une erreur SQL survient on affiche l’erreur dans la console.

        }

        return planning;
    }
}
