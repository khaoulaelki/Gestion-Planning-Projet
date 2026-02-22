package view;

import model.ProjetService;
import model.Projet;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;

public class FormulaireAjoutProjet extends JFrame {

    public FormulaireAjoutProjet() {
        setTitle("Ajouter un projet");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 10, 10));
        // Champs de saisie
        JTextField txtNom = new JTextField();
        JTextField txtDescription = new JTextField();
        JTextField txtDateDebut = new JTextField("yyyy-mm-dd");
        JTextField txtDateFin = new JTextField("yyyy-mm-dd");
        JComboBox<String> comboStatut = new JComboBox<>(new String[]{"En attente", "En cours", "Terminé"});
        JButton btnValider = new JButton("Créer");

        // Action bouton "Créer projet"
        btnValider.addActionListener(e -> {
            String nom = txtNom.getText().trim();
            String description = txtDescription.getText().trim();
            String d1 = txtDateDebut.getText().trim();
            String d2 = txtDateFin.getText().trim();
            String statut = (String) comboStatut.getSelectedItem();

            if (nom.isEmpty() || description.isEmpty() || d1.isEmpty() || d2.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.");
                return;
            }             //Rendre tous les champs obligatoires

            try {
                Date dateDebut = Date.valueOf(d1);
                Date dateFin = Date.valueOf(d2);

                if (ProjetService.verifierExistenceProjet(nom)) {
                    JOptionPane.showMessageDialog(this, "❌ Projet déjà existant !");
                } else {
                    Projet p = new Projet(0, nom, description, dateDebut, dateFin, statut);
                    int idProjet = ProjetService.ajouterProjetEtRetournerId(p); 

                    if (idProjet != -1) {
                        JOptionPane.showMessageDialog(this, "✅ Projet ajouté !");
                        this.dispose(); // Fermer la fenêtre
                        new FormulaireAjoutTache(idProjet); // ouvrir la fenêtre d’ajout des tâches
                    } else {
                        JOptionPane.showMessageDialog(this, "❌ Erreur lors de l'ajout.");
                    }
                }
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, "❌ Format de date incorrect. Utilisez yyyy-mm-dd.");
            }
        });

        // Placement des composants
        add(new JLabel("Nom du projet :")); add(txtNom);
        add(new JLabel("Description :")); add(txtDescription);
        add(new JLabel("Date début :")); add(txtDateDebut);
        add(new JLabel("Date fin :")); add(txtDateFin);
        add(new JLabel("Statut :")); add(comboStatut);
        add(new JLabel()); add(btnValider); // bouton aligné à droite

        setVisible(true);
    }

   
}
