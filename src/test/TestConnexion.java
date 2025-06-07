package test;
import java.sql.Connection;
import basedonnee.Database;
public class TestConnexion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Connection conn = Database.getConnection();

        if (conn != null) {
            System.out.println("✅ Connexion réussie à la base de données !");
        } else {
            System.out.println("❌ Connexion échouée !");
        }
	}

}
