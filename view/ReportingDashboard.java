package view;

import model.Projet;
import model.ProjetService;
import model.Task;
import model.TaskService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class ReportingDashboard extends JFrame {

    private JComboBox<Projet> comboProjets;
    private JTextArea txtDetails;

    public ReportingDashboard() {
        setTitle("Suivi & Reporting");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Haut : sélection projet
        JPanel topPanel = new JPanel();
        comboProjets = new JComboBox<>(ProjetService.getTousLesProjets().toArray(new Projet[0]));
        JButton btnAfficher = new JButton("Afficher le reporting");
        topPanel.add(new JLabel("Choisir un projet :"));
        topPanel.add(comboProjets);
        topPanel.add(btnAfficher);
        add(topPanel, BorderLayout.NORTH);

        // Centre : zone de texte avec état des tâches
        txtDetails = new JTextArea();
        txtDetails.setEditable(false);
        add(new JScrollPane(txtDetails), BorderLayout.CENTER);

        // Bas : export
        JPanel bottomPanel = new JPanel();
        JButton btnPDF = new JButton("Exporter en PDF");
        JButton btnExcel = new JButton("Exporter en Excel");
        bottomPanel.add(btnPDF);
        bottomPanel.add(btnExcel);
        add(bottomPanel, BorderLayout.SOUTH);

        // Actions
        btnAfficher.addActionListener((ActionEvent e) -> afficherRapport());
        btnPDF.addActionListener(e -> ExportService.exporterPDF(getProjetSelectionne(), txtDetails.getText()));
        btnExcel.addActionListener(e -> ExportService.exporterExcel(getProjetSelectionne(), txtDetails.getText()));

        setVisible(true);
    }

    private Projet getProjetSelectionne() {
        return (Projet) comboProjets.getSelectedItem();
    }

    private void afficherRapport() {
        Projet projet = getProjetSelectionne();
        if (projet == null) {
            txtDetails.setText("Aucun projet sélectionné.");
            return;
        }

        List<Task> taches = TaskService.getTachesParProjet(projet.getId());
        long total = taches.size();
        long terminees = taches.stream().filter(t -> t.getStatut().equalsIgnoreCase("terminé")).count();
        double avancement = total == 0 ? 0 : (terminees * 100.0) / total;

        StringBuilder sb = new StringBuilder();
        sb.append("Projet : ").append(projet.getNom()).append("\n");
        sb.append("Description : ").append(projet.getDescription()).append("\n");
        sb.append("Avancement : ").append(String.format("%.2f", avancement)).append(" %\n\n");

        sb.append("État des tâches :\n");
        for (Task t : taches) {
            sb.append("- ").append(t.getTitre()).append(" : ").append(t.getStatut()).append("\n");
        }

        txtDetails.setText(sb.toString());
    }
}
