/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.vromvrom;

import com.jfoenix.controls.JFXComboBox;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import static tn.esprit.vromvrom.DashboardAdminController.RecupCombo;
import tn.esprit.vromvrom.Database.Database;
import tn.esprit.vromvrom.Model.Reclamation;
import tn.esprit.vromvrom.Model.User;
import tn.esprit.vromvrom.service.ServiceReclamation;
import tn.esprit.vromvrom.service.ServiceRole;
import tn.esprit.vromvrom.service.ServiceUser;

/**
 * FXML Controller class
 *
 * @author MediaCenter Zaghouan
 */
public class ManageUserController implements Initializable {

    @FXML
    private TableView<User> tab_User;
    @FXML
    private TableColumn<User, String> r;
    @FXML
    private TableColumn<User, String> n;
    @FXML
    private TableColumn<User, String> p;
    @FXML
    private TableColumn<User, String> e;
    @FXML
    private TableColumn<User, String> u;
    @FXML
    private TableColumn<User, String> s;
    @FXML
    private TableColumn<User, Integer> user;
  
@FXML
    private TableColumn<User, Integer> num;
    /**
     * Initializes the controller class.
     */
    ServiceUser usr = new ServiceUser();
    
                    ObservableList<User> Chercheuser;

    @FXML
    private TextField recherche;
    @FXML
    private TableColumn<User, String> Action;
    public ManageUserController(){
        Connection cnx = Database.getInstance().getCnx();
    }
      private Connection cnx;
    
    @FXML
    private Button Add2;
    @FXML
    private Button Edi;
    @FXML
    private Button dd;
    @FXML
    private TextField uu;
    @FXML
    private Button img;
    @FXML
    private TextField urlx;
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
    private JFXComboBox<String> comm;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        table2();
         user.setVisible(false);
        uu.setVisible(false);
        urlx.setVisible(false);
                comm.setItems(usr.RecupCombo());
                ChercheFichier();

                 tab_User.setOnMouseClicked((MouseEvent event) -> {
    if (event.getClickCount() > 0) {
        onEdit2();
        
    }
});
                 
