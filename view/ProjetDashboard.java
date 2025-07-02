package view;

import javax.swing.*;
import java.awt.*;

public class ProjetDashboard extends JFrame {

    public ProjetDashboard() {
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 1, 10, 10));

        JLabel titre = new JLabel("Gestion des projets", SwingConstants.CENTER);
        titre.setFont(new Font("Arial", Font.BOLD, 16));

        JButton btnCreer = new JButton("➕ Créer un projet");
        JButton btnListe = new JButton("📋 Liste des projets existants");

        btnCreer.addActionListener(e -> {
            new FormulaireAjoutProjet();
            dispose();
        });

        btnListe.addActionListener(e -> {
            new ListeProjetsView();
            dispose();
        });

        add(titre);
        add(btnCreer);
        add(btnListe);

        setVisible(true);
    }

    
}
