package view;
import javax.swing.*;
import java.awt.*;
public class MembreDashboard extends JFrame {

	public MembreDashboard() {
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 1, 10, 10));

        JLabel titre = new JLabel("Gestion des membres", SwingConstants.CENTER);
        titre.setFont(new Font("Arial", Font.BOLD, 16));

        JButton btnListe = new JButton("🔍 Voir la liste des membres");
        JButton btnAjouter = new JButton("➕ Créer un nouveau membre");
     // Actions
        btnListe.addActionListener(e -> {
            new ListeMembresView();                  
            dispose();
        });

        btnAjouter.addActionListener(e -> {
            new FormulaireAjoutMembre();  
            dispose();
        });
        
        add(titre);
        add(btnListe);
        add(btnAjouter);

        setVisible(true);
    }

    // Pour test
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MembreDashboard::new);
    }
}