                  Callback<TableColumn<User, String>, TableCell<User, String>> cellFoctory = (TableColumn<User, String> param) -> {
            // make cell containing buttons
            final TableCell<User, String> cell = new TableCell<User, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.CLOSE);
                         FontAwesomeIconView tickIcon = new FontAwesomeIconView(FontAwesomeIcon.CHECK);
                       

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        tickIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00FF00;"
                        );
                        
                         deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            try {
                                User us = tab_User.getSelectionModel().getSelectedItem();
                                ServiceUser usr = new ServiceUser();
                                System.out.println(us);
                                usr.BAN(us);
                                table2();
                                JOptionPane.showMessageDialog(null,"l'utilisateur a éte bannée");

                            } catch (SQLException ex) {
                                Logger.getLogger(ManageUserController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            });
                         
                         tickIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            try {
                                User us = tab_User.getSelectionModel().getSelectedItem();
                                ServiceUser usr = new ServiceUser();
                                System.out.println(us);
                                usr.UNBAN(us);
                                table2();
                                JOptionPane.showMessageDialog(null,"l'utilisateur a éte activée");

                            } catch (SQLException ex) {
                                Logger.getLogger(ManageUserController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            });
                      
                     HBox managebtn = new HBox(deleteIcon,tickIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(deleteIcon, new Insets(2, 3, 0, 2));

                     

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
             Action.setCellFactory(cellFoctory);

                 
        
    }    

    
        public void onEdit2() {
               
               java.sql.Connection cnx;
     cnx = Database.getInstance().getCnx();
     
    if (tab_User.getSelectionModel().getSelectedItem() != null) {
          User f = tab_User.getSelectionModel().getSelectedItem();
          int i = f.getId_user();
        String n = String.valueOf(i);
         
          nom.setText(f.getNom());
          prenom.setText(f.getPrenom());
          Email.setText(f.getMail());
          nd.setText(f.getNomd());   
          uu.setText(n);
    }
}
           public void table2(){
         
//        r.setCellValueFactory( new PropertyValueFactory<>("Role"));
r.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<User,String>,ObservableValue<String>>(){
    @Override
    public ObservableValue<String> call(TableColumn.CellDataFeatures<User, String> param) {
                            return new SimpleStringProperty(param.getValue().getId_role().getRole());
    }
            });
        n.setCellValueFactory( new PropertyValueFactory<>("nom"));
        p.setCellValueFactory(new PropertyValueFactory <>("prenom"));
        e.setCellValueFactory( new PropertyValueFactory<>("mail"));
        u.setCellValueFactory(new PropertyValueFactory <>("Nomd"));
        s.setCellValueFactory(new PropertyValueFactory <>("status"));
        user.setCellValueFactory(new PropertyValueFactory <>("id_user"));
                num.setCellValueFactory(new PropertyValueFactory <>("num"));

          
        tab_User.setItems(usr.RecupBase2()); 
               System.out.println(usr.RecupBase2());
        
       }
           
           
            public static ObservableList<User> RecupBase2(){
             
    ObservableList<User> list = FXCollections.observableArrayList();
    
       java.sql.Connection cnx;
     cnx = Database.getInstance().getCnx();
          String sql = "select *from user ";
    try {
       
        PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);

    ResultSet R = st.executeQuery();
    while (R.next()){
        ServiceRole r = new ServiceRole();
        
//       r.SelectRole(R.getInt(2));
        
        User u = new User();
        u.setId_user(R.getInt(1));
     u.setId_role(r.SelectRole(R.getInt(2)));
     u.setNom(R.getString(3));
     u.setPrenom(R.getString(4));
     u.setMail(R.getString(5));
     u.setNomd(R.getString(6));
     u.setStatus(R.getString(8));
     u.setNum(R.getLong(10));

 

    
     
      list.add(u);
    }
    }catch (SQLException ex){
    ex.getMessage(); 
    } 
    return list;
    }
           
           
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
            st.setString(4, Email.getText());
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
    private void AddUser(ActionEvent event) throws SQLException {
                if (nd.getText().isEmpty()|| nom.getText().isEmpty() || Email.getText().isEmpty() || prenom.getText().isEmpty() || pass.getText().isEmpty() || comm.getValue().isEmpty())
         {
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Vérifier les paramétres");
            
            alert.setContentText("Remplir tous les paramétres");
            Optional<ButtonType> result = alert.showAndWait();}
           
           else if(!VerifNomDutil())  {
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Changer votre nom d'utilisateur");
            
            alert.setContentText("Nom d'utilisateur déja utilisé ");
            Optional<ButtonType> result = alert.showAndWait();}
           
           else if(!usr.VerifEmail(Email.getText())){
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
                
             else{
             
                    System.out.println(nd.getText());
                   EnregistrerVersBase2();
               JOptionPane.showMessageDialog(null,"L'utilisateur a été ajoutée avec succés");

           
           nom.clear();
           prenom.clear();
           Email.clear();
           nd.clear();
           pass.clear();
           urlx.clear();
           conf_pass.clear();
             
             }
    }

    
     public void ChercheFichier(){
      User f = new User();
        n.setCellValueFactory( new PropertyValueFactory<>("nom"));
        
        p.setCellValueFactory( new PropertyValueFactory<>("prenom"));
                e.setCellValueFactory( new PropertyValueFactory<>("mail"));

    
       
    Chercheuser = usr.RecupBase2();
    tab_User.setItems(usr.RecupBase2());
    FilteredList<User> filtreddata;
     filtreddata = new FilteredList<>(Chercheuser ,b ->true);
    recherche.textProperty().addListener((observable,oldValue,newValue)->{
      filtreddata.setPredicate((u  ->  {
          
          if((newValue ==null) || newValue.isEmpty())
          { return true;}
      
      String lowerCaseFilter = newValue.toLowerCase();
      if (u.getNom().toLowerCase().contains(lowerCaseFilter)){
      return true;
      } else if (u.getPrenom().toLowerCase().contains(lowerCaseFilter))
          {return true;}
         else if (u.getMail().toLowerCase().contains(lowerCaseFilter))
          {return true;}
     
        
      return false;
      })); 
    });
    
    SortedList<User> srt = new SortedList<>(filtreddata);
    srt.comparatorProperty().bind(tab_User.comparatorProperty());
    tab_User.setItems(srt);
    }
    


    @FXML
    private void Modifier2(ActionEvent event) {
        
        String username= nd.getText();
     
String no= nom.getText();
String pr= prenom.getText();
   String n= uu.getText();
        int i = Integer.valueOf(n);

       
       
        String sql ="UPDATE `user` SET `nom`='"+no+"',`prenom`='"+pr+"',`Nomd`='"+n+"' WHERE id_user='"+i+"'";
        try {
         PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);
         st.executeUpdate();
                  table2();

          JOptionPane.showMessageDialog(null," L'utilisateur a été modifier");
    }catch(SQLException ex){
        ex.getMessage();
    }
        
    }

    @FXML
    private void dell(ActionEvent event) throws SQLException {

        ServiceUser su = new ServiceUser();

        String n= uu.getText();
        int id = Integer.valueOf(n);
        User usr = new User();
        usr = su.SelectUser(id);
        System.out.println(r);
              su.delete(usr);
        
                         table2();

                JOptionPane.showMessageDialog(null,"L'utilisateur a été supprimer avec succés");
    
        

      
        
    }

    @FXML
    private void Ajoutefichier(ActionEvent event) {
             FileChooser f = new FileChooser();
             f.getExtensionFilters().addAll(
                      new FileChooser.ExtensionFilter("Image", "*.jpg", "*.png"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg")
             );
        File S = f.showOpenDialog(null);
         if(S!=null){
       
        
         String n = S.getAbsolutePath();
        urlx.setText(S.getAbsolutePath());
        
         }
    }
  
    
}
