package view;

import model.UserService;
import model.User;

import javax.swing.*;
import java.awt.*;

public class FormulaireModificationMembre extends JFrame {

    public FormulaireModificationMembre(User membre) {
        setTitle("Modifier membre");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));

        // Champs correspondant à la classe User
        JTextField txtUsername = new JTextField(membre.getUsername());
        JTextField txtPassword = new JTextField(membre.getPassword());
        JComboBox<String> comboRole = new JComboBox<>(new String[]{"membre", "chef_projet"});
        comboRole.setSelectedItem(membre.getRole());

        JButton btnModifier = new JButton("Modifier");

        btnModifier.addActionListener(e -> {
            String username = txtUsername.getText().trim();
            String password = txtPassword.getText().trim();
            String role = (String) comboRole.getSelectedItem();

            if (username.isEmpty() || password.isEmpty() || role.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.");
                return;
            }

            membre.setUsername(username);
            membre.setPassword(password);
            membre.setRole(role);

            boolean updated = UserService.updateMembre(membre);
            if (updated) {
                JOptionPane.showMessageDialog(this, "✅ Membre modifié avec succès !");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "❌ Échec de la modification.");
            }
        });

        // Placement des composants
        add(new JLabel("Nom d'utilisateur :")); add(txtUsername);
        add(new JLabel("Mot de passe :")); add(txtPassword);
        add(new JLabel("Rôle :")); add(comboRole);
        add(new JLabel()); add(btnModifier);

        setVisible(true);
    }
}
