/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.vromvrom.Model;

/**
 *
 * @author MediaCenter Zaghouan
 */
public class Voiture {
    private int id;
    private User id_user;
    private String modele;
    private String marque;
    private int matricule;
    private String img;

    public int getId() {
        return id;
    }

    public User getId_user() {
        return id_user;
    }

    public String getModele() {
        return modele;
    }

    public String getMarque() {
        return marque;
    }

    public int getMatricule() {
        return matricule;
    }

    public String getImg() {
        return img;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_user(User id_user) {
        this.id_user = id_user;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public void setMatricule(int matricule) {
        this.matricule = matricule;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Voiture{" + "id=" + id + ", id_user=" + id_user + ", modele=" + modele + ", marque=" + marque + ", matricule=" + matricule + ", img=" + img + '}';
    }

    public Voiture(User id_user, String modele, String marque, int matricule, String img) {
        this.id_user = id_user;
        this.modele = modele;
        this.marque = marque;
        this.matricule = matricule;
        this.img = img;
    }

    public Voiture(User id_user, String modele, String marque, int matricule) {
        this.id_user = id_user;
        this.modele = modele;
        this.marque = marque;
        this.matricule = matricule;
    }
    
    
}
