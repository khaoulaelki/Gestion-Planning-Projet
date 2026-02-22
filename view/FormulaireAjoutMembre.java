package view;

import model.User;
import model.UserService;

import javax.swing.*;
import java.awt.*;

public class FormulaireAjoutMembre extends JFrame {

    public FormulaireAjoutMembre() {
        setTitle("Créer un nouveau membre");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));

        // Étiquettes
        JLabel lblUsername = new JLabel("Nom d'utilisateur :");
        JLabel lblPassword = new JLabel("Mot de passe :");
        JLabel lblRole = new JLabel("Rôle :");

        // Champs de saisie
        JTextField txtUsername = new JTextField();
        JPasswordField txtPassword = new JPasswordField();
        JComboBox<String> comboRole = new JComboBox<>(new String[]{"membre", "chef"});

        JButton btnValider = new JButton("Ajouter");

        // Action bouton
        btnValider.addActionListener(e -> {
            String username = txtUsername.getText().trim();
            String password = new String(txtPassword.getPassword()).trim();
            String role = comboRole.getSelectedItem().toString();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.");
                return;
            }

            if (UserService.verifierExistence(username)) {
                JOptionPane.showMessageDialog(this, "❌ Ce membre existe déjà.");
            } else {
                User nouveau = new User(0, username, password, role);
                boolean success = UserService.ajouterMembre(nouveau);
                if (success) {
                    JOptionPane.showMessageDialog(this, "✅ Membre ajouté avec succès !");
                    dispose();  // Fermer la fenêtre
                } else {
                    JOptionPane.showMessageDialog(this, "❌ Échec de l'ajout.");
                }
            }
        });

        // Ajout des composants dans la fenêtre
        add(lblUsername); add(txtUsername);
        add(lblPassword); add(txtPassword);
        add(lblRole); add(comboRole);
        add(new JLabel()); add(btnValider);

        setVisible(true);
    }

    
}
