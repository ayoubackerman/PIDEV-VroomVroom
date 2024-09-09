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
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import tn.esprit.vromvrom.Database.Database;
import tn.esprit.vromvrom.Model.Reclamation;
import tn.esprit.vromvrom.Model.Reponse;
import tn.esprit.vromvrom.Model.User;
import tn.esprit.vromvrom.service.ServiceReponse;
import tn.esprit.vromvrom.service.ServiceUser;

/** 93010536
 * FXML Controller class
 *
 * @author ASUS
 */
public class ReponseController implements Initializable {
    
     private Connection cnx;


    public ReponseController(){
        cnx = Database.getInstance().getCnx();
    }
    
          @FXML
    private TextField statuts;

    @FXML
    private TextField rreponse;
    
     @FXML
    private TextField temps;
     
      @FXML
    private TextField id_rec;
      
      
       @FXML
    private TextField id_user;
       
         @FXML
    private TextField cherch;

    @FXML
    private TableView<Reclamation> tableau;

    @FXML
    private TableColumn<Reclamation, Integer> colrep;

    @FXML
    private TableColumn<Reclamation, Integer> coliduser;

    @FXML
    private TableColumn<Reclamation, String> colnom;
    
      @FXML
    private TableColumn<Reclamation, String> colrecl;

    @FXML
    private TableColumn<Reclamation, String> coltyperec;

    @FXML
    private TableColumn<Reclamation, String> colreso;


    @FXML
    private TableColumn<Reclamation, String> colpren;

    @FXML
    private TableColumn<Reclamation, String> colrepn;

  
    @FXML
    private TableColumn<Reclamation, String> coltimerep;

    @FXML
    private Button modrep;
    
    @FXML
    private Button ad;


    @FXML
    private Button adrep;

    @FXML
    private Button suprep;
    
     @FXML
    private Button rf;
     
     ObservableList<Reclamation> chercherecla ;
     
    
     public void table(){
         
        colrep.setCellValueFactory( new PropertyValueFactory<>("id_reclamation"));
       // coliduser.setCellValueFactory(new PropertyValueFactory <>("id_user"));
      coliduser.setCellValueFactory((TableColumn.CellDataFeatures<Reclamation, Integer> param) -> Bindings.createObjectBinding(() -> param.getValue().getId_user().getId_user()));
        // colnom.setCellValueFactory( new PropertyValueFactory<>("nom"));
       colnom.setCellValueFactory((TableColumn.CellDataFeatures<Reclamation, String> param) -> new SimpleStringProperty(param.getValue().getId_user().getNom()));
        //colpren.setCellValueFactory(new PropertyValueFactory <>("prenom"));
        colpren.setCellValueFactory((TableColumn.CellDataFeatures<Reclamation, String> param) -> new SimpleStringProperty(param.getValue().getId_user().getPrenom()));
          colrecl.setCellValueFactory( new PropertyValueFactory<>("reclamation"));
        coltyperec.setCellValueFactory(new PropertyValueFactory <>("type_reclamation"));
         colreso.setCellValueFactory( new PropertyValueFactory<>("resolution"));
         colrepn.setCellValueFactory( new PropertyValueFactory<>("reponse"));
         coltimerep.setCellValueFactory( new PropertyValueFactory<>("time"));
       
        tableau.setItems(RecupBase()); 

 
}
         public static ObservableList<Reclamation> RecupBase(){
             
    ObservableList<Reclamation> list = FXCollections.observableArrayList();
    
       java.sql.Connection cnx;
     cnx = Database.getInstance().getCnx();
          String sql = "select *from reclamation";
    try {
       
        PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);

    ResultSet R = st.executeQuery();
    
    while (R.next()){
        
        ServiceUser s = new ServiceUser();
        
        User us = new User();
        
        us = s.SelectUser(R.getInt(2));
        System.out.println(us);
         ServiceReponse aa = new ServiceReponse();
         Reponse bb = new Reponse();
       bb = aa.SelectReponse(R.getInt(1));
        System.out.println(bb);
      Reclamation r =new Reclamation();
      User u = new User(R.getInt(2));
      r.setId_reclamation(R.getInt(1));
     r.setId_user(us);
     r.setReclamation(R.getString(3));
     r.setResolution(R.getString(4));
     r.setType_reclamation(R.getString(5));
     r.setReponse(bb.getReponse());
     r.setTime(R.getString(6));
    
     
      list.add(r);
    }
    }catch (SQLException ex){
    ex.getMessage(); 
    } 
    return list;
    }
       
      

