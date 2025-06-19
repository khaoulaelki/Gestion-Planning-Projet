package view;

import model.Projet;
import model.Tache;
import model.TacheService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class EtatTachesView extends JFrame {

    public EtatTachesView(Projet projet) {
        setTitle("État des tâches du projet : " + projet.getNom());
        setSize(700, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        String[] colonnes = {"Titre", "Description", "Date échéance", "Statut", "Responsable"};
        DefaultTableModel model = new DefaultTableModel(colonnes, 0);
        JTable table = new JTable(model);

        List<Tache> taches = TacheService.getTachesParProjet(projet.getId());
        for (Tache t : taches) {
            model.addRow(new Object[]{
                t.getTitre(),
                t.getDescription(),
                t.getDateEcheance(),
                t.getStatut(),
                t.getResponsable()
            });
        }

        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton btnFermer = new JButton("Fermer");
        btnFermer.addActionListener(e -> dispose());
        add(btnFermer, BorderLayout.SOUTH);

        setVisible(true);
    }
}
