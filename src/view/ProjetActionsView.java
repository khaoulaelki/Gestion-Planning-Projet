package view;

import model.Projet;

import javax.swing.*;
import java.awt.*;

public class ProjetActionsView extends JFrame {

    private Projet projet;

    public ProjetActionsView(Projet projet) {
        this.projet = projet;

        setTitle("Actions sur le projet : " + projet.getNom());
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 1, 10, 10));

        JLabel titre = new JLabel("Projet sélectionné : " + projet.getNom(), SwingConstants.CENTER);
        titre.setFont(new Font("Arial", Font.BOLD, 16));

        JButton btnSuivi = new JButton("🔎 Suivi de l'état du projet");
        JButton btnModifier = new JButton("⚙️ Modifier les paramètres du projet");
        JButton btnTaches = new JButton("📋 Gestion des tâches");

        btnSuivi.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Statut actuel : " + projet.getStatut());
        });

        btnModifier.addActionListener(e -> {
            new FormulaireModificationProjet(projet);
            dispose();
        });

        btnTaches.addActionListener(e -> {
            // TODO: remplacer par vraie gestion des tâches plus tard
            JOptionPane.showMessageDialog(this, "Interface de gestion des tâches à venir...");
        });

        add(titre);
        add(btnSuivi);
        add(btnModifier);
        add(btnTaches);

        setVisible(true);
    }
}
