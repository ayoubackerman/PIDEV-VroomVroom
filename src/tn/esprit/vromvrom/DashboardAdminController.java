/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.vromvrom;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import tn.esprit.vromvrom.Database.Database;
import tn.esprit.vromvrom.Model.Role;
import tn.esprit.vromvrom.Model.User;
import tn.esprit.vromvrom.service.ServiceRole;

/**
 * FXML Controller class
 *
 * @author MediaCenter Zaghouan
 */
public class DashboardAdminController implements Initializable {

    /**
     * Initializes the controller class.
     */
      private Connection cnx;
    private TableView<User> tab_User;
   
    
    private TableColumn<User, String> r;
    private TableColumn<User, String> n;
    private TableColumn<User, String> p;
    private TableColumn<User, String> e;
    private TableColumn<User, String> u;
    private TableColumn<User, String> s;
    private TableColumn<User, Integer> user;
    private TextField uu;
    private TextField urlx;
    @FXML
    private HBox root;
    @FXML
    private AnchorPane side_ankerpane;
    @FXML
    private Pane inner_pane;
    @FXML
    private Pane most_inner_pane;
    @FXML
    private JFXButton btn_workbench;
    @FXML
    private JFXButton btn_workbench1;
    @FXML
    private JFXButton btn_workbench11;
    @FXML
    private JFXButton btn_workbench12;
    @FXML
    private JFXButton btn_workbench111;
    @FXML
    private JFXButton btn_workbench1111;
    @FXML
    private JFXTextField txt_serach;
    @FXML
    private Pane pane_1;
    @FXML
    private Pane pane_11;
    @FXML
    private ProgressBar progress21;
    @FXML
    private Pane pane_12;
    @FXML
    private ProgressBar progress2;
    @FXML
    private Pane pane_111;
    @FXML
    private Pane pane_1111;
    @FXML
    private ProgressBar progress111;
    @FXML
    private Pane inner_pane1;
    @FXML
    private Pane pane_13;
    @FXML
    private Pane pane_131;
    @FXML
    private Pane pane_1311;
    @FXML
    private Pane pane_132;
    @FXML
    private JFXButton btn_workbench11111;

    public DashboardAdminController(){
        cnx = Database.getInstance().getCnx();
    }
    
    private TableView<Role> tab;
    
    private TableColumn<Role,Integer> columnid;

    private TableColumn<Role,String> columnRole;
    
    private TextField Role_txt ;
     
     private TextField idx;
     
    private ComboBox<String> comm;
    private TextField mail;

    private TextField nom;

    private TextField prenom;

    private TextField nd;

   

    private PasswordField pass;

    
    
    
      public void onEdit2() {
               
               java.sql.Connection cnx;
     cnx = Database.getInstance().getCnx();
     
    if (tab_User.getSelectionModel().getSelectedItem() != null) {
          User f = tab_User.getSelectionModel().getSelectedItem();
          int i = f.getId_user();
        String n = String.valueOf(i);
         
          nom.setText(f.getNom());
          prenom.setText(f.getPrenom());
          mail.setText(f.getMail());
          nd.setText(f.getNomd());   
          uu.setText(n);
    }
}
      @FXML
        void Modifier2(ActionEvent event) {
        String username= nd.getText();
     
String no= nom.getText();
String pr= prenom.getText();
   String n= uu.getText();
        int i = Integer.valueOf(n);

       
       
        String sql ="UPDATE `user` SET `nom`='"+no+"',`prenom`='"+pr+"',`Nomd`='"+n+"' WHERE id_user='"+i+"'";
        try {
         PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);
         st.executeUpdate();
          JOptionPane.showMessageDialog(null," L'utilisateur a été modifier");
    }catch(SQLException ex){
        ex.getMessage();
    }
        

    }
      @FXML
      void dell(ActionEvent event) {
          
          
          
        String n= uu.getText();
        int id = Integer.valueOf(n);
     

       
       
        String sql ="DELETE FROM `user` WHERE id_user='" + id + "'";
        try {
         PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);
         st.executeUpdate();
          JOptionPane.showMessageDialog(null,"L'utilisateur a été supprimer");
    }catch(SQLException ex){
        ex.getMessage();
    }
        

    }
     

    
 
       public String encryptThisString(String input) 
    { 
        try { 
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest = md.digest(input.getBytes()); 
            BigInteger no = new BigInteger(1, messageDigest); 
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
            return hashtext; 
        } 
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    }
    
