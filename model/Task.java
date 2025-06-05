package model;

public class Task {
    private int id;
    private String description;
    private String etat;
    private String dateEcheance;
    private int priorite;

    public Task(int id, String description, String etat, String dateEcheance, int priorite) {
        this.id = id;
        this.description = description;
        this.etat = etat;
        this.dateEcheance = dateEcheance;
        this.priorite = priorite;
    }

    public int getId() { return id; }
    public String getDescription() { return description; }
    public String getEtat() { return etat; }
    public void setEtat(String etat) { this.etat = etat; }
    public String getDateEcheance() { return dateEcheance; }
    public int getPriorite() { return priorite; }
}