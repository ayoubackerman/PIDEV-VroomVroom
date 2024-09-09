/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.vromvrom.Model;

import javafx.scene.image.ImageView;

/**
 *
 * @author MediaCenter Zaghouan
 */
public class verifcode {
    private int id_code;
    private String code;
    private float pourcentage;
    private String  Etat;
    private ImageView  ImgQR;

    public verifcode() {
    }

    public verifcode(int id_code, String code, float pourcentage, String Etat) {
        this.id_code = id_code;
        this.code = code;
        this.pourcentage = pourcentage;
        this.Etat = Etat;
    }

    public verifcode(String code, float pourcentage, String Etat) {
        this.code = code;
        this.pourcentage = pourcentage;
        this.Etat = Etat;
    }

    public int getId_code() {
        return id_code;
    }

    public void setId_code(int id_code) {
        this.id_code = id_code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public float getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(float pourcentage) {
        this.pourcentage = pourcentage;
    }

    public String getEtat() {
        return Etat;
    }

    public void setEtat(String Etat) {
        this.Etat = Etat;
    }

    public ImageView getImgQR() {
        return ImgQR;
    }

    public void setImgQR(ImageView ImgQR) {
        this.ImgQR = ImgQR;
    }

    @Override
    public String toString() {
        return "verifcode{" + "id_code=" + id_code + ", code=" + code + ", pourcentage=" + pourcentage + ", Etat=" + Etat + ", ImgQR=" + ImgQR + '}';
    }



    

}