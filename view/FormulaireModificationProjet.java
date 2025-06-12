package view;

import model.Projet;
import model.ProjetService;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class FormulaireModificationProjet extends JFrame {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public FormulaireModificationProjet(Projet projet) {
        setTitle("Modifier projet");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 10, 10));

        // Champs
        JTextField txtNom = new JTextField(projet.getNom());
        JTextArea txtDescription = new JTextArea(projet.getDescription(), 3, 20);
        JTextField txtDateDebut = new JTextField(dateFormat.format(projet.getDateDebut()));
        JTextField txtDateFin = new JTextField(dateFormat.format(projet.getDateFin()));
        JComboBox<String> comboStatut = new JComboBox<>(new String[]{"En attente", "En cours", "Terminé"});
        comboStatut.setSelectedItem(projet.getStatut());

        JButton btnModifier = new JButton("Modifier");

        btnModifier.addActionListener(e -> {
            String nom = txtNom.getText().trim();
            String description = txtDescription.getText().trim();
            String d1 = txtDateDebut.getText().trim();
            String d2 = txtDateFin.getText().trim();
            String statut = (String) comboStatut.getSelectedItem();

            if (nom.isEmpty() || description.isEmpty() || d1.isEmpty() || d2.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.");
                return;
            }

            try {
                Date dateDebut = new Date(dateFormat.parse(d1).getTime());
                Date dateFin = new Date(dateFormat.parse(d2).getTime());

                if (dateFin.before(dateDebut)) {
                    JOptionPane.showMessageDialog(this, "La date de fin ne peut pas être avant la date de début.");
                    return;
                }

                // Mise à jour de l'objet projet
                projet.setNom(nom);
                projet.setDescription(description);
                projet.setDateDebut(dateDebut);
                projet.setDateFin(dateFin);
                projet.setStatut(statut);

                boolean ok = ProjetService.updateProjet(projet);
                if (ok) {
                    JOptionPane.showMessageDialog(this, "✅ Projet modifié avec succès !");
                    dispose();
                    new ListeProjetsView(); // rechargement
                } else {
                    JOptionPane.showMessageDialog(this, "❌ Échec de la modification.");
                }

            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this, "Format de date invalide. Utilisez yyyy-MM-dd.");
            }
        });

        // Ajout des composants
        add(new JLabel("Nom :")); add(txtNom);
        add(new JLabel("Description :")); add(new JScrollPane(txtDescription));
        add(new JLabel("Date début (yyyy-MM-dd) :")); add(txtDateDebut);
        add(new JLabel("Date fin (yyyy-MM-dd) :")); add(txtDateFin);
        add(new JLabel("Statut :")); add(comboStatut);
        add(new JLabel()); add(btnModifier);

        setVisible(true);
    }
}
