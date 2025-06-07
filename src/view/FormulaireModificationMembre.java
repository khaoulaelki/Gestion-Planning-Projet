package view;

import dao.MembreDAO;
import model.Membre;

import javax.swing.*;
import java.awt.*;

public class FormulaireModificationMembre extends JFrame {

    public FormulaireModificationMembre(Membre membre) {
        setTitle("Modifier membre");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));

        JTextField txtNom = new JTextField(membre.getNom());
        JTextField txtPrenom = new JTextField(membre.getPrenom());
        JTextField txtAge = new JTextField(String.valueOf(membre.getAge()));

        JButton btnModifier = new JButton("Modifier");

        btnModifier.addActionListener(e -> {
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

            membre.setNom(nom);
            membre.setPrenom(prenom);
            membre.setAge(age);

            boolean updated = MembreDAO.updateMembre(membre);
            if (updated) {
                JOptionPane.showMessageDialog(this, "✅ Membre modifié avec succès !");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "❌ Échec de la modification.");
            }
        });

        add(new JLabel("Nom :")); add(txtNom);
        add(new JLabel("Prénom :")); add(txtPrenom);
        add(new JLabel("Âge :")); add(txtAge);
        add(new JLabel()); add(btnModifier);

        setVisible(true);
    }
}
