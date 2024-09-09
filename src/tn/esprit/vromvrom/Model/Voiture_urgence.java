/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.vromvrom.Model;

import java.util.Objects;

/**
 *
 * @author USER
 */
public class Voiture_urgence {
    private int id_voiture,nombre_place,statuts;
    private String modele,marque,matricule,image;

    public Voiture_urgence() {
    }

    public Voiture_urgence(int id_voiture) {
        this.id_voiture = id_voiture;
    }

    public Voiture_urgence(int id_voiture, int nombre_place, int statuts, String modele, String marque, String matricule, String image) {
        this.id_voiture = id_voiture;
        this.nombre_place = nombre_place;
        this.statuts = statuts;
        this.modele = modele;
        this.marque = marque;
        this.matricule = matricule;
        this.image = image;
    }

    public Voiture_urgence(int nombre_place, int statuts, String modele, String marque, String matricule, String image) {
        this.nombre_place = nombre_place;
        this.statuts = statuts;
        this.modele = modele;
        this.marque = marque;
        this.matricule = matricule;
        this.image = image;
    }

    public int getId_voiture() {
        return id_voiture;
    }

    public void setId_voiture(int id_voiture) {
        this.id_voiture = id_voiture;
    }

    public int getNombre_place() {
        return nombre_place;
    }

    public void setNombre_place(int nombre_place) {
        this.nombre_place = nombre_place;
    }

    public int getStatuts() {
        return statuts;
    }

    public void setStatuts(int statuts) {
        this.statuts = statuts;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    @Override
    public String toString() {
        return "voiture_urgence{" +
                "id_voiture=" + id_voiture +
                ", nombre_place=" + nombre_place +
                ", statuts=" + statuts +
                ", modele='" + modele + '\'' +
                ", marque='" + marque + '\'' +
                ", matricule='" + matricule + '\'' +
                ", image='" + image + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voiture_urgence that = (Voiture_urgence) o;
        return id_voiture == that.id_voiture && nombre_place == that.nombre_place && statuts == that.statuts && Objects.equals(modele, that.modele) && Objects.equals(marque, that.marque) && Objects.equals(matricule, that.matricule) && Objects.equals(image, that.image);
    }

}
