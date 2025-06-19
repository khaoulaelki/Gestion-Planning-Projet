package view;

import model.Projet;

import javax.swing.*;
import java.awt.*;

public class EtatProjetView extends JFrame {

    public EtatProjetView(Projet projet) {
        setTitle("État du projet");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1, 10, 10));

        add(new JLabel("📌 Nom du projet : " + projet.getNom()));
        add(new JLabel("🗓️ Date début : " + projet.getDateDebut()));
        add(new JLabel("🗓️ Date fin : " + projet.getDateFin()));
        add(new JLabel("📊 Statut : " + projet.getStatut()));

        JButton btnFermer = new JButton("Fermer");
        btnFermer.addActionListener(e -> dispose());

        add(btnFermer);
        setVisible(true);
    }
}
