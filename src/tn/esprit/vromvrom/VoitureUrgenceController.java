package tn.esprit.vromvrom;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import tn.esprit.vromvrom.Model.Urgence;
import tn.esprit.vromvrom.Model.Voiture_urgence;
import tn.esprit.vromvrom.Database.Database;

import javax.swing.*;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class VoitureUrgenceController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TableView<Voiture_urgence> m ;

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
    private TextField modele;
    @FXML
    private TextField marque;
    @FXML
    private TextField matricule;
    @FXML
    private TextField nb_place;
    @FXML
    private TextField statuts;
    @FXML
    private TextField image;
    @FXML
    private Button add;





    @FXML
    void Modifier(ActionEvent event) {
        String t= modele.getText();
        String ivd = marque.getText();
        String l= matricule.getText();
        String d = nb_place.getText();
        String s = statuts.getText();
        String da = image.getText();



        String sql = "UPDATE voiture_urgence SET modele = '"+ t +"', marque = '"+ ivd +"', nombre_place = '"+ d +"', statuts = '"+ s +"', image = '"+ da +"' WHERE matricule = '"+ l +"'" ;
        java.sql.Connection cnx;
        cnx = Database.getInstance().getCnx();
        try {
            PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);
            st.executeUpdate();
            table();
            JOptionPane.showMessageDialog(null,"Le fichier a été modifier");
        }catch(SQLException ex){
            ex.getMessage();
        }


    }






    @FXML
    private void Supprimer(ActionEvent event) {


        String idf=matricule.getText();
        //int i=Integer.valueOf(idf);
        SuppRole(idf);
        table();


        JOptionPane.showMessageDialog(null,"Le fichier a été supprimer avec succés");


    }

    public void SuppRole(String id){


        String sql ="DELETE FROM voiture_urgence WHERE matricule='"+ id +"'";
        try {
            java.sql.Connection cnx;
            cnx = Database.getInstance().getCnx();
            PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);
            st.executeUpdate();
            table();
            JOptionPane.showMessageDialog(null,"Le fichier a été supprimer");
        }catch(SQLException ex){
            ex.getMessage();
        }



    }







    public void onEdit() {

        java.sql.Connection cnx;
        cnx = Database.getInstance().getCnx();

        if (m.getSelectionModel().getSelectedItem() != null) {
            Voiture_urgence v = m.getSelectionModel().getSelectedItem();
            int i = v.getNombre_place();
            String n = String.valueOf(i);
            int j = v.getStatuts();
            String m = String.valueOf(j);


            nb_place.setText(n);
            statuts.setText(m);
            modele.setText(v.getModele());
            marque.setText(v.getMarque());
            matricule.setText(v.getMatricule());
            image.setText(v.getImage());
            matricule.setDisable(true);






        }
    }










    private void EnregistrerVersBase3() {


        java.sql.Connection cnx;
        cnx = Database.getInstance().getCnx();


        try {
            String sql = "INSERT INTO voiture_urgence (modele, marque, matricule, nombre_place, statuts, image) VALUES (?,?,?,?,?,?)";

            PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);



            st.setString(1, modele.getText());
            st.setString(1, modele.getText());
            st.setString(2, marque.getText());
            st.setString(3, matricule.getText());
            st.setString(4, nb_place.getText());
            st.setString(5, statuts.getText());
            st.setString(6, image.getText());


            st.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error");
        }
    }

    @FXML
    private void AddVoitureUrgence(ActionEvent event){

        EnregistrerVersBase3();
        table();

        modele.clear();
        marque.clear();
        matricule.clear();
        nb_place.clear();
        statuts.clear();
        image.clear();






    }


















    public void table(){


        a.setCellValueFactory(new PropertyValueFactory<>("modele"));
        b.setCellValueFactory(new PropertyValueFactory <>("marque"));
        c.setCellValueFactory(new PropertyValueFactory <>("matricule"));
        d.setCellValueFactory(new PropertyValueFactory <>("nombre_place"));
        e.setCellValueFactory(new PropertyValueFactory <>("statuts"));
        f.setCellValueFactory(new PropertyValueFactory <>("image"));


        m.setItems(RecupBase());



    }
    public static ObservableList<Voiture_urgence> RecupBase(){

        ObservableList<Voiture_urgence> list = FXCollections.observableArrayList();

        java.sql.Connection cnx;
        cnx = Database.getInstance().getCnx();
        String sql = "SELECT * FROM `voiture_urgence`";
        try {

            PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);

            ResultSet R = st.executeQuery();
            while (R.next()){
                Voiture_urgence r =new Voiture_urgence();
                r.setModele(R.getString(2));
                r.setMarque(R.getString(3));
                r.setMatricule(R.getString(4));
                r.setNombre_place(R.getInt(5));
                r.setStatuts(R.getInt(6));
                r.setImage(R.getString(7));
                System.out.println(r);
                //System.out.println(r.getId_Trajet());





                list.add(r);
            }
        }catch (SQLException ex){
            ex.getMessage();
        }
        return list;
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        m.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() > 0) {
                onEdit();

            }
        });

        anchorPane.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() > 0) {
                matricule.setDisable(false);

            }
        });



        table();
    }
}
