package view;

import javax.swing.*;
import java.awt.*;

public class ProjetTacheDashboard extends JFrame {

    public ProjetTacheDashboard(int idProjet) {
        setTitle("Gestion des Tâches du Projet " + idProjet);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 1, 10, 10));

        JLabel titre = new JLabel("Tâches du Projet #" + idProjet, SwingConstants.CENTER);
        titre.setFont(new Font("Arial", Font.BOLD, 16));

        JButton btnAjout = new JButton("➕ Ajouter une tâche");
        JButton btnListe = new JButton("📋 Liste des tâches");
        JButton btnGantt = new JButton("📈 Diagramme de Gantt");

        // Actions
        btnAjout.addActionListener(e -> new FormulaireAjoutTache(idProjet));
        btnListe.addActionListener(e -> {
            JFrame frame = new JFrame("Tâches du projet");
            frame.setSize(600, 400);
            frame.add(new ListeTachesPanel(idProjet));
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
        btnGantt.addActionListener(e -> {
            JFrame frame = new JFrame("Diagramme de Gantt");
            frame.setSize(800, 500);
            frame.add(new GanttPanel(idProjet));
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

        add(titre);
        add(btnAjout);
        add(btnListe);
        add(btnGantt);

        setVisible(true);
    }
}
