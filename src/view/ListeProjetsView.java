package view;

import dao.ProjetDAO;
import model.Projet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListeProjetsView extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public ListeProjetsView() {
        setTitle("Liste des projets");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        model = new DefaultTableModel(new String[]{"ID", "Nom", "Date Début", "Date Fin", "Statut"}, 0);
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
            Projet projet = ProjetDAO.getProjetParId(id);

            if (projet != null) {
                new ProjetActionsView(projet);
                dispose();
            }
        });

        add(btnModifier, BorderLayout.SOUTH);

        chargerProjets();

        setVisible(true);
    }

    private void chargerProjets() {
        model.setRowCount(0);
        List<Projet> projets = ProjetDAO.getTousLesProjets();
        for (Projet p : projets) {
            model.addRow(new Object[]{
                p.getId(),
                p.getNom(),
                p.getDateDebut(),
                p.getDateFin(),
                p.getStatut()
            });
        }
    }
}
