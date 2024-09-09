package tn.esprit.vromvrom.Model;

import java.util.Objects;

public class Urgence {
    private int id_urgence;
    private Trajet trajet;
    private Voiture_urgence voiture;
    private String localisation;
    private String description;
    private String statuts;
    private String temps;


    public Urgence() {
    }

    public Urgence(String temps) {
        this.temps = temps;
    }

    public Urgence(Trajet trajet, Voiture_urgence voiture, String localisation, String description, String statuts, String temps) {
        this.trajet = trajet;
        this.voiture = voiture;
        this.localisation = localisation;
        this.description = description;
        this.statuts = statuts;
        this.temps = temps;
    }

    public Urgence(Trajet trajet, Voiture_urgence voiture, String localisation, String description, String status) {
        this.trajet = trajet;
        this.voiture = voiture;
        this.localisation = localisation;
        this.description = description;
        this.statuts = status;
    }


    public int getId_urgence() {
        return id_urgence;
    }

    public void setId_urgence(int id_urgence) {
        this.id_urgence = id_urgence;
    }

    public Trajet getTrajet() {
        return trajet;
    }

    public void setTrajet(Trajet trajet) {
        this.trajet = trajet;
    }

    public Voiture_urgence getVoiture() {
        return voiture;
    }

    public void setVoiture(Voiture_urgence voiture) {
        this.voiture = voiture;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatuts() {
        return statuts;
    }

    public void setStatuts(String statuts) {
        this.statuts = statuts;
    }

    public String getTemps() {
        return temps;
    }

    public void setTemps(String temps) {
        this.temps = temps;
    }


    @Override
    public String toString() {
        return "Urgence{" +
                "id_urgence=" + id_urgence +
                ", trajet=" + trajet +
                ", voiture=" + voiture +
                ", localisation='" + localisation + '\'' +
                ", description='" + description + '\'' +
                ", statuts='" + statuts + '\'' +
                ", temps='" + temps + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Urgence urgence = (Urgence) o;
        return id_urgence == urgence.id_urgence && Objects.equals(trajet, urgence.trajet) && Objects.equals(voiture, urgence.voiture) && Objects.equals(localisation, urgence.localisation) && Objects.equals(description, urgence.description) && Objects.equals(statuts, urgence.statuts) && Objects.equals(temps, urgence.temps);
    }


}
