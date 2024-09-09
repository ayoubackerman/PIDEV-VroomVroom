/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.vromvrom;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import tn.esprit.vromvrom.Database.Database;
import tn.esprit.vromvrom.service.ServiceUser;

/**
 * FXML Controller class
 *
 * @author MediaCenter Zaghouan
 */
public class HomeController implements Initializable {

    @FXML
    private Pane pane1;
    @FXML
    private PieChart pie;
    @FXML
    private Label tt;
    @FXML
    private Label ttt;
    @FXML
    private Label t;
     public HomeController(){
        Connection cnx = Database.getInstance().getCnx();
    }
       private ObservableList data;
     ServiceUser usr =new ServiceUser();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         t.setText(String.valueOf(usr.RecupTotal()));
            tt.setText(String.valueOf(usr.RecupTotalChauf()));
            ttt.setText(String.valueOf(usr.RecupTotalClient()));
            
              buildData();
        pie.getData().addAll(data);
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
    
    
}
