package view;

import model.Task;
import model.TaskService;
import model.User;

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
        JLabel welcomeLabel = new JLabel("Bienvenue " + username, SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(welcomeLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10));

        JButton tachesButton = new JButton("Liste des tâches");

        tachesButton.addActionListener(e -> {
            List<Task> taches = TaskService.getTachesForUser(username);
            JPanel panel = new JPanel(new GridLayout(taches.size(), 1, 5, 5));
            String[] etats = {"En attente", "En cours", "Terminée"};

            for (Task t : taches) {
                JPanel ligne = new JPanel(new BorderLayout());
                JLabel label = new JLabel(t.getDescription());
                JComboBox<String> combo = new JComboBox<>(etats);
                combo.setSelectedItem(t.getEtat());

                combo.addActionListener(ev -> {
                    t.setEtat((String) combo.getSelectedItem());
                    TaskService.updateEtat(t.getId(), t.getEtat());
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

        buttonPanel.add(tachesButton);
        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }
}