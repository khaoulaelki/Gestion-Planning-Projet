package view;

import dao.ProjetDAO;
import model.Projet;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class FormulaireAjoutProjet extends JFrame {

    public FormulaireAjoutProjet() {
        setTitle("Ajouter un projet");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));

        JTextField txtNom = new JTextField();
        JTextField txtDateDebut = new JTextField("yyyy-mm-dd");
        JTextField txtDateFin = new JTextField("yyyy-mm-dd");
        JComboBox<String> comboStatut = new JComboBox<>(new String[]{"En attente", "En cours", "Terminé"});
        JButton btnValider = new JButton("Créer");

        btnValider.addActionListener(e -> {
            String nom = txtNom.getText().trim();
            String d1 = txtDateDebut.getText().trim();
            String d2 = txtDateFin.getText().trim();
            String statut = (String) comboStatut.getSelectedItem();

            if (nom.isEmpty() || d1.isEmpty() || d2.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs obligatoires.");
                return;
            }

            try {
                Date dateDebut = java.sql.Date.valueOf(d1);
                Date dateFin = java.sql.Date.valueOf(d2);

                if (ProjetDAO.verifierExistenceProjet(nom)) {
                    JOptionPane.showMessageDialog(this, "❌ Projet déjà existant !");
                } else {
                    Projet p = new Projet(nom, "", dateDebut, dateFin, statut); // description vide
                    boolean ok = ProjetDAO.ajouterProjet(p);
                    if (ok) {
                        JOptionPane.showMessageDialog(this, "✅ Projet ajouté avec succès !");
                        dispose();
                        new ProjetDashboard(); // retour
                    } else {
                        JOptionPane.showMessageDialog(this, "❌ Erreur lors de l'ajout.");
                    }
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Format de date incorrect. Format attendu : yyyy-mm-dd");
            }
        });

        add(new JLabel("Nom du projet :")); add(txtNom);
        add(new JLabel("Date début :")); add(txtDateDebut);
        add(new JLabel("Date fin :")); add(txtDateFin);
        add(new JLabel("Statut :")); add(comboStatut);
        add(new JLabel()); add(btnValider);

        setVisible(true);
    }
}
