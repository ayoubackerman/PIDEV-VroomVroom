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
public class Reponse {
    private int id_reponse;
    private Reclamation id_reclamation;
    private User id_user;
    private String reponse , temps ,nom,prenom;

    public Reponse() {
        
    }

    public Reponse(int id_reponse, Reclamation id_reclamation, User id_user, String reponse, String temps, String nom, String prenom) {
        this.id_reponse = id_reponse;
        this.id_reclamation = id_reclamation;
        this.id_user = id_user;
        this.reponse = reponse;
        this.temps = temps;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Reponse(int id_reponse, Reclamation id_reclamation, User id_user, String reponse) {
        this.id_reponse = id_reponse;
        this.id_reclamation = id_reclamation;
        this.id_user = id_user;
        this.reponse = reponse;
    }

    public Reponse(int id_reponse, Reclamation id_reclamation, User id_user, String reponse, String temps) {
        this.id_reponse = id_reponse;
        this.id_reclamation = id_reclamation;
        this.id_user = id_user;
        this.reponse = reponse;
        this.temps = temps;
    }

    public Reponse(int id_reponse, int id_reclamation, String reponse) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Reponse(int aInt, int aInt0, User user, String string, String string0, String string1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }




    public int getId_reponse() {
        return id_reponse;
    }

    public Reclamation getId_reclamation() {
        return id_reclamation;
    }

    public User getId_user() {
        return id_user;
    }

    public String getReponse() {
        return reponse;
    }

   
    public String getTemps() {
        return temps;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setId_reponse(int id_reponse) {
        this.id_reponse = id_reponse;
    }

    public void setId_reclamation(Reclamation id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public void setId_user(User id_user) {
        this.id_user = id_user;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public void setTemps(String temps) {
        this.temps = temps;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        return "Reponse{" + "id_reponse=" + id_reponse + ", id_reclamation=" + id_reclamation + ", id_user=" + id_user + ", reponse=" + reponse + ", temps=" + temps +  '}';
    }

    public void setId_reclamation(User r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setReponse(int recid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
   

  

  
   
 
    
}
