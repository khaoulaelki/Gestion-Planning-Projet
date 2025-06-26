package model;

import java.util.ArrayList;
import java.util.List;

public class PlanningService {

    public static List<Task> getPlanning(String username) {
        List<Task> planning = new ArrayList<>();
        username = username.toLowerCase();

        if (username.equals("membre")) {
            planning.add(new Task(1, "Réunion d'équipe", "Prévu", "2025-06-02", 2));
            planning.add(new Task(2, "Démo client", "Prévu", "2025-06-04", 3));
        } else if (username.equals("mohamed")) {
            planning.add(new Task(3, "Refactorisation code", "Prévu", "2025-06-03", 2));
            planning.add(new Task(4, "Présentation sprint", "Prévu", "2025-06-05", 3));
        }

        return planning;
    }
}