package tn.esprit.vromvrom;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import tn.esprit.vromvrom.Model.Trajet;
import tn.esprit.vromvrom.Model.Urgence;
import tn.esprit.vromvrom.Model.Voiture_urgence;
import tn.esprit.vromvrom.service.ServiceUrgence;
import tn.esprit.vromvrom.Database.Database;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;


import javax.swing.*;

public class UrgenceController implements Initializable{

    @FXML
    private TableView<Urgence> m ;

    @FXML
    private TableColumn<Urgence, Integer> a;

    @FXML
    private TableColumn<Urgence, Integer> b;

    @FXML
    private TableColumn<Urgence, String> c;

    @FXML
    private TableColumn<Urgence, String> d ;

    @FXML
    private TableColumn<Urgence, String> e;

    @FXML
    private TableColumn<Urgence, String> f;




    @FXML
    private TextField id_trajet;
    @FXML
    private TextField id_voiture;
    @FXML
    private TextField localisation;
    @FXML
    private TextField description;
    @FXML
    private TextField status;
    @FXML
    private TextField date;
    @FXML
    private Button Add;

    ServiceUrgence SU = new ServiceUrgence();
    @FXML
    private Button Del;
    @FXML
    private Button Update;




    @FXML
    void Modifier(ActionEvent event) throws SQLException {
        int t= Integer.parseInt(id_trajet.getText());
        int ivd = Integer.parseInt(id_voiture.getText());
        String l= localisation.getText();
        String d = description.getText();
        String s = status.getText();
        String da = date.getText();

        Urgence ur =new Urgence(new Trajet(t),new Voiture_urgence(ivd),l,d,s,da);

        SU.updateOne(ur);
        table();
/*
        String sql = "UPDATE urgence SET id_trajet = '"+ t +"', id_voiture = '"+ ivd +"', localisation = '"+ l +"', description = '"+ d +"', statuts = '"+ s +"' WHERE temps = '"+ da +"'" ;
        java.sql.Connection cnx;
        cnx = MaConnexion.getInstance().getCnx();
        try {
            PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);
            st.executeUpdate();
            table();
            JOptionPane.showMessageDialog(null,"Le fichier a été modifier");
        }catch(SQLException ex){
            ex.getMessage();
        }

 */




    }






    @FXML
    private void Supprimer(ActionEvent event) throws SQLException {


        String idf=date.getText();
        //int i=Integer.valueOf(idf);
        //SuppRole(idf);
        Urgence ur = new Urgence(idf);
        SU.deletOne(ur);
        table();


       // JOptionPane.showMessageDialog(null,"Le fichier a été supprimer avec succés");


    }
/*
    public void SuppRole(String id){


        String sql ="DELETE FROM urgence WHERE temps='"+ id +"'";
        try {
            java.sql.Connection cnx;
            cnx = MaConnexion.getInstance().getCnx();
            PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);
            st.executeUpdate();
            table();
            JOptionPane.showMessageDialog(null,"Le fichier a été supprimer");
        }catch(SQLException ex){
            ex.getMessage();
        }


    }

 */









    public void onEdit() {

        java.sql.Connection cnx;
        cnx = Database.getInstance().getCnx();

        if (m.getSelectionModel().getSelectedItem() != null) {
            Urgence urgence = m.getSelectionModel().getSelectedItem();
            int i = urgence.getTrajet().getId_trajet();
            String n = String.valueOf(i);
            int j = urgence.getVoiture().getId_voiture();
            String m = String.valueOf(j);


            id_trajet.setText(n);
            id_voiture.setText(m);
            localisation.setText(urgence.getLocalisation());
            description.setText(urgence.getDescription());
            status.setText(urgence.getStatuts());
            date.setText(urgence.getTemps());



        }
    }







/*
    private void EnregistrerVersBase2() {


        java.sql.Connection cnx;
        cnx = MaConnexion.getInstance().getCnx();


        try {
            String sql = "INSERT INTO urgence (id_trajet, id_voiture, localisation, description, statuts) VALUES (?,?,?,?,?)";

            PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);



            st.setString(1, id_trajet.getText());
            st.setString(2, id_voiture.getText());
            st.setString(3, localisation.getText());
            st.setString(4, description.getText());
            st.setString(5, status.getText());



            st.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error");
        }
    }

 */

    @FXML
    private void AddUrgence(ActionEvent event) throws SQLException {

        Voiture_urgence v = new Voiture_urgence(Integer.parseInt(id_voiture.getText()));
        Trajet t = new Trajet(Integer.parseInt(id_trajet.getText()));

        Urgence ur = new Urgence(t,v,localisation.getText(),description.getText(),status.getText());

        SU.createOne(ur);
        table();

        id_trajet.clear();
        id_voiture.clear();
        description.clear();
        localisation.clear();
        status.clear();






    }

















    public void table() throws SQLException {


       // a.setCellValueFactory(new PropertyValueFactory <>("Trajet"));


        a.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getTrajet().getId_trajet()).asObject());

        b.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getVoiture().getId_voiture()).asObject());
        c.setCellValueFactory(new PropertyValueFactory <>("localisation"));
        d.setCellValueFactory(new PropertyValueFactory <>("description"));
        e.setCellValueFactory(new PropertyValueFactory <>("statuts"));
        f.setCellValueFactory(new PropertyValueFactory <>("temps"));


        m.setItems(SU.selectAll());
        //m.setItems(RecupBase());



    }

/*
    public static ObservableList<Urgence> RecupBase(){

        ObservableList<Urgence> list = FXCollections.observableArrayList();

        java.sql.Connection cnx;
        cnx = MaConnexion.getInstance().getCnx();
        String sql = "SELECT urgence.*, trajet.*\n" +
                "FROM urgence\n" +
                "JOIN trajet ON urgence.id_trajet = trajet.id_trajet;";
        try {

            PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);

            ResultSet R = st.executeQuery();



            while (R.next()){
                Urgence r =new Urgence();
               // r.setId_trajet(R.getInt(2));
                //r.setId_voiture(R.getInt(3));
                r.setLocalisation(R.getString(4));
                r.setDescription(R.getString(5));
                r.setStatuts(R.getString(6));
                r.setTemps(R.getString(7));
                //System.out.println(r);
                //System.out.println(r.getId_Trajet());

                System.out.println(R.getString(15));




                list.add(r);
            }
        }catch (SQLException ex){
            ex.getMessage();
        }
        return list;
    }

 */

    @Override
    public void initialize(URL location, ResourceBundle resources) {



        date.setVisible(false);
        m.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() > 0) {
                onEdit();

            }
        });


        try {
            table();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }







}