public void Adid(ActionEvent event) throws SQLException {
    ServiceReponse sr = new ServiceReponse();
    Reponse rp = new Reponse();
    User ur = new User(Integer.parseInt(id_user.getText()));
    rp.setReponse(rreponse.getText());
    rp.setId_user(ur);
    
    // Set the id_reclamation value for the Reponse object
    Reclamation rc = new Reclamation(Integer.parseInt(id_rec.getText()));
    rp.setId_reclamation(rc);
   // rp.setId_reclamation(Integer.parseInt(id_rec.getText()));
    
    // Add the Reponse object to the database
    sr.ajouter(rp);
    
    // Update the table view with the new data
    table();
}


  

    private void onEdit() {
                 java.sql.Connection cnx;
     cnx = Database.getInstance().getCnx();
     
    if (tableau.getSelectionModel().getSelectedItem() != null) {
          Reclamation f = tableau.getSelectionModel().getSelectedItem();
       int i = f.getId_reclamation();
        String n = String.valueOf(i);  
        int a = f.getId_user().getId_user();
        String b= String.valueOf(a);
        
                   rreponse.setText(f.getReponse());
                   temps.setText(f.getTime());
                  id_rec.setText(n);
                  id_user.setText(b);                   
    }}

    
    
    
            @FXML
  public void update(ActionEvent event) throws SQLException {
    ServiceReponse sr = new ServiceReponse();
    Reponse rp = new Reponse();
//    rp.setId_reclamation(Integer.parseInt(id_rec.getText()));
Reclamation rc = new Reclamation(Integer.parseInt(id_rec.getText()));
    rp.setId_reclamation(rc);
    rp.setReponse(rreponse.getText());
    sr.update(rp);
    sr.ResolutionREC(rp);
    table(); // assuming that table() method is used to refresh the table view
}


      
        
  
    
  @FXML
public void DELLTT(ActionEvent event) throws SQLException {
    ServiceReponse sr = new ServiceReponse();
    Reponse rp = new Reponse();
//    rp.setId_reclamation(Integer.parseInt(id_rec.getText()));
Reclamation rc = new Reclamation(Integer.parseInt(id_rec.getText()));
    rp.setId_reclamation(rc);
    if (sr.search(rp)) {
        sr.delete(rp);
        table(); // assuming that table() method is used to refresh the table view
    } else {
        System.out.println("La reclamation n'existe pas");
    }
}

   

public void ChercheReclamation(){
      User f = new User();
        colnom.setCellValueFactory((TableColumn.CellDataFeatures<Reclamation, String> param) -> new SimpleStringProperty(param.getValue().getId_user().getNom()));
        //colpren.setCellValueFactory(new PropertyValueFactory <>("prenom"));
                colpren.setCellValueFactory((TableColumn.CellDataFeatures<Reclamation, String> param) -> new SimpleStringProperty(param.getValue().getId_user().getPrenom()));

                coltyperec.setCellValueFactory( new PropertyValueFactory<>("type_reclamation"));

    
       
    chercherecla = RecupBase();
    tableau.setItems(RecupBase());
    FilteredList<Reclamation> filtreddata;
     filtreddata = new FilteredList<>(chercherecla ,b ->true);
    cherch.textProperty().addListener((observable,oldValue,newValue)->{
      filtreddata.setPredicate((u  ->  {
          
          if((newValue ==null) || newValue.isEmpty())
          { return true;}
      
      String lowerCaseFilter = newValue.toLowerCase();
  
         if (u.getType_reclamation().toLowerCase().contains(lowerCaseFilter))
          {return true;}
         else if (u.getId_user().getNom().toLowerCase().contains(lowerCaseFilter))
          {return true;}
          else if (u.getId_user().getPrenom().toLowerCase().contains(lowerCaseFilter))
          {return true;}
     
        
      return false;
      })); 
    });
    
    SortedList<Reclamation> srt = new SortedList<>(filtreddata);
    srt.comparatorProperty().bind(tableau.comparatorProperty());
    tableau.setItems(srt);
    }


    
    





      @Override
    public void initialize(URL url, ResourceBundle rb) { 
        table();
        ChercheReclamation();
       temps.setVisible(false);
       id_user.setDisable(true);
       id_rec.setDisable(true);
      tableau.setOnMouseClicked((MouseEvent event) -> {
    if (event.getClickCount() > 0) {
        onEdit();
        
    }
});
    }
      @FXML
    private void print(ActionEvent event) {
        
        
          PrinterJob job = PrinterJob.createPrinterJob();
       
        Node root= this.tableau;
        
     if(job != null){
     job.showPrintDialog(root.getScene().getWindow()); 
     Printer printer = job.getPrinter();
     PageLayout pageLayout = printer.createPageLayout(Paper.A3, PageOrientation.LANDSCAPE, Printer.MarginType.HARDWARE_MINIMUM);
     boolean success = job.printPage(pageLayout, root);
     if(success){
        job.endJob();
     }
   }
   
   
}
}