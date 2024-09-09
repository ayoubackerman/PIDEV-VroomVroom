/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.vromvrom;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import tn.esprit.vromvrom.Database.Database;
import tn.esprit.vromvrom.service.ServiceUser;
import utils.SMS;



/**
 * FXML Controller class
 *
 * @author MediaCenter Zaghouan
 */
public class ForgetpasswordController implements Initializable {

    @FXML
    private Button close;
    @FXML
    private Button goback;
    @FXML
    private TextField jTextField7;
    @FXML
    private TextField code;
    @FXML
    private Label alert;
    @FXML
    private Button vrif;
    @FXML
    private Label alert2;
    @FXML
    private Button vrif1;
    @FXML
    private Button vrif2;
    @FXML
    private TextField SMS;
    @FXML
    private TextField Email;
    @FXML
    private TextField Pass;
    @FXML
    private Button newpass;

    /**
     * Initializes the controller class.
     */
     public ForgetpasswordController(){
        cnx = Database.getInstance().getCnx();
    }
         private Connection cnx;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
            Randum();
            jTextField7.setVisible(false);
                        jTextField7.setVisible(false);
            alert.setVisible(false);
            alert2.setVisible(false);
            vrif.setVisible(false);
            code.setVisible(false);
            Pass.setVisible(false);
            newpass.setVisible(false);


    }    
    

    
    public void Randum(){
       
       Random rd = new Random();
       jTextField7.setText(""+rd.nextInt(10000+1));
       
       }
    
    @FXML
    public void VerifSMS() throws SQLException{
    
     if (SMS.getText().isEmpty())
         {
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur");
            
            alert.setContentText("Please fill all input");
            Optional<ButtonType> result = alert.showAndWait();}
           
        
             else if(!VeriSMS()){
                 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur");
            
            alert.setContentText("INvalid Phone NUmber");
            Optional<ButtonType> result = alert.showAndWait();
             }
             
            
           
           
         else {
           
           SMS s = new SMS();
           s.sendsms(jTextField7.getText());
           alert.setVisible(true);
            alert2.setVisible(true);
            vrif.setVisible(true);
            code.setVisible(true);
            Email.setVisible(false);
            vrif1.setVisible(false);
            vrif2.setVisible(false);
            SMS.setVisible(false);
           
//           JOptionPane.showMessageDialog(null,"Account created successfully");

          
           
      
//           
//           
//         
           }
       

        
    
    
    
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
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(Email.getText().trim()));
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
    
     
      @FXML
       private void VerifMail(ActionEvent event) throws SQLException{
           
                    if (Email.getText().isEmpty())
         {
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur");
            
            alert.setContentText("Please fill all input");
            Optional<ButtonType> result = alert.showAndWait();}
           
        
             else if(!Verifmail()){
                 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur");
            
            alert.setContentText("INvalid adresse mail");
            Optional<ButtonType> result = alert.showAndWait();
             }
             
            
           
           
         else {
           
           sendmail();
           alert.setVisible(true);
            alert2.setVisible(true);
            vrif.setVisible(true);
            code.setVisible(true);
            Email.setVisible(false);
            vrif1.setVisible(false);
            vrif2.setVisible(false);
            SMS.setVisible(false);
           
//           JOptionPane.showMessageDialog(null,"Account created successfully");

          
           
      
//           
//           
//         
           }
       
      }
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
     
                  public boolean VeriSMS() throws SQLException{
      
            String sql = "select * from user where num = ?";
            PreparedStatement ps =  cnx.prepareStatement(sql);
            ps.setString(1,SMS.getText());
            
            ResultSet rs = ps.executeQuery();
             boolean ok=false;
            if(rs.next()){
         if (rs.getString(10).equals(SMS.getText()))
             ok=true;
     }
     return ok;
          }
     
     
    @FXML
    private void exit(ActionEvent event) {
    }

    @FXML
    private void Goback(ActionEvent event) {
    }

    
    @FXML 
    private void changepassword(ActionEvent event){
    
        
       if(Pass.getText().length()<8){
                 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur");
            
            alert.setContentText("Password is too weak");
            Optional<ButtonType> result = alert.showAndWait();
           
           }
       else{
        
        
        
        ServiceUser usr = new ServiceUser();
        
          String password= usr.encryptThisString(Pass.getText());
     
String email = Email.getText();

           System.out.println(password);

       
       
        String sql ="UPDATE `user` SET `mdp`='"+password+"' WHERE mail='"+email+"'  ";
        try {
         PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);
         st.executeUpdate();

          JOptionPane.showMessageDialog(null," mot de passe à éte modifiée");
    }catch(SQLException ex){
        ex.getMessage();
    }
        
    
    
       }
    
    
    }
    
    
    
    @FXML
    private void Veifcode(ActionEvent event) {
          if(!code.getText().equals(jTextField7.getText())){
       
       
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Erreur");
            
            alert.setContentText("invalid code");
            Optional<ButtonType> result = alert.showAndWait();
       
       }
       else {
              vrif.setVisible(false);
              code.setVisible(false);
            Pass.setVisible(true);
            newpass.setVisible(true);    
 
       }
    }
    
}
