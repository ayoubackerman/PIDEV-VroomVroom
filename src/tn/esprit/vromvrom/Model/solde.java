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
public class solde {
    private int id_user;
    private int id_paiement;
    private double  montant;
    private String  date, Nom,Prenom;
    

    
    

    public solde() {
    }

    public solde(int  id_user, int id_paiement, double montant, String date) {
        this.id_user = id_user;
        this.id_paiement = id_paiement;
        this.montant = montant;
        this.date = date;
       
    }

    public solde(int id_user, int id_paiement, double montant, String date, String Nom, String Prenom) {
        this.id_user = id_user;
        this.id_paiement = id_paiement;
        this.montant = montant;
        this.date = date;
        this.Nom = Nom;
        this.Prenom = Prenom;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }
    

    public solde(int id_user, double montant) {
        this.id_user = id_user;
        this.montant = montant;
    }
    

    public solde(int id_user, double montant, String date) {
        this.id_user = id_user;
        this.montant = montant;
        this.date = date;
    }
    

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_paiement() {
        return id_paiement;
    }

    public void setId_paiement(int id_paiement) {
        this.id_paiement = id_paiement;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "solde{" + "id_user=" + id_user + ", id_paiement=" + id_paiement + ", montant=" + montant + ", date=" + date + '}';
    }

  
    
    
    
}