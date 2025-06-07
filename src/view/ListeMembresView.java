package view;

import dao.MembreDAO;
import model.Membre;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListeMembresView extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public ListeMembresView() {
        setTitle("Liste des membres");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Modèle du tableau
        model = new DefaultTableModel(new String[]{"ID", "Nom", "Prénom", "Âge"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton btnModifier = new JButton("Modifier le membre sélectionné");

        btnModifier.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner un membre.");
                return;
            }

            int id = (int) model.getValueAt(selectedRow, 0);
            String nom = (String) model.getValueAt(selectedRow, 1);
            String prenom = (String) model.getValueAt(selectedRow, 2);
            int age = (int) model.getValueAt(selectedRow, 3);

            Membre membre = new Membre(id, nom, prenom, age);
            // TODO: Ouvrir formulaire de modification
            new FormulaireModificationMembre(membre);
            dispose();

        });

        add(btnModifier, BorderLayout.SOUTH);

        chargerMembres();

        setVisible(true);
    }

    private void chargerMembres() {
        model.setRowCount(0);  // Vider le tableau
        List<Membre> membres = MembreDAO.getTousLesMembres();

        for (Membre m : membres) {
            model.addRow(new Object[]{
                m.getId(), m.getNom(), m.getPrenom(), m.getAge()
            });
        }
    }
}
