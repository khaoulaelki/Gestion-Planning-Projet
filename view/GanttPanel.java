package view;

import model.Task;
import model.TaskService;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class GanttPanel extends JPanel {

    private List<Task> taches;

    public GanttPanel(int projetId) {
        this.taches = TaskService.getTachesParProjet(projetId);
        setPreferredSize(new Dimension(800, 400));
        setBorder(BorderFactory.createTitledBorder("Diagramme de Gantt"));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (taches == null || taches.isEmpty()) return;

        Graphics2D g2 = (Graphics2D) g;
        int y = 30;

        for (Task t : taches) {
            g2.setColor(Color.BLACK);
            g2.drawString(t.getTitre(), 10, y + 15);

            int facteur = switch (t.getPriorite()) {
                case "basse" -> 1;
                case "moyenne" -> 2;
                case "haute" -> 3;
                default -> 1;
            };

            int largeur = facteur * 80;
            g2.setColor(new Color(100, 150, 255));
            g2.fillRect(150, y, largeur, 20);

            y += 35;
        }
    }
}