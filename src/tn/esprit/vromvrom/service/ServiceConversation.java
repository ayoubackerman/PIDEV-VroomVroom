package tn.esprit.vromvrom.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tn.esprit.vromvrom.Model.*;
import tn.esprit.vromvrom.Database.Database;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

public class ServiceConversation implements IService <Conversation>{


    private Connection cnx;

    public ServiceConversation(){
        cnx = Database.getInstance().getCnx();
    }


    @Override
    public void createOne(Conversation conversation) throws SQLException {
        String req = "INSERT INTO conversation (utilisateur1) " +
                " VALUES (?)";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setInt(1, conversation.getUtilisateur1().getId_user());
        //st.setInt(2, conversation.getUtilisateur2().getId_user());
        st.executeUpdate();
        System.out.println("Conversation ajouté !");
    }

    @Override
    public void updateOne(Conversation conversation) throws SQLException {
        String req = "UPDATE conversation SET heure_fin = ? WHERE id = ?";

        System.out.println(req);

        PreparedStatement st = cnx.prepareStatement(req);
        st.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
        st.setInt(2,conversation.getId());


        int rowsUpdated = st.executeUpdate();
        System.out.println(rowsUpdated + " lignes ont été mises à jour.");
    }

    @Override
    public void deletOne(Conversation conversation) throws SQLException {

    }

    @Override
    public List<Conversation> selectAll() throws SQLException {
        ObservableList<Conversation> temp = FXCollections.observableArrayList();

        String req = "SELECT *" +
                "FROM conversation" +
                " JOIN user  ON Conversation.utilisateur1 = user.id_user" +
                //" JOIN user u2 ON Conversation.utilisateur2 = u2.id_user";
                " JOIN role  ON user.id_role = role.id_role";

        PreparedStatement ps = cnx.prepareStatement(req);

        ResultSet rs = ps.executeQuery();

        while (rs.next()){

            Conversation p = new Conversation();


            p.setId(rs.getInt(1));


            Role role = new Role(rs.getInt(14),rs.getString(15));
            User us1 = new User(role,rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13));
            p.setUtilisateur1(us1);
            //user us2 = new user(rs.getInt(15),rs.getString(16),rs.getString(17),rs.getString(18),rs.getString(19),rs.getString(20),rs.getString(21));
            //p.setUtilisateur2(us2);
            p.setHeureDebut(rs.getDate(4));
            p.setHeureFin(rs.getDate(5));


            temp.add(p);

        }


        return temp;
    }
}
