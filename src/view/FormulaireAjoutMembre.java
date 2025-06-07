package view;

import dao.MembreDAO;
import model.Membre;

import javax.swing.*;
import java.awt.*;

public class FormulaireAjoutMembre extends JFrame {

    public FormulaireAjoutMembre() {
        setTitle("Créer un nouveau membre");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));

        JLabel lblNom = new JLabel("Nom :");
        JLabel lblPrenom = new JLabel("Prénom :");
        JLabel lblAge = new JLabel("Âge :");

        JTextField txtNom = new JTextField();
        JTextField txtPrenom = new JTextField();
        JTextField txtAge = new JTextField();

        JButton btnValider = new JButton("Ajouter");

        btnValider.addActionListener(e -> {
            String nom = txtNom.getText().trim();
            String prenom = txtPrenom.getText().trim();
            String ageStr = txtAge.getText().trim();

            if (nom.isEmpty() || prenom.isEmpty() || ageStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.");
                return;
            }

            int age;
            try {
                age = Integer.parseInt(ageStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "L'âge doit être un nombre.");
                return;
            }

            if (MembreDAO.verifierExistence(nom, prenom)) {
                JOptionPane.showMessageDialog(this, "❌ Ce membre existe déjà.");
            } else {
                Membre nouveau = new Membre(nom, prenom, age);
                boolean success = MembreDAO.ajouterMembre(nouveau);
                if (success) {
                    JOptionPane.showMessageDialog(this, "✅ Membre ajouté avec succès !");
                    dispose();  // fermer la fenêtre
                } else {
                    JOptionPane.showMessageDialog(this, "❌ Échec de l'ajout.");
                }
            }
        });

        add(lblNom); add(txtNom);
        add(lblPrenom); add(txtPrenom);
        add(lblAge); add(txtAge);
        add(new JLabel()); add(btnValider);

        setVisible(true);
    }

    // Pour test seul
    public static void main(String[] args) {
        SwingUtilities.invokeLater(FormulaireAjoutMembre::new);
    }
}
