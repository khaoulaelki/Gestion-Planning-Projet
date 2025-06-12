package view;

import model.Projet;
import model.ProjetService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ModifierProjetView extends JFrame {
    private JTextField champNom;
    private JTextArea champDescription;
    private JTextField champDateDebut;
    private JTextField champDateFin;
    private JTextField champStatut;
    private JButton btnSauvegarder;

    private Projet projet;

    public ModifierProjetView(Projet projet) {
        this.projet = projet;

        setTitle("Modifier le projet");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(7, 2, 10, 10));

        champNom = new JTextField(projet.getNom());
        champDescription = new JTextArea(projet.getDescription());
        champDateDebut = new JTextField(projet.getDateDebut().toString());
        champDateFin = new JTextField(projet.getDateFin().toString());
        champStatut = new JTextField(projet.getStatut());
        btnSauvegarder = new JButton("Sauvegarder les modifications");

        add(new JLabel("Nom du projet:"));
        add(champNom);
        add(new JLabel("Description:"));
        add(new JScrollPane(champDescription));
        add(new JLabel("Date de début (YYYY-MM-DD):"));
        add(champDateDebut);
        add(new JLabel("Date de fin (YYYY-MM-DD):"));
        add(champDateFin);
        add(new JLabel("Statut:"));
        add(champStatut);
        add(new JLabel(""));
        add(btnSauvegarder);

        btnSauvegarder.addActionListener((ActionEvent e) -> {
            try {
                projet.setNom(champNom.getText());
                projet.setDescription(champDescription.getText());
                projet.setDateDebut(parseDate(champDateDebut.getText()));
                projet.setDateFin(parseDate(champDateFin.getText()));
                projet.setStatut(champStatut.getText());

                boolean result = ProjetService.updateProjet(projet);
                if (result) {
                    JOptionPane.showMessageDialog(null, "Projet modifié avec succès !");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Erreur lors de la modification du projet.");
                }
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, "Format de date invalide (utilisez YYYY-MM-DD)");
            }
        });

        setVisible(true);
    }

    private Date parseDate(String dateStr) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate = format.parse(dateStr);
        return new Date(utilDate.getTime());
    }
}
