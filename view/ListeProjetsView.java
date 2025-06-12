package view;

import model.Projet;
import model.ProjetService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListeProjetsView extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public ListeProjetsView() {
        setTitle("Liste des projets");
        setSize(900, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // 🆗 Ajout de la colonne "Description"
        model = new DefaultTableModel(
            new String[]{"ID", "Nom", "Description", "Date Début", "Date Fin", "Statut"}, 0
        );
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton btnModifier = new JButton("Modifier le projet sélectionné");

        btnModifier.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner un projet.");
                return;
            }

            int id = (int) model.getValueAt(row, 0);
            Projet projet = ProjetService.getProjetParId(id); // ✅ utilisation de ProjetService

            if (projet != null) {
                new ProjetActionsView(projet); // cette classe doit exister
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Projet introuvable.");
            }
        });

        add(btnModifier, BorderLayout.SOUTH);

        chargerProjets();

        setVisible(true);
    }

    private void chargerProjets() {
        model.setRowCount(0);
        List<Projet> projets = ProjetService.getTousLesProjets();
        for (Projet p : projets) {
            model.addRow(new Object[]{
                p.getId(),
                p.getNom(),
                p.getDescription(),
                p.getDateDebut(),
                p.getDateFin(),
                p.getStatut()
            });
        }
    }
}
