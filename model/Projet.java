package model;

import java.sql.Date;
public class Projet {
    private int id;
    private String nom;
    private String description;
    private Date dateDebut;
    private Date dateFin;
    private String statut;

    public Projet(int id, String nom, String description, Date dateDebut, Date dateFin, String statut) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.statut = statut;
    }

    // Getters & Setters
    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getDescription() { return description; }
    public Date getDateDebut() { return dateDebut; }
    public Date getDateFin() { return dateFin; }
    public String getStatut() { return statut; }

    public void setNom(String nom) { this.nom = nom; }
    public void setDescription(String description) { this.description = description; }
    public void setDateDebut(Date dateDebut) { this.dateDebut = dateDebut; }
    public void setDateFin(Date dateFin) { this.dateFin = dateFin; }
    public void setStatut(String statut) { this.statut = statut; }
    
    
    @Override
    public String toString() {
        return nom; 
    }

}

