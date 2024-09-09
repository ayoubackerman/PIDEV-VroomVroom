/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.vromvrom;

import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import tn.esprit.vromvrom.Database.Database;
import tn.esprit.vromvrom.service.ServiceUser;

/**
 * FXML Controller class
 *
 * @author MediaCenter Zaghouan
 */
public class SignupController implements Initializable {

    @FXML
    private Button close;
    @FXML
    private Button goback;
    @FXML
    private TextField nom;
    @FXML
    private TextField Email;
    @FXML
    private TextField prenom;
    @FXML
    private TextField nd;
    @FXML
    private PasswordField conf_pass;
    @FXML
    private PasswordField pass;
    @FXML
    private Button register;
    @FXML
    private JFXComboBox<String> comm;

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField jTextField7;
    @FXML
    private TextField code;
    @FXML
    private Label alert;
    @FXML
    private Button vrif;
    @FXML
    private PasswordField num;
    @FXML
    private Label alert2;
     public SignupController(){
        cnx = Database.getInstance().getCnx();
    }
         private Connection cnx;

    
                 ServiceUser us = new ServiceUser();

        private void EnregistrerVersBase2() {
           java.sql.Connection cnx;
     cnx = Database.getInstance().getCnx();
             
           try {
            String sql = "INSERT INTO user (id_role ,nom ,prenom ,mail ,Nomd ,mdp,num) VALUES (?,?,?,?,?,?,?)";
            
        PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);
        String n = num.getText();
        int numero = Integer.valueOf(n);
        
        
        int i = comm.getSelectionModel().getSelectedIndex() + 2;
               System.out.println(i);
            st.setInt(1, i);
            st.setString(2, nom.getText());
            st.setString(3, prenom.getText());
            st.setString(4, Email.getText());
            st.setString(5, nd.getText());
            st.setString(6, us.encryptThisString(pass.getText()));
            st.setInt(7, numero);

        
            st.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error");
        }
    } 
        
         @FXML
       private void AddUser(ActionEvent event) throws SQLException{
           
                    if (nd.getText().isEmpty()|| nom.getText().isEmpty() || Email.getText().isEmpty() || prenom.getText().isEmpty() || pass.getText().isEmpty() || comm.getValue().isEmpty())
         {
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur");
            
            alert.setContentText("Please fill all input");
            Optional<ButtonType> result = alert.showAndWait();}
           
           else if(VerifNomDutil())  {
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur");
            
            alert.setContentText("Username already existe");
            Optional<ButtonType> result = alert.showAndWait();}
           
           else if(!us.VerifEmail(Email.getText())){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur");
            
            alert.setContentText("invalid Email format");
            Optional<ButtonType> result = alert.showAndWait();
           }
           else if(pass.getText().length()<8){
                 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur");
            
            alert.setContentText("Password is too weak");
            Optional<ButtonType> result = alert.showAndWait();
           
           }
             else if(!pass.getText().equals(conf_pass.getText())){
                 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur");
            
            alert.setContentText("confirm your password");
            Optional<ButtonType> result = alert.showAndWait();
             }
             else if(Verifmail()){
                 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur");
            
            alert.setContentText("Email already used by another user");
            Optional<ButtonType> result = alert.showAndWait();
             }
             
            
           
           
         else {
           
           sendmail();
           
//           JOptionPane.showMessageDialog(null,"Account created successfully");

          
           
           nom.setVisible(false);
           prenom.setVisible(false);
           Email.setVisible(false);
           nd.setVisible(false);
           pass.setVisible(false);
           conf_pass.setVisible(false);
           code.setVisible(true);
           vrif.setVisible(true);
           alert.setVisible(true);
           alert2.setVisible(true);
           num.setVisible(false);

           comm.setVisible(false);
//           
//           
//         
           }
       
      }
       
       
         @FXML
       private void Veifcode(ActionEvent event){
       if(!code.getText().equals(jTextField7.getText())){
       
       
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur");
            
            alert.setContentText("invalid code");
            Optional<ButtonType> result = alert.showAndWait();
       
       }
       else {
       EnregistrerVersBase2();
                  JOptionPane.showMessageDialog(null,"Account created successfully");
                       nom.setVisible(true);
           prenom.setVisible(true);
           Email.setVisible(true);
           nd.setVisible(true);
           pass.setVisible(true);
           conf_pass.setVisible(true);
           code.setVisible(false);
           vrif.setVisible(false);
           alert.setVisible(false);
           comm.setVisible(true);
           nom.clear();
           prenom.clear();
           Email.clear();
           nd.clear();
           pass.clear();
           conf_pass.clear();
 
       }
       }
       
       public void Randum(){
       
       Random rd = new Random();
       jTextField7.setText(""+rd.nextInt(1000+1));
       
       }
       
       public void sendmail(){
       
           Properties props=new Properties();
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.port",465);
        props.put("mail.smtp.user","benbrahimayoubben@gmail.com");
        props.put("mail.smtp.auth",true);
        props.put("mail.smtp.starttls.enable",true);
        props.put("mail.smtp.debug",true);
        props.put("mail.smtp.socketFactory.port",465);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback",false); 
        
        try {             
                Session session = Session.getDefaultInstance(props, null);
                session.setDebug(true);
                MimeMessage message = new MimeMessage(session);
                message.setText("Your OTP is " + jTextField7.getText());
                message.setSubject("OTP For your Neftola Account");
                message.setFrom(new InternetAddress("benbrahimayoubben@gmail.com"));
                message.addRecipient(RecipientType.TO, new InternetAddress(Email.getText().trim()));
                message.saveChanges();
                try
                {
                Transport transport = session.getTransport("smtp");
                transport.connect("smtp.gmail.com","benbrahimayoubben@gmail.com","rayxxvzckpdqvrvs");
                transport.sendMessage(message, message.getAllRecipients());
                transport.close();
                
            
                
                JOptionPane.showMessageDialog(null,"OTP has send to your Email id"); 
                }catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null,"Please check your internet connection");
                }              
            
        } catch (Exception e) {
            e.printStackTrace();  
            JOptionPane.showMessageDialog(null,e);
        }  
       }
 
 //Verifier le nom d'utilisateur s'il existe ou pas 
       
         public boolean Verifmail() throws SQLException{
      
            String sql = "select * from user where mail = ?";
            PreparedStatement ps =  cnx.prepareStatement(sql);
            ps.setString(1,Email.getText());
            
            ResultSet rs = ps.executeQuery();
             boolean ok=false;
            if(rs.next()){
         if (rs.getString(5).equals(Email.getText()))
             ok=true;
     }
     return ok;
          }
       
       
  public boolean VerifNomDutil() throws SQLException{
      
            String sql = "select * from user where Nomd=?";
            PreparedStatement p  = cnx.prepareStatement(sql);
            p.setString(1,nd.getText());
            ResultSet rs = p.executeQuery();
     boolean ok=false;
     if (rs.next()) {         
         if (rs.getString(6).equals(nd.getText()))
             ok=true;
     }
     return ok;
          }
    
       @FXML
    private void exit(ActionEvent event){
        
        System.exit(0);
    }
    
       @FXML
    private void Goback(ActionEvent event) throws IOException{
        
Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow(); 
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));       
                    stage.setScene(scene);
                    stage.setTitle("Login");
                    stage.show();  
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    ObservableList<String> list = FXCollections.observableArrayList("Chauffeur","Client");
    comm.setItems(list);
    Randum();
    jTextField7.setVisible(false);

        code.setVisible(false);
        vrif.setVisible(false);
        alert.setVisible(false);
                alert2.setVisible(false);

    }    

    
}
