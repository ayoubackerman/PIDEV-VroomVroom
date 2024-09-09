/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.vromvrom;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import tn.esprit.vromvrom.Database.Database;
import tn.esprit.vromvrom.Model.Role;
import tn.esprit.vromvrom.service.ServiceRole;

/**
 * FXML Controller class
 *
 * @author MediaCenter Zaghouan
 */
public class ManageRoleController implements Initializable {

    @FXML
    private TableView<Role> tab;
    @FXML
    private TableColumn<Role, Integer> columnid;
    @FXML
    private TableColumn<Role, String> columnRole;
    @FXML
    private TextField Role_txt;
    @FXML
    private Button Add;
    @FXML
    private TextField idx;
    @FXML
    private Button del;
    @FXML
    private Button Edit;

    @FXML
    private Pagination pagination;
    
          private final static int rowPerPage = 10;
    
    

    /**
     * Initializes the controller class.
     */
            ServiceRole sr = new ServiceRole();

    
      public void table(){
         
        columnid.setCellValueFactory( new PropertyValueFactory<>("id_role"));
        columnRole.setCellValueFactory(new PropertyValueFactory <>("role"));

        tab.setItems(sr.RecupBase()); 



}
      private Node createPage(int pageIndex){
      int fromIndex = pageIndex * rowPerPage ;
      int toIndex = Math.min(fromIndex + rowPerPage , data.size());
      tab.setItems(FXCollections.observableArrayList(data.subList(fromIndex, toIndex)));
      
      return tab;
      
      }
            private  List<Role> data = sr.RecupBase();

        
         
         
         
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
           
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        table();
        pagination.setPageFactory(this::createPage);
        idx.setVisible(false);
                columnid.setVisible(false);

             tab.setOnMouseClicked((MouseEvent event) -> {
    if (event.getClickCount() > 0) {
        onEdit();
        
    }
});
    }    

    @FXML
    private void AddRole(ActionEvent event) {
        
            
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
                   data = sr.RecupBase();
//                   pagination.setPageFactory(this::createPage);

               } catch (SQLException ex) {
                   Logger.getLogger(DashboardAdminController.class.getName()).log(Level.SEVERE, null, ex);
               }

           table();
           
           Role_txt.clear();
       
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

    @FXML
    private void Modifier(ActionEvent event) {
    }

   
    
}
