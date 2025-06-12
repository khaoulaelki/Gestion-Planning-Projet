package model;

public class Task {
    private int id;
    private String titre;
    private String statut; // "à faire", "en cours", "terminé"
    private String dateEcheance;
    private String priorite; // "basse", "moyenne", "haute"
    private int idProjet;
    private int idMembre;

    public Task(int id, String titre, String statut, String dateEcheance, String priorite, int idProjet, int idMembre) {
        this.id = id;
        this.titre = titre;
        this.statut = statut;
        this.dateEcheance = dateEcheance;
        this.priorite = priorite;
        this.idProjet = idProjet;
        this.idMembre = idMembre;
    }

    public int getId() { return id; }
    public String getTitre() { return titre; }
    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }
    public String getDateEcheance() { return dateEcheance; }
    public String getPriorite() { return priorite; }
    public int getIdProjet() { return idProjet; }
    public int getIdMembre() { return idMembre; }
}