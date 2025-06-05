package model;

import java.sql.Connection;
import java.sql.DriverManager;   //permet d’obtenir une connexion via getConnection()
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/gestion_projet?useSSL=false&serverTimezone=UTC";  //se connecter à un serveur MySQL local
    private static final String USER = "root";       
    private static final String PASSWORD = "root";       

    public static Connection getConnection() throws SQLException {
        try {
            // Charger explicitement le driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver MySQL introuvable dans le classpath.", e);
        }

        return DriverManager.getConnection(URL, USER, PASSWORD); //Si le driver a bien été chargé, on établit la connexion avec les identifiants et l’URL fournis
    }
}
