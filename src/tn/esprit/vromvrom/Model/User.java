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
public class User {
    private int id_user;
    private Role id_role;
    private String nom;
    private String prenom;
    private String mail;
    private String nomd;
    private String mdp;
    private String status;
    private String Image;
    private long num;

  

    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }
    public static User connecte;

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public User(int id_user, Role id_role, String nom, String prenom, String mail, String nomd, String status, long num) {
        this.id_user = id_user;
        this.id_role = id_role;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.nomd = nomd;
        this.status = status;
        this.num = num;
    }
    

    public User(int id_user, Role id_role, String nom, String prenom, String mail, String nomd, String mdp, String status, String Image, long num) {
        this.id_user = id_user;
        this.id_role = id_role;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.nomd = nomd;
        this.mdp = mdp;
        this.status = status;
        this.Image = Image;
        this.num = num;
    }
    
    
    

    public User(Role id_role, String nom, String prenom, String mail, String nomd, String mdp, String Image) {
        this.id_role = id_role;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.nomd = nomd;
        this.mdp = mdp;
        this.Image = Image;
    }

    public User(int id_user, Role id_role, String nom, String prenom, String mail, String nomd, String mdp, String Image) {
        this.id_user = id_user;
        this.id_role = id_role;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.nomd = nomd;
        this.mdp = mdp;
        this.Image = Image;
    }

    public User(int id_user, Role id_role, String nom, String prenom, String mail, String nomd, String mdp, String status, long num) {
        this.id_user = id_user;
        this.id_role = id_role;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.nomd = nomd;
        this.mdp = mdp;
        this.status = status;
        this.num = num;
    }
    

    public User(int id_user) {
        this.id_user = id_user;
    }
    

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    

    public User() {
    }

  


  
    @Override
    public String toString() {
        return "User{" + "id_user=" + id_user + ", id_role=" + id_role + ", nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + ", nomd=" + nomd + ", mdp=" + mdp + '}';
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public User(String nom, String prenom, String mail, String nomd, String status) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.nomd = nomd;
        this.status = status;
    }

    public Role getId_role() {
        return id_role;
    }

    public void setId_role(Role id_role) {
        this.id_role = id_role;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNomd() {
        return nomd;
    }

    public void setNomd(String nomd) {
        this.nomd = nomd;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public static User getConnecte() {
        return connecte;
    }

    public static void setConnecte(User connecte) {
        User.connecte = connecte;
    }
}
