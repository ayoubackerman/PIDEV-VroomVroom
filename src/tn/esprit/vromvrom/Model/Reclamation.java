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
public class Reclamation {
    
    private int id_reclamation;
    private User id_user;
    private String nom,prenom,reclamation ,type_reclamation ,resolution,time ,reponse ;

    public Reclamation(int id_reclamation, User id_user, String nom, String prenom, String reclamation, String type_reclamation, String resolution, String time, String reponse) {
        this.id_reclamation = id_reclamation;
        this.id_user = id_user;
        this.nom = nom;
        this.prenom = prenom;
        this.reclamation = reclamation;
        this.type_reclamation = type_reclamation;
        this.resolution = resolution;
        this.time = time;
        this.reponse = reponse;
    }

    public Reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public Reclamation(int id_reclamation, User id_user, String reclamation, String type_reclamation, String resolution, String time) {
        this.id_reclamation = id_reclamation;
        this.id_user = id_user;
        this.reclamation = reclamation;
        this.type_reclamation = type_reclamation;
        this.resolution = resolution;
        this.time = time;
    }

    
    public Reclamation(User id_user, String nom, String prenom, String reclamation, String type_reclamation, String resolution, String time, String reponse) {
        this.id_user = id_user;
        this.nom = nom;
        this.prenom = prenom;
        this.reclamation = reclamation;
        this.type_reclamation = type_reclamation;
        this.resolution = resolution;
        this.time = time;
        this.reponse = reponse;
    }

    public Reclamation(String nom, String prenom, String reclamation, String type_reclamation, String resolution, String time, String reponse) {
        this.nom = nom;
        this.prenom = prenom;
        this.reclamation = reclamation;
        this.type_reclamation = type_reclamation;
        this.resolution = resolution;
        this.time = time;
        this.reponse = reponse;
    }

    public Reclamation(User id_user, String reclamation, String type_reclamation) {
        this.id_user = id_user;
        this.reclamation = reclamation;
        this.type_reclamation = type_reclamation;
    }

   

  

    

   

    public int getId_reclamation() {
        return id_reclamation;
    }

    public User getId_user() {
        return id_user;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getReclamation() {
        return reclamation;
    }

    public String getType_reclamation() {
        return type_reclamation;
    }

    public String getResolution() {
        return resolution;
    }
    
    public String getTime() {
        return time;
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public void setId_user(User id_user) {
        this.id_user = id_user;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setReclamation(String reclamation) {
        this.reclamation = reclamation;
    }

    public void setType_reclamation(String type_reclamation) {
        this.type_reclamation = type_reclamation;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

  

    public Reclamation() {
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id_reclamation=" + id_reclamation + ", id_user=" + id_user + ", nom=" + nom + ", prenom=" + prenom + ", reclamation=" + reclamation + ", type_reclamation=" + type_reclamation + ", resolution=" + resolution + ", time=" + time + '}';
    }

    public void setString(int i, String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getid_reclamation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

   
    
}
