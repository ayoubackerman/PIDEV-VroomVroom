/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.vromvrom;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tn.esprit.vromvrom.Model.User;
import java.io.File;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.util.Duration;
import tn.esprit.vromvrom.Database.Database;
import tn.esprit.vromvrom.service.ServiceUser;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author MediaCenter Zaghouan
 */
public class DashboardUserController implements Initializable {

    @FXML
    private HBox root;
    @FXML
    private AnchorPane side_ankerpane;
    @FXML
    private Pane inner_pane;
    @FXML
    private JFXButton Home;
    @FXML
    private Pane pane;
    @FXML
    private Pane pane1;
    @FXML
    private Button Sattings;
    @FXML
    private JFXButton Home1;
    @FXML
    private JFXButton Home2;
    @FXML
    private JFXButton Home5;

    
    @FXML
    private Label bvn;
    @FXML
    private ImageView img;
    @FXML
    private PieChart pie;
    @FXML
    private JFXButton Home21;
    @FXML
    private JFXButton conversation;
    @FXML
    private JFXButton conversation1;
    @FXML
    private JFXButton conversation11;
    @FXML
    private JFXButton conversation111;
    @FXML
    private Label t;
    @FXML
    private Label tt;
    @FXML
    private Label ttt;
   public DashboardUserController(){
        Connection cnx = Database.getInstance().getCnx();
    }
       private ObservableList data;
     ServiceUser usr =new ServiceUser();

   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            
            t.setText(String.valueOf(usr.RecupTotal()));
            tt.setText(String.valueOf(usr.RecupTotalChauf()));
            ttt.setText(String.valueOf(usr.RecupTotalClient()));

            
            String s = User.connecte.getImage().toString();
            
            File file = new File(s);
            
            
            System.out.println(s);
            Image image = new Image(file.toURI().toURL().toExternalForm());
            img.setImage(image);
            
            bvn.setText("Welcome"+" "+User.connecte.getNom()+" "+User.connecte.getPrenom());
            
        buildData();
        pie.getData().addAll(data);

            
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(DashboardUserController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }    
    
      @FXML
    private void Logout(ActionEvent event) {
         try {
                     Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow(); 
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));       
                    stage.setScene(scene);
                    stage.setTitle("Login");
                    stage.show();  
                     TrayNotification tray = new TrayNotification();
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.setTitle("Goodbye");
            tray.setMessage("Thank you visiting us !");
            tray.setAnimationType(AnimationType.SLIDE);
            tray.showAndDismiss(Duration.millis(1500));
                    
        } catch (IOException ex) {    

            Logger.getLogger(DashboardUserController.class.getName()).log(Level.SEVERE, null, ex);

    }
    }
      public void buildData(){
          java.sql.Connection cnx;
     cnx = Database.getInstance().getCnx();
          data = FXCollections.observableArrayList();
          try{
            //SQL FOR SELECTING NATIONALITY OF CUSTOMER
            String SQL =" SELECT COUNT(*), role FROM user u,role r where u.id_role = r.id_role GROUP BY u.id_role";

            ResultSet rs = cnx.createStatement().executeQuery(SQL);
            while(rs.next()){
                //adding data on piechart data
                data.add(new PieChart.Data(rs.getString(2),rs.getInt(1)));
            }
          }catch(Exception e){
              System.out.println("Error on DB connection");
              return;
          }

      }
    
    @FXML
    public void LoadScreen(ActionEvent event) throws IOException{
     
        Parent fxml = FXMLLoader.load(getClass(). getResource("Login.fxml"));
        pane1.getChildren().removeAll();
        pane1.getChildren().setAll(fxml);
    }
        @FXML
    public void LoadSettings(ActionEvent event) throws IOException{
     
        Parent fxml = FXMLLoader.load(getClass(). getResource("Profile.fxml"));
        pane1.getChildren().removeAll();
        pane1.getChildren().setAll(fxml);
    }
          @FXML
    public void LoadRole(ActionEvent event) throws IOException{
     
        Parent fxml = FXMLLoader.load(getClass(). getResource("ManageRole.fxml"));
        pane1.getChildren().removeAll();
        pane1.getChildren().setAll(fxml);
    }
         @FXML
    public void LoadUser(ActionEvent event) throws IOException{
     
        Parent fxml = FXMLLoader.load(getClass(). getResource("ManageUser.fxml"));
        pane1.getChildren().removeAll();
        pane1.getChildren().setAll(fxml);
    }
        @FXML
    public void LoadReclamation(ActionEvent event) throws IOException{
     
        Parent fxml = FXMLLoader.load(getClass(). getResource("Reponse.fxml"));
        pane1.getChildren().removeAll();
        pane1.getChildren().setAll(fxml);
    }
    @FXML
      public void LoadConversation(ActionEvent event) throws IOException{
     
        Parent fxml = FXMLLoader.load(getClass(). getResource("ChatUrgence.fxml"));
        pane1.getChildren().removeAll();
        pane1.getChildren().setAll(fxml);
    }
    @FXML
         public void LoadUrgence(ActionEvent event) throws IOException{
     
        Parent fxml = FXMLLoader.load(getClass(). getResource("urgence.fxml"));
        pane1.getChildren().removeAll();
        pane1.getChildren().setAll(fxml);
    }
    @FXML
           public void LoadVoiture(ActionEvent event) throws IOException{
     
        Parent fxml = FXMLLoader.load(getClass(). getResource("VoitureUrgence.fxml"));
        pane1.getChildren().removeAll();
        pane1.getChildren().setAll(fxml);
    }
            @FXML
           public void LoadCode(ActionEvent event) throws IOException{
     
        Parent fxml = FXMLLoader.load(getClass(). getResource("verifcode.fxml"));
        pane1.getChildren().removeAll();
        pane1.getChildren().setAll(fxml);
    }



  
}
