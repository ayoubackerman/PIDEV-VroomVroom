/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.vromvrom.Model;

import java.util.Objects;

/**
 *
 * @author user
 */
public class Trajet {
            private String Ville_depart;
            private String Ville_darrive;
            private float Prix;
            private int Nbr_place;
            private int Id_user;
            private int id_trajet;
            private int Duree_pose;
            private String  Mode_paiement;

    public Trajet(int id_trajet) {
        this.id_trajet = id_trajet;
    }

    public Trajet(String ville_depart, String ville_darrive, float prix, int nbr_place, int id_user, int duree_pose, String mode_paiement) {
        Ville_depart = ville_depart;
        Ville_darrive = ville_darrive;
        Prix = prix;
        Nbr_place = nbr_place;
        Id_user = id_user;
        Duree_pose = duree_pose;
        Mode_paiement = mode_paiement;
    }

    public Trajet(String ville_depart, String ville_darrive, float prix, int nbr_place, int id_user, int id_t, int duree_pose, String mode_paiement) {
        Ville_depart = ville_depart;
        Ville_darrive = ville_darrive;
        Prix = prix;
        Nbr_place = nbr_place;
        Id_user = id_user;
        id_trajet = id_t;
        Duree_pose = duree_pose;
        Mode_paiement = mode_paiement;
    }

    public String getVille_depart() {
        return Ville_depart;
    }

    public void setVille_depart(String Ville_depart) {
        this.Ville_depart = Ville_depart;
    }

    public String getVille_darrive() {
        return Ville_darrive;
    }

    public void setVille_darrive(String Ville_darrive) {
        this.Ville_darrive = Ville_darrive;
    }

    public float getPrix() {
        return Prix;
    }

    public void setPrix(int Prix) {
        this.Prix = Prix;
    }

    public int getNbr_place() {
        return Nbr_place;
    }

    public void setNbr_place(int Nbr_place) {
        this.Nbr_place = Nbr_place;
    }

    public int getId_user() {
        return Id_user;
    }

    public void setId_user(int Id_user) {
        this.Id_user = Id_user;
    }

    
    public int getId_trajet() {
        return id_trajet;
    }

    public void setId_trajet(int Id_trajet) {
        this.id_trajet = Id_trajet;
    }

    public int getDuree_pose() {
        return Duree_pose;
    }

    public void setDuree_pose(int Duree_pose) {
        this.Duree_pose = Duree_pose;
    }

    public String getMode_paiement() {
        return Mode_paiement;
    }

    public void setMode_paiement(String Mode_paiement) {
        this.Mode_paiement = Mode_paiement;
    }

    @Override
    public String toString() {
        return "trajet{" + "Ville_depart=" + Ville_depart + ", Ville_darrive=" + Ville_darrive + ", Prix=" + Prix + ", Nbr_place=" + Nbr_place + ", Id_user=" + Id_user + ", Id_trajet=" + id_trajet + ", Duree_pose=" + Duree_pose + ", Mode_paiement=" + Mode_paiement + '}';
    }



    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Trajet other = (Trajet) obj;
        if (!Objects.equals(this.Ville_depart, other.Ville_depart)) {
            return false;
        }
        if (!Objects.equals(this.Ville_darrive, other.Ville_darrive)) {
            return false;
        }
        if (Float.floatToIntBits(this.Prix) != Float.floatToIntBits(other.Prix)) {
            return false;
        }
        if (this.Nbr_place != other.Nbr_place) {
            return false;
        }
        if (this.Id_user != other.Id_user) {
            return false;
        }
        if (this.id_trajet != other.id_trajet) {
            return false;
        }
        if (this.Duree_pose != other.Duree_pose) {
            return false;
        }
        if (!Objects.equals(this.Mode_paiement, other.Mode_paiement)) {
            return false;
        }
        return true;
    }

   
    
            
            
  
     

    
   
  
}
