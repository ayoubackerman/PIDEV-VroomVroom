package tn.esprit.vromvrom.Model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Messages {
        private int id;
        private Conversation conversation;
        private String expediteur;
        private String destinataire;
        private String texte;
        private LocalDateTime heureEnvoi;

    public Messages() {
    }


    public Messages(int id, Conversation conversation, String expediteur, String destinataire, String texte) {
        this.id = id;
        this.conversation = conversation;
        this.expediteur = expediteur;
        this.destinataire = destinataire;
        this.texte = texte;
    }

    public Messages(Conversation conversation, String expediteur, String destinataire, String texte) {
        this.conversation = conversation;
        this.expediteur = expediteur;
        this.destinataire = destinataire;
        this.texte = texte;
    }

    public Messages(int id, Conversation conversation, String expediteur, String destinataire, String texte, LocalDateTime heureEnvoi) {
            this.id = id;
            this.conversation = conversation;
            this.expediteur = expediteur;
            this.destinataire = destinataire;
            this.texte = texte;
            this.heureEnvoi = heureEnvoi;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Conversation getConversation() {
            return conversation;
        }

        public void setConversation(Conversation conversation) {
            this.conversation = conversation;
        }

        public String getExpediteur() {
            return expediteur;
        }

        public void setExpediteur(String expediteur) {
            this.expediteur = expediteur;
        }

        public String getDestinataire() {
            return destinataire;
        }

        public void setDestinataire(String destinataire) {
            this.destinataire = destinataire;
        }

        public String getTexte() {
            return texte;
        }

        public void setTexte(String texte) {
            this.texte = texte;
        }

        public LocalDateTime getHeureEnvoi() {
            return heureEnvoi;
        }

        public void setHeureEnvoi(LocalDateTime heureEnvoi) {
            this.heureEnvoi = heureEnvoi;
        }


    @Override
    public String toString() {
        return "Messages{" +
                "id=" + id +
                ", conversation=" + conversation +
                ", expediteur='" + expediteur + '\'' +
                ", destinataire='" + destinataire + '\'' +
                ", texte='" + texte + '\'' +
                ", heureEnvoi=" + heureEnvoi +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Messages messages = (Messages) o;
        return id == messages.id && conversation == messages.conversation && Objects.equals(expediteur, messages.expediteur) && Objects.equals(destinataire, messages.destinataire) && Objects.equals(texte, messages.texte) && Objects.equals(heureEnvoi, messages.heureEnvoi);
    }

}
