package view;

import model.Projet;
import model.ProjetService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SelectionProjetView extends JFrame {

    private JComboBox<Projet> comboProjets;

    public SelectionProjetView() {
        setTitle("Sélection du projet");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel supérieur
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Choisir un projet :"));

        comboProjets = new JComboBox<>();
        List<Projet> projets = ProjetService.getTousLesProjets();
        for (Projet p : projets) {
            comboProjets.addItem(p);
        }

        topPanel.add(comboProjets);
        add(topPanel, BorderLayout.NORTH);

        // Panel des boutons
        JPanel btnPanel = new JPanel(new GridLayout(3, 1, 10, 10));

        JButton btnEtatProjet = new JButton("📌 État du projet");
        JButton btnEtatTaches = new JButton("📝 État des tâches");
        JButton btnRapport = new JButton("📤 Générer rapport");

        btnEtatProjet.addActionListener(e -> {
            Projet projet = (Projet) comboProjets.getSelectedItem();
            if (projet != null) {
                new EtatProjetView(projet);
            }
        });

        btnEtatTaches.addActionListener(e -> {
            Projet projet = (Projet) comboProjets.getSelectedItem();
            if (projet != null) {
                new EtatTachesView(projet);
            }
        });

        btnRapport.addActionListener(e -> {
            Projet projet = (Projet) comboProjets.getSelectedItem();
            if (projet != null) {
                new ExportReportingView(projet);
            }
        });

        btnPanel.add(btnEtatProjet);
        btnPanel.add(btnEtatTaches);
        btnPanel.add(btnRapport);

        add(btnPanel, BorderLayout.CENTER);
        setVisible(true);
    }
}
