package basedonnee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static final String URL = "jdbc:mysql://localhost:3306/planning_db";
    private static final String USER = "root";         
    private static final String PASSWORD = "hajar1234";        

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("❌ Erreur de connexion : " + e.getMessage());
            return null;
        }
    }
}
