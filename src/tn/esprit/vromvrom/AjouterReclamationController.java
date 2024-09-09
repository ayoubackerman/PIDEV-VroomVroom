/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.vromvrom;

import java.io.IOException;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import tn.esprit.vromvrom.Database.Database;
import tn.esprit.vromvrom.Model.Reclamation;
import tn.esprit.vromvrom.Model.User;
import tn.esprit.vromvrom.service.ServiceReclamation;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import static utils.BadWords.checkWords;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AjouterReclamationController implements Initializable {
    
    private Boolean update = false;

    ObservableList<String> recList = FXCollections.observableArrayList("Conduite dangereuse",
            "Problèmes avec les autres passagers",
            "Manquement aux termes et conditions",
            "Problèmes avec le véhicule",
            "Problèmes de paiement",
            "Autre");
//    Utilisateur userConn = Utilisateur.user_connecter;
private Connection cnx;
//    @FXML
//    private Rectangle type1;
    @FXML
    private TextArea description;
    @FXML
    private Rectangle type1;
    @FXML
    private TextField time;
public AjouterReclamationController(){
        cnx = Database.getInstance().getCnx();
    }


    @FXML
    private ChoiceBox<String> type;
    @FXML
    private Button envoi;
    
    @FXML
    private TextField user;
    ServiceReclamation sc = new ServiceReclamation();
        /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
  
     @Override
    public void initialize(URL url, ResourceBundle rb) { 
        // TODO
       type.setValue("  "); 
        type.setItems(recList);
        time.setVisible(false);
    }

   
      public Boolean VerifUserChamps() {
        int verif = 0;
        String style = " -fx-border-color: red;";
        if (description.getText().trim().equals("")) {
            description.setStyle(style);
            verif = 1;
        }
      if (type.getSelectionModel().isEmpty()) {
    type.setStyle(style);
    verif = 1;
        if (verif == 0) {
            return true;
        }
        Alert al = new Alert(Alert.AlertType.ERROR);
        al.setTitle("Alert");
        al.setContentText("Verifier les champs");
        al.setHeaderText(null);
        al.show();

        return false;

    }    return null;
}

    
    int attention=0;
   
    @FXML
    public void AddReclamation(ActionEvent event) throws SQLException, IOException{
        if (!update){
    String cont = description.getText();
          if (cont.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        } else {
              if(checkWords(cont).equals("false")){
                  ServiceReclamation sr = new ServiceReclamation();
                  Reclamation p = new Reclamation();
                  User u = new User(Integer.parseInt(user.getText()));
                  p.setReclamation(description.getText());
                  p.setType_reclamation(type.getValue());
                  p.setId_user(u);
                  System.out.println(p);
                  sr.ajouter(p);
                  
                  user.clear();
                  description.clear();
                  
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
                  alert.setTitle("Done");
                  alert.setContentText("Reclamation ajouté avec succes!");
                  alert.show();
                  
                  
              }else
              {
                  attention++;
                 
                  Alert alert = new Alert(Alert.AlertType.WARNING);
                  alert.setTitle("Worning !! ");
                  alert.setContentText("vous ne pouvez pas ajouter cette reclamation avec ces mots ! ");
                  alert.show();
                  
                  if(attention>2)
                  {
                      System.out.println(attention);
                        TrayNotification tray = new TrayNotification();
            tray.setNotificationType(NotificationType.WARNING);
            tray.setTitle("YA ZEBI MATEFHEMCH NAYEK KLEM ZAYED LAAAA");
            tray.setMessage("EHCHEM 3ASBA");
            tray.setAnimationType(AnimationType.SLIDE);
            tray.showAndDismiss(Duration.millis(6500));
                  }
                  
              }
       
    

    }
          
        }
        else{
        String cont = description.getText();
          if (cont.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        } else {
              if(checkWords(cont).equals("false")){
         ServiceReclamation mm = new ServiceReclamation();
                  Reclamation m = new Reclamation();
                  m.setTime(time.getText());
                  m.setReclamation(description.getText());
                  m.setType_reclamation(type.getValue());
                  
                  System.out.println(m);
                  mm.update(m);
                  user.clear();
                  description.clear();
   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                  alert.setTitle("Done");
                  alert.setContentText("\"Votre Reclamation a ete modifiée avec succès!\"");
                  alert.show();
                  
                  
                   Stage stage = (Stage) envoi.getScene().getWindow();
                   

    // Close the window
    stage.close();
                  
              }else
              {
                  attention++;
                  Alert alert = new Alert(Alert.AlertType.WARNING);
                  alert.setTitle("Worning !! ");
                  alert.setContentText("vous ne pouvez pas ajouter ce reclamation avec ces mots ! ");
                  alert.show();
                  
                  if(attention>2)
                  {
                      System.out.println(attention);
//                          Mail.envoyer(user);
                  }
                  
              }
       
    

    }
       
        }
       
    

    }
    
    void setUpdate(boolean b) {
        this.update = b;

    }

void setTextField(String desc, String id, String typee, String timee) {

        description.setText(desc);
        user.setText(id);
        type.setValue(typee);
        type.setItems(recList);
        time.setText(timee);
    }

void setButton(String aer){
    envoi.setText("Modifier");
}
    
    
          
          
      
        
    }

   
    

