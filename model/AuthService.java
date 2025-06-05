package model;

import java.sql.*;

public class AuthService {
    public static User authenticate(String username, String password) {
        String sql = "SELECT * FROM membre WHERE nom = ? AND mot_de_passe = ?";

        try (Connection conn = DatabaseConnection.getConnection();  //etablit la connexion avec la base de donnees
             PreparedStatement stmt = conn.prepareStatement(sql)) { //Prépare une requête SQL avec des zones dynamiques (?)

            stmt.setString(1, username);    //Le 1er ? prend la valeur du nom d’utilisateur
            stmt.setString(2, password);    //Le 2e ? prend la valeur du mot de passe.



            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {   // Si une ligne est trouvée
                int id = rs.getInt("id");
                String role = rs.getString("role");
                return new User(id, username, password, role);   //On retourne un objet User contenant les données récupérées
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}