package view;

import model.Projet;
import model.Tache;
import model.TacheService;
import utils.ExportPDF;
import utils.ExportExcel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ExportReportingView extends JFrame {

    public ExportReportingView(Projet projet) {
        setTitle("Générer un rapport pour le projet : " + projet.getNom());
        setSize(400, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 10, 10));

        JLabel label = new JLabel("Choisir le format du rapport :");
        JComboBox<String> comboFormat = new JComboBox<>(new String[]{"PDF", "Excel"});
        JButton btnGenerer = new JButton("Générer");

        btnGenerer.addActionListener(e -> {
            String format = (String) comboFormat.getSelectedItem();
            List<Tache> taches = TacheService.getTachesParProjet(projet.getId());

            if (format.equals("PDF")) {
                ExportPDF.generer(projet, taches);
            } else {
                ExportExcel.generer(projet, taches);
            }

            JOptionPane.showMessageDialog(this, "✅ Rapport généré avec succès !");
        });

        add(label);
        add(comboFormat);
        add(btnGenerer);

        JButton btnFermer = new JButton("Fermer");
        btnFermer.addActionListener(e -> dispose());
        add(btnFermer);

        setVisible(true);
    }
}
