package tn.esprit.vromvrom.service;

import tn.esprit.vromvrom.Model.Urgence;
import tn.esprit.vromvrom.Model.Voiture_urgence;
import tn.esprit.vromvrom.Database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceVoiture_urgence implements IService<Voiture_urgence>{

    private Connection cnx;

    public ServiceVoiture_urgence() {
        cnx = Database.getInstance().getCnx();
    }

    @Override
    public void createOne(Voiture_urgence voiture_urgence) throws SQLException {
        String req = "INSERT INTO voiture_urgence (modele, marque, matricule, nombre_place, statuts, image) " +
                " VALUES (?, ?, ?, ?, ?, ?)";
        System.out.println(req);
        PreparedStatement st = cnx.prepareStatement(req);
        st.setString(1, voiture_urgence.getModele());
        st.setString(2, voiture_urgence.getMarque());
        st.setString(3, voiture_urgence.getMatricule());
        st.setInt(4, voiture_urgence.getNombre_place());
        st.setInt(5, voiture_urgence.getStatuts());
        st.setString(6, voiture_urgence.getImage());


        st.executeUpdate();
        System.out.println("voiture ajouté !");
    }

    @Override
    public void updateOne(Voiture_urgence voiture_urgence) throws SQLException {
        String req = "UPDATE voiture_urgence SET modele = ?, marque = ?, nombre_place = ?, statuts = ?, image = ? WHERE matricule = ?";

        System.out.println(req);

        PreparedStatement st = cnx.prepareStatement(req);
        st.setString(1, voiture_urgence.getModele());
        st.setString(2, voiture_urgence.getMarque());
        st.setInt(3, voiture_urgence.getNombre_place());
        st.setInt(4, voiture_urgence.getStatuts());
        st.setString(5, voiture_urgence.getImage());
        st.setString(6,voiture_urgence.getMatricule());



        int rowsUpdated = st.executeUpdate();
        System.out.println(rowsUpdated + " lignes ont été mises à jour.");
    }

    @Override
    public void deletOne(Voiture_urgence voiture_urgence) throws SQLException {
        String req = "DELETE FROM voiture_urgence WHERE id_voiture = ?";

        System.out.println(req);
        PreparedStatement st = cnx.prepareStatement(req);
        st.setInt(1,voiture_urgence.getId_voiture());
        int rowsDeleted = st.executeUpdate();
        System.out.println(rowsDeleted + " lignes ont été supprimées.");
    }

    @Override
    public List<Voiture_urgence> selectAll() throws SQLException {
        List<Voiture_urgence> temp = new ArrayList<>();

        String req = "SELECT * FROM `voiture_urgence`";

        PreparedStatement ps = cnx.prepareStatement(req);

        ResultSet rs = ps.executeQuery();

        while (rs.next()){

            Voiture_urgence v1 = new Voiture_urgence();

            v1.setId_voiture(rs.getInt(1));
            v1.setModele(rs.getString(2));
            v1.setMarque(rs.getString(3));
            v1.setMatricule(rs.getString(4));
            v1.setNombre_place(rs.getInt(5));
            v1.setStatuts(rs.getInt(6));
            v1.setImage(rs.getString(7));

            temp.add(v1);

        }


        return temp;
    }
}
