package view;

import model.Task;
import model.TaskService;
import model.UserService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListeTachesPanel extends JPanel {

    private JTable table;

    public ListeTachesPanel(int projetId) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Liste des tâches"));

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        chargerTaches(projetId);
    }

    private void chargerTaches(int projetId) {
        List<Task> taches = TaskService.getTachesParProjet(projetId);
        String[] columns = {"ID", "Titre", "Statut", "Date Échéance", "Priorité", "Assigné à"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (Task t : taches) {
            String nomMembre = UserService.findByNomId(t.getIdMembre());
            model.addRow(new Object[]{
                t.getId(),
                t.getTitre(),
                t.getStatut(),
                t.getDateEcheance(),
                t.getPriorite(),
                nomMembre
            });
        }

        table.setModel(model);
    }
}
