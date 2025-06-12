package view;

import model.Task;
import model.TaskService;
import model.User;
import model.UserService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GestionTachesPanel extends JPanel {

    private int projetId;
    private JComboBox<User> comboMembre;
    private JTextField titreField;
    private JTextField dateField;
    private JComboBox<String> prioriteBox;
    private JComboBox<String> statutBox;
    private JButton ajouterButton;
    private JPanel listeTachesPanel;
    private JPanel ganttPanel;

    public GestionTachesPanel(int projetId) {
        this.projetId = projetId;
        setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("Créer Tâche", creerPanelAjout());
        tabbedPane.add("Liste des Tâches", new JScrollPane(creerPanelListe()));
        tabbedPane.add("Diagramme de Gantt", new JScrollPane(creerPanelGantt()));

        add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel creerPanelAjout() {
        JPanel panel = new JPanel(new GridLayout(7, 2));

        titreField = new JTextField();
        dateField = new JTextField("2025-06-30");
        prioriteBox = new JComboBox<>(new String[]{"basse", "moyenne", "haute"});
        statutBox = new JComboBox<>(new String[]{"à faire", "en cours", "terminé"});

        comboMembre = new JComboBox<>();
        for (User m : UserService.getTousLesMembresSansChefs()) {
            comboMembre.addItem(m);
        }

        ajouterButton = new JButton("Ajouter");
        ajouterButton.addActionListener(e -> {
            String titre = titreField.getText();
            String date = dateField.getText();
            String priorite = (String) prioriteBox.getSelectedItem();
            String statut = (String) statutBox.getSelectedItem();
            User membre = (User) comboMembre.getSelectedItem();

            if (membre != null && !titre.isEmpty()) {
                Task tache = new Task(0, titre, statut, date, priorite, projetId, membre.getId());
                if (TaskService.ajouterTache(tache)) {
                    JOptionPane.showMessageDialog(this, "Tâche ajoutée !");
                    refreshListe();
                    refreshGantt();
                }
            }
        });

        panel.add(new JLabel("Titre :"));
        panel.add(titreField);
        panel.add(new JLabel("Date échéance :"));
        panel.add(dateField);
        panel.add(new JLabel("Priorité :"));
        panel.add(prioriteBox);
        panel.add(new JLabel("Statut :"));
        panel.add(statutBox);
        panel.add(new JLabel("Membre :"));
        panel.add(comboMembre);
        panel.add(new JLabel());
        panel.add(ajouterButton);

        return panel;
    }

    private JPanel creerPanelListe() {
        listeTachesPanel = new JPanel();
        listeTachesPanel.setLayout(new BoxLayout(listeTachesPanel, BoxLayout.Y_AXIS));
        refreshListe();
        return listeTachesPanel;
    }

    private JPanel creerPanelGantt() {
        ganttPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawGantt(g);
            }
        };
        ganttPanel.setPreferredSize(new Dimension(600, 400));
        return ganttPanel;
    }

    private void refreshListe() {
        listeTachesPanel.removeAll();
        for (Task t : TaskService.getTachesParProjet(projetId)) {
            listeTachesPanel.add(new JLabel(t.getTitre() + " - " + t.getStatut() + " - Échéance : " + t.getDateEcheance()));
        }
        listeTachesPanel.revalidate();
        listeTachesPanel.repaint();
    }

    private void refreshGantt() {
        ganttPanel.repaint();
    }

    private void drawGantt(Graphics g) {
        List<Task> taches = TaskService.getTachesParProjet(projetId);
        int y = 30;

        for (Task t : taches) {
            g.drawString(t.getTitre(), 10, y);
            g.setColor(Color.BLUE);

            int facteur = switch (t.getPriorite()) {
                case "basse" -> 1;
                case "moyenne" -> 2;
                case "haute" -> 3;
                default -> 1;
            };

            int largeur = facteur * 50;
            g.fillRect(100, y - 15, largeur, 20);
            y += 30;
        }
    }
}
