package view;

import model.Task;
import model.TaskService;
import model.User;
import model.UserService;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.util.List;

public class FormulaireAjoutTache extends JFrame {

    public FormulaireAjoutTache(int idProjet) {
        setTitle("Ajouter une tâche");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 10, 10));

        JTextField txtTitre = new JTextField();
        JComboBox<String> comboStatut = new JComboBox<>(new String[]{"à faire", "en cours", "terminé"});
        JTextField txtDate = new JTextField("2025-06-15");
        JComboBox<String> comboPriorite = new JComboBox<>(new String[]{"basse", "moyenne", "haute"});

        JComboBox<String> comboMembres = new JComboBox<>();
        List<User> membres = UserService.getTousLesMembresSansChefs();
        for (User u : membres) {
            comboMembres.addItem(u.getUsername());
        }

        JButton btnAjouter = new JButton("Ajouter");

        btnAjouter.addActionListener(e -> {
            try {
                String titre = txtTitre.getText().trim();
                String statut = (String) comboStatut.getSelectedItem();
                Date date = Date.valueOf(txtDate.getText().trim());
                String priorite = (String) comboPriorite.getSelectedItem();
                String nomMembre = (String) comboMembres.getSelectedItem();

                if (titre.isEmpty() || nomMembre == null) {
                    JOptionPane.showMessageDialog(this, "❗ Veuillez remplir tous les champs.");
                    return;
                }

                User membre = UserService.findByNom(nomMembre);
                if (membre == null || !"membre".equalsIgnoreCase(membre.getRole())) {
                    JOptionPane.showMessageDialog(this, "❌ Seuls les membres peuvent être assignés à une tâche.");
                    return;
                }

                Task t = new Task(0, titre, statut, date.toString(), priorite, idProjet, membre.getId());
                boolean ok = TaskService.ajouterTache(t);

                if (ok) {
                    JOptionPane.showMessageDialog(this, "✅ Tâche ajoutée avec succès !");
                    txtTitre.setText("");
                    txtDate.setText("2025-06-15");
                    comboPriorite.setSelectedIndex(0);
                    comboStatut.setSelectedIndex(0);
                    comboMembres.setSelectedIndex(0);
                } else {
                    JOptionPane.showMessageDialog(this, "❌ Erreur lors de l'ajout de la tâche.");
                }

            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, "❌ Format de date incorrect (yyyy-mm-dd).");
            }
        });

        add(new JLabel("Titre de la tâche :")); add(txtTitre);
        add(new JLabel("Statut :")); add(comboStatut);
        add(new JLabel("Date d'échéance :")); add(txtDate);
        add(new JLabel("Priorité :")); add(comboPriorite);
        add(new JLabel("Affecter à :")); add(comboMembres);
        add(new JLabel()); add(btnAjouter);

        setVisible(true);
    }
}
