package tn.esprit.vromvrom.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Conversation {
    public void setHeureDebut(Date heureDebut) {
        this.heureDebut = heureDebut;
    }

    private int id;
    private User utilisateur1;
    //private user utilisateur2;
    private Date heureDebut;
    private Date heureFin;
   // private List<Messages> messages;

    public Conversation() {
    }


    public Conversation(int id, User utilisateur1, Date heureDebut, Date heureFin) {
        this.id = id;
        this.utilisateur1 = utilisateur1;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
    }

    public Conversation(User utilisateur1) {
        this.utilisateur1 = utilisateur1;
        //this.utilisateur2 = utilisateur2;
    }

    public Conversation(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public User getUtilisateur1() {
        return utilisateur1;
    }

    //public user getUtilisateur2() {
      //  return utilisateur2;
    //}

    public Date getHeureDebut() {
        return heureDebut;
    }

    public Date getHeureFin() {
        return heureFin;
    }

    /*public List<Messages> getMessages() {
        return messages;
    }*/

    public void setId(int id) {
        this.id = id;
    }

    public void setUtilisateur1(User utilisateur1) {
        this.utilisateur1 = utilisateur1;
    }

    //public void setUtilisateur2(user utilisateur2) {
      //  this.utilisateur2 = utilisateur2;
    //}

    public void setHeureFin(Date heureFin) {
        this.heureFin = heureFin;
    }

    /*public void addMessage(Messages message) {
        messages.add(message);
    }*/

    @Override
    public String toString() {
        return "Conversation{" +
                "id=" + id +
                ", utilisateur1=" + utilisateur1 +
               // ", utilisateur2=" + utilisateur2 +
                ", heureDebut=" + heureDebut +
                ", heureFin=" + heureFin +
                //", messages=" + messages +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conversation that = (Conversation) o;
        return id == that.id && Objects.equals(utilisateur1, that.utilisateur1)
                //&& Objects.equals(utilisateur2, that.utilisateur2)
                && Objects.equals(heureDebut, that.heureDebut) && Objects.equals(heureFin, that.heureFin);
        //&& Objects.equals(messages, that.messages);
    }

}