//      private void EnregistrerVersBase() {
//           java.sql.Connection cnx;
//     cnx = Database.getInstance().getCnx();
//             
//        
//           try {
//            String sql = "INSERT INTO role (role) VALUES (?)";
//            
//        PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);
//            st.setString(1, Role_txt.getText());
//         
//        
//            st.executeUpdate();
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null,"Error");
//        }
//    } 
    
       private void EnregistrerVersBase2() {
           java.sql.Connection cnx;
     cnx = Database.getInstance().getCnx();
             
        
           try {
            String sql = "INSERT INTO user (id_role ,nom ,prenom ,mail ,Nomd ,mdp,Image) VALUES (?,?,?,?,?,?,?)";
            
        PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);
        
        
        
        int i = comm.getSelectionModel().getSelectedIndex() + 1;
               System.out.println(i);
            st.setInt(1, i);
            st.setString(2, nom.getText());
            st.setString(3, prenom.getText());
            st.setString(4, mail.getText());
            st.setString(5, nd.getText());
            st.setString(6, encryptThisString(pass.getText()));
            st.setString(7,urlx.getText());
               System.out.println(urlx.getText());
               System.out.println(encryptThisString(pass.getText()));
            st.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error");
        }
    } 
       @FXML
       private void AddUser(ActionEvent event) throws SQLException{
           
                    if (nd.getText().isEmpty()|| nom.getText().isEmpty() || mail.getText().isEmpty() || prenom.getText().isEmpty() || pass.getText().isEmpty() || comm.getValue().isEmpty())
         {
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Vérifier les paramétres");
            
            alert.setContentText("Remplir tous les paramétres");
            Optional<ButtonType> result = alert.showAndWait();}
           
           else if(VerifNomDutil())  {
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Changer votre nom d'utilisateur");
            
            alert.setContentText("Nom d'utilisateur déja utilisé ");
            Optional<ButtonType> result = alert.showAndWait();}
           
           else if(!VerifEmail(mail.getText())){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Email incorrecte");
            
            alert.setContentText("Veuillez verifier la structure d'email");
            Optional<ButtonType> result = alert.showAndWait();
           }
           else if(urlx.getText().isEmpty()){
                 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Ajouter une image");
            
            alert.setContentText("Ajouter une image");
            Optional<ButtonType> result = alert.showAndWait();
           
           }
           
           
         else {
           
           EnregistrerVersBase2();
           
           nom.clear();
           prenom.clear();
           mail.clear();
           nd.clear();
           pass.clear();
           urlx.clear();
           
           
         
           }
       
       
       }
       @FXML
    private void Ajoutefichier(ActionEvent event) {
              FileChooser f = new FileChooser();
        File S = f.showOpenDialog(null);
         if(S!=null){
       
        
         String n = S.getAbsolutePath();
        urlx.setText(S.getAbsolutePath());
        
         }
    }
      @FXML
       private void AddRole(ActionEvent event){
           
           
           if(Role_txt.getText().isEmpty()){
                   Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Vérifier les paramétres");
            
            alert.setContentText("Remplir tous les paramétres");
            Optional<ButtonType> result = alert.showAndWait();
           }
           else{
//           EnregistrerVersBase();
             ServiceRole sr = new ServiceRole();
             Role r = new Role();
             r.setRole(Role_txt.getText());
               try {
                   sr.ajouter(r);
               } catch (SQLException ex) {
                   Logger.getLogger(DashboardAdminController.class.getName()).log(Level.SEVERE, null, ex);
               }

           table();
           
           Role_txt.clear();
       
           }
       
       }
    
        ObservableList<Role> List;

    
         public void table(){
         
        columnid.setCellValueFactory( new PropertyValueFactory<>("id_role"));
        columnRole.setCellValueFactory(new PropertyValueFactory <>("role"));
        ServiceRole sr = new ServiceRole();

        tab.setItems(sr.RecupBase()); 



}
         
         
         
           public void onEdit() {
               
               java.sql.Connection cnx;
     cnx = Database.getInstance().getCnx();
     
    if (tab.getSelectionModel().getSelectedItem() != null) {
          Role f = tab.getSelectionModel().getSelectedItem();
         int i = f.getId_role();
        String n = String.valueOf(i);
      
          idx.setText(n);
          Role_txt.setText(f.getRole());
          
          
          
                  
       
        
      
            
    }
}
           @FXML
             void Modifier(ActionEvent event) {
        String role= Role_txt.getText();
        String id = idx.getText();
                int i=Integer.valueOf(id);

       
       
        String sql ="update role set role='"+role+"' where id_role='"+i+"'";
        try {
         PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);
         st.executeUpdate();
            table();
          JOptionPane.showMessageDialog(null,"Le role a été modifier");
    }catch(SQLException ex){
        ex.getMessage();
    }
        

    }
      
     
      
      
      
      
          @FXML   
    private void Supprimer(ActionEvent event) throws SQLException {
         
        ServiceRole sr = new ServiceRole();
        
        String idf=idx.getText();
        int i=Integer.valueOf(idf);
        Role r = new Role();
        r = sr.SelectRole(i);
         
              System.out.println(r);
              sr.delete(r);
        
                         table();

//        SuppRole(i);
     
       
        
                JOptionPane.showMessageDialog(null,"Le role a été supprimer avec succés");
    
        
    }
     
//         public void SuppRole(int id){
//             
//               
//        String sql ="DELETE FROM role WHERE id_role="+ id +"";
//        try {
//         PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);
//         st.executeUpdate();
//            table();
//    }catch(SQLException ex){
//        ex.getMessage();
//    }
//        
//         
//         
//         }
         
         public static ObservableList<String> RecupCombo(){
             
             
    ObservableList<String> list = FXCollections.observableArrayList();
    
       java.sql.Connection cnx;
     cnx = Database.getInstance().getCnx();
          String sql = "SELECT role FROM `role`";
    try {
       
        PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);

    ResultSet R = st.executeQuery();
    while (R.next()){
      
     
   String r = R.getString(1);
        System.out.println(r);
    
     
      list.add(r);
    }
    }catch (SQLException ex){
    ex.getMessage(); 
    } 
    return list;
    }
         
         
            public  boolean VerifEmail(String email) 
    { 
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
                              
        Pattern pat = Pattern.compile(emailRegex); 
        if (email == null) 
            return false; 
        return pat.matcher(email).matches(); 
    } 
 //Verifier le nom d'utilisateur s'il existe ou pas 
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
         
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        table();
        
        comm.setItems(RecupCombo());
        columnid.setVisible(false);
        idx.setVisible(false);
        user.setVisible(false);
        uu.setVisible(false);
        urlx.setVisible(false);
        
             tab.setOnMouseClicked((MouseEvent event) -> {
    if (event.getClickCount() > 0) {
        onEdit();
        
    }
});
               tab_User.setOnMouseClicked((MouseEvent event) -> {
    if (event.getClickCount() > 0) {
        onEdit2();
        
    }
});
    }    
    
}
