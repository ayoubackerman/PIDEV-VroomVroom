package tn.esprit.vromvrom.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tn.esprit.vromvrom.Model.Trajet;
import tn.esprit.vromvrom.Model.Urgence;
import tn.esprit.vromvrom.Model.Voiture_urgence;
import tn.esprit.vromvrom.Database.Database;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;




public class ServiceUrgence  implements IService<Urgence>{

    private Connection cnx;
    //private Object sp;

    public ServiceUrgence(){
        cnx = Database.getInstance().getCnx();
    }

    @Override
    public void createOne(Urgence urgence) throws SQLException {
        String req = "INSERT INTO urgence (id_trajet, id_voiture, localisation, description, statuts) " +
                " VALUES (?, ?, ?, ?, ?)";
        //System.out.println(req);
        PreparedStatement st = cnx.prepareStatement(req);
        st.setInt(1, urgence.getTrajet().getId_trajet());
        st.setInt(2, urgence.getVoiture().getId_voiture());
        st.setString(3, urgence.getLocalisation());
        st.setString(4, urgence.getDescription());
        st.setString(5, urgence.getStatuts());


        st.executeUpdate();
        System.out.println("Urgence ajouté !");
    }

    @Override
    public void updateOne(Urgence urgence) throws SQLException {
        String req = "UPDATE urgence SET id_trajet = ?, id_voiture = ?, localisation = ?, description = ?, statuts = ? WHERE temps = ?";

        System.out.println(req);

        PreparedStatement st = cnx.prepareStatement(req);
        st.setInt(1,urgence.getTrajet().getId_trajet());
        st.setInt(2,urgence.getVoiture().getId_voiture());
        st.setString(3,urgence.getLocalisation());
        st.setString(4,urgence.getDescription());
        st.setString(5,urgence.getStatuts());
        st.setString(6,urgence.getTemps());


        int rowsUpdated = st.executeUpdate();
        System.out.println(rowsUpdated + " lignes ont été mises à jour.");
    }

    @Override
    public void deletOne(Urgence urgence) throws SQLException {
        String req = "DELETE FROM urgence WHERE temps = ?";

        System.out.println(req);
        PreparedStatement st = cnx.prepareStatement(req);
        st.setString(1,urgence.getTemps());
        int rowsDeleted = st.executeUpdate();
        System.out.println(rowsDeleted + " lignes ont été supprimées.");


    }

    @Override
    public ObservableList<Urgence> selectAll() throws SQLException {
        ObservableList<Urgence> temp = FXCollections.observableArrayList();

        String req = "SELECT *" +
                "FROM urgence" +
                " JOIN trajet ON urgence.id_trajet = trajet.id_trajet" +
                " JOIN voiture_urgence ON urgence.id_voiture = voiture_urgence.id_voiture";

        PreparedStatement ps = cnx.prepareStatement(req);

        ResultSet rs = ps.executeQuery();

        while (rs.next()){

            Urgence p = new Urgence();

            p.setId_urgence(rs.getInt(1));
            Trajet t = new Trajet(rs.getString(8),rs.getString(9),rs.getFloat(10),rs.getInt(11),rs.getInt(12),rs.getInt(13),rs.getInt(14),rs.getString(15));
            System.out.println(t);
            p.setTrajet(t);
            Voiture_urgence v = new Voiture_urgence(rs.getInt(16),rs.getInt(20),rs.getInt(21),rs.getString(17),rs.getString(18),rs.getString(19),rs.getString(22));
            //Voiture_urgence v = new Voiture_urgence(21,4,2,"hhh","jjjj","hhhh","pppp");
            System.out.println(v);
            p.setVoiture(v);
            p.setLocalisation(rs.getString(4));
            p.setDescription(rs.getString(5));
            p.setStatuts(rs.getString(6));
            p.setTemps(rs.getString(7));

            temp.add(p);

        }


        //System.out.println(temp);
        return temp;
    }
}
