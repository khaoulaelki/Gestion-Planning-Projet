package view;

import dao.ProjetDAO;
import model.Projet;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class FormulaireModificationProjet extends JFrame {

    public FormulaireModificationProjet(Projet projet) {
        setTitle("Modifier projet");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));

        JTextField txtNom = new JTextField(projet.getNom());
        JTextField txtDateDebut = new JTextField(projet.getDateDebut().toString());
        JTextField txtDateFin = new JTextField(projet.getDateFin().toString());
        JComboBox<String> comboStatut = new JComboBox<>(new String[]{"En attente", "En cours", "Terminé"});
        comboStatut.setSelectedItem(projet.getStatut());

        JButton btnModifier = new JButton("Modifier");

        btnModifier.addActionListener(e -> {
            String nom = txtNom.getText().trim();
            String d1 = txtDateDebut.getText().trim();
            String d2 = txtDateFin.getText().trim();
            String statut = (String) comboStatut.getSelectedItem();

            if (nom.isEmpty() || d1.isEmpty() || d2.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.");
                return;
            }

            try {
                Date dateDebut = java.sql.Date.valueOf(d1);
                Date dateFin = java.sql.Date.valueOf(d2);

                projet.setNom(nom);
                projet.setDateDebut(dateDebut);
                projet.setDateFin(dateFin);
                projet.setStatut(statut);

                boolean ok = ProjetDAO.updateProjet(projet);
                if (ok) {
                    JOptionPane.showMessageDialog(this, "✅ Projet modifié avec succès !");
                    dispose();
                    new ListeProjetsView(); // refresh
                } else {
                    JOptionPane.showMessageDialog(this, "❌ Échec de la modification.");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Format de date invalide (yyyy-mm-dd).");
            }
        });

        add(new JLabel("Nom :")); add(txtNom);
        add(new JLabel("Date début :")); add(txtDateDebut);
        add(new JLabel("Date fin :")); add(txtDateFin);
        add(new JLabel("Statut :")); add(comboStatut);
        add(new JLabel()); add(btnModifier);

        setVisible(true);
    }
}
