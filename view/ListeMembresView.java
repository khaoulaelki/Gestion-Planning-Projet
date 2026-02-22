package view;

import model.User;
import model.UserService;

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

        // Adapter les colonnes à username, password, role
        model = new DefaultTableModel(new String[]{"ID", "Nom complet", "Mot de passe", "Rôle"}, 0);
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
            String username = (String) model.getValueAt(selectedRow, 1);
            String password = (String) model.getValueAt(selectedRow, 2);
            String role = (String) model.getValueAt(selectedRow, 3);

            User membre = new User(id, username, password, role);
            new FormulaireModificationMembre(membre);
            dispose();
        });

        add(btnModifier, BorderLayout.SOUTH);

        chargerMembres();

        setVisible(true);
    }

    private void chargerMembres() {
        model.setRowCount(0);  // Vider le tableau
        List<User> membres = UserService.getTousLesMembres();

        for (User m : membres) {
            model.addRow(new Object[]{
                m.getId(), m.getUsername(), m.getPassword(), m.getRole()
            });
        }
    }
}
