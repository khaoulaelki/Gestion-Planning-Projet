package model;

import java.util.Date;

public class Tache {
    private int id;
    private String titre;
    private String description;
    private Date dateEcheance;
    private String statut;
    private String responsable;
    private int idProjet;

    public Tache(int id, String titre, String description, Date dateEcheance, String statut, String responsable, int idProjet) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.dateEcheance = dateEcheance;
        this.statut = statut;
        this.responsable = responsable;
        this.idProjet = idProjet;
    }

    // Getters uniquement
    public int getId() { return id; }
    public String getTitre() { return titre; }
    public String getDescription() { return description; }
    public Date getDateEcheance() { return dateEcheance; }
    public String getStatut() { return statut; }
    public String getResponsable() { return responsable; }
    public int getIdProjet() { return idProjet; }
}
