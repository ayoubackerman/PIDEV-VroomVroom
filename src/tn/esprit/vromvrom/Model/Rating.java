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
public class Rating {
    private int id;
    private User id_user;
    private int etoiles;
    private String commentaire;

    public int getId() {
        return id;
    }

    public User getId_user() {
        return id_user;
    }

    public int getEtoiles() {
        return etoiles;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_user(User id_user) {
        this.id_user = id_user;
    }

    public void setEtoiles(int etoiles) {
        this.etoiles = etoiles;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Rating(User id_user, int etoiles, String commentaire) {
        this.id_user = id_user;
        this.etoiles = etoiles;
        this.commentaire = commentaire;
    }

    @Override
    public String toString() {
        return "Rating{" + "id=" + id + ", id_user=" + id_user + ", etoiles=" + etoiles + ", commentaire=" + commentaire + '}';
    }
    
    
}
