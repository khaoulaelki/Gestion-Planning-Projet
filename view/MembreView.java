package view;

import model.Task;
import model.TaskService;
import model.User;
import model.PlanningService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MembreView extends JFrame {

    public MembreView(User utilisateur) {
        setTitle("Espace Membre");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        String username = utilisateur.getUsername();
        int userId = utilisateur.getId(); 

        JLabel welcomeLabel = new JLabel("Bienvenue " + username, SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(welcomeLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10));

        // === Bouton "Liste des tâches" ===
        JButton tachesButton = new JButton("Liste des tâches");
        tachesButton.addActionListener(e -> {
            List<Task> taches = TaskService.getTachesForUser(username);
            JPanel panel = new JPanel(new GridLayout(taches.size(), 1, 5, 5));
            String[] statuts = {"à faire", "en cours", "terminé"};

            for (Task t : taches) {
                JPanel ligne = new JPanel(new BorderLayout());
                JLabel label = new JLabel(t.getTitre());
                JComboBox<String> combo = new JComboBox<>(statuts);
                combo.setSelectedItem(t.getStatut());

                combo.addActionListener(ev -> {
                    t.setStatut((String) combo.getSelectedItem());
                    TaskService.updateStatut(t.getId(), t.getStatut());
                });

                ligne.add(label, BorderLayout.WEST);
                ligne.add(combo, BorderLayout.EAST);
                panel.add(ligne);
            }

            JScrollPane scrollPane = new JScrollPane(panel);
            scrollPane.setPreferredSize(new Dimension(400, 200));

            JOptionPane.showMessageDialog(null, scrollPane,
                    "Tâches de " + username, JOptionPane.INFORMATION_MESSAGE);
        });

        // === Bouton "Planning" ===
        JButton planningButton = new JButton("Voir le planning");
        planningButton.addActionListener(e -> {
            List<Task> planning = PlanningService.getPlanningByMembreId(userId);
            JPanel panel = new JPanel(new GridLayout(planning.size(), 1, 5, 5));

            for (Task t : planning) {
                String ligne = String.format("• %s | Échéance : %s | Priorité : %s",
                        t.getTitre(), t.getDateEcheance(), t.getPriorite());
                panel.add(new JLabel(ligne));
            }

            JScrollPane scrollPane = new JScrollPane(panel);
            scrollPane.setPreferredSize(new Dimension(400, 200));

            JOptionPane.showMessageDialog(null, scrollPane,
                    "Planning de " + username, JOptionPane.INFORMATION_MESSAGE);
        });

        buttonPanel.add(tachesButton);
        buttonPanel.add(planningButton);
        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }
}
