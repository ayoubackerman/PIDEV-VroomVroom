/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.vromvrom;


import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import tn.esprit.vromvrom.Model.Reclamation;
import tn.esprit.vromvrom.Model.User;
import tn.esprit.vromvrom.service.ServiceReclamation;
import tn.esprit.vromvrom.service.ServiceUser;



/**
 *
 * @author ASUS
 */
public class DetailleReclamationController implements Initializable{
    
     private boolean update;
    

    @FXML
    private Label label_nom;

    @FXML
    private Label label_localisation;

    @FXML
    private Label label_num_tel;

    @FXML
    private Label label_client;

    @FXML
    private Label label_email;

    
    
    ServiceReclamation service_reclamation =new ServiceReclamation();  
     ServiceUser service_user =new ServiceUser(); 

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   @FXML
private void exporter_pdf(ActionEvent event) {
    // Code to export the data to a PDF file
}

public void set_data(int id_reclamation) throws SQLException {
    User user = service_user.SelectUser(id_reclamation);
    System.out.println("user data " + user);
    label_client.setText(user.getNom() + " " + user.getPrenom()); 
    label_client.setText(user.getMail());
    
    Reclamation reclamation = service_reclamation.SelectReclamation(id_reclamation);
    label_nom.setText(reclamation.getType_reclamation()); 
    label_localisation.setText(reclamation.getReclamation());
    label_num_tel.setText("(+216) " + reclamation.getTime());
}
void setUpdate(boolean b) {
        this.update = b;

    }

void setTextField(String id, String name) {

        label_nom.setText(id);
        label_email.setText(name);

    }
}

