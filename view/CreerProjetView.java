package view;

import model.Projet;
import model.ProjetService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class CreerProjetView extends JFrame {
    private JTextField champNom;
    private JTextArea champDescription;
    private JTextField champDateDebut;
    private JTextField champDateFin;
    private JButton btnEnregistrer;

    public CreerProjetView() {
        setTitle("Créer un projet");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 10, 10));

        champNom = new JTextField();
        champDescription = new JTextArea();
        champDateDebut = new JTextField("YYYY-MM-DD");
        champDateFin = new JTextField("YYYY-MM-DD");
        btnEnregistrer = new JButton("Enregistrer");

        add(new JLabel("Nom du projet:"));
        add(champNom);
        add(new JLabel("Description:"));
        add(new JScrollPane(champDescription));
        add(new JLabel("Date de début:"));
        add(champDateDebut);
        add(new JLabel("Date de fin:"));
        add(champDateFin);
        add(new JLabel(""));
        add(btnEnregistrer);

        btnEnregistrer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nom = champNom.getText().trim();
                String description = champDescription.getText().trim();
                String dateDebutStr = champDateDebut.getText().trim();
                String dateFinStr = champDateFin.getText().trim();

                if (nom.isEmpty() || dateDebutStr.isEmpty() || dateFinStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs obligatoires.");
                    return;
                }

                try {
                    Date dateDebut = Date.valueOf(dateDebutStr); // Format YYYY-MM-DD
                    Date dateFin = Date.valueOf(dateFinStr);

                    Projet projet = new Projet(0, nom, description, dateDebut, dateFin, "En cours");

                    boolean succes = ProjetService.ajouterProjet(projet);
                    if (succes) {
                        JOptionPane.showMessageDialog(null, "Projet créé avec succès !");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Erreur lors de la création du projet.");
                    }
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, "Format de date invalide. Utilisez YYYY-MM-DD.");
                }
            }
        });

        setVisible(true);
    }
}
