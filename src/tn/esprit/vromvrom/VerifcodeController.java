/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tn.esprit.vromvrom;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;
import net.glxn.qrgen.QRCode;
import tn.esprit.vromvrom.Database.Database;
import tn.esprit.vromvrom.Model.verifcode;
import tn.esprit.vromvrom.service.ServiceVerifcode;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author user
 */
public class VerifcodeController implements Initializable {
    
    Connection cnx;
    @FXML
    private ImageView codeQR;
    @FXML
    private Button ajou;
    @FXML
    private Label Codelabel;
    @FXML
    private TableView<verifcode> tableCode;
    private Statement ste;
    private Connection con;
    private final ObservableList<verifcode> data = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    
    
    ServiceVerifcode sv = new ServiceVerifcode();
    @FXML
    private TextField pourcentage;
    @FXML
    private TableColumn<verifcode, Integer> idT;
    @FXML
    private TableColumn<verifcode, String> codeT;
    @FXML
    private TableColumn<verifcode, Float> pourcentageT;
    @FXML
    private TableColumn<verifcode, String> etatT;
    @FXML
    private TableColumn<verifcode, ImageView> imageqrT;
    @FXML
    private TextField recherche;
    
  @FXML
    void EnregistrerVersBase(ActionEvent event) throws SQLException {

        verifcode verif= new verifcode(Codelabel.getText(), Float.valueOf(pourcentage.getText()) , "Disponible");
        sv.ajouter(verif);
        Aff();
        TrayNotification tray = new TrayNotification();
        tray.setTitle("Ajouter");
        tray.setMessage("Ajouté avec succès");
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndWait();


   

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Aff();
    }

    public void Aff(){
                                try {
            con = Database.getInstance().getCnx();
            ste = con.createStatement();
            data.clear();

            ResultSet rs = ste.executeQuery("select * from verifcode");
            while(rs.next()){
                   int id=rs.getInt(1);
                   String code=rs.getString("code");
                   float pourcentage=rs.getFloat("pourcentage");
                   String etat=rs.getString("etat");

                   verifcode p=new verifcode(id, code, pourcentage, etat);
                                
                File file = new File(projectPath + "\\src\\tn\\esprit\\vromvrom\\resources\\image\\" + p.getCode()+ ".jpg");
                System.out.println(file.toURI().toString());
                Image image = new Image(file.toURI().toString());
                
                ImageView imageView =new ImageView(image);
                imageView.setImage(image);
                imageView.setFitHeight(100);
                imageView.setFitWidth(100);

                p.setImgQR(imageView);                

                data.add(p);
            }
        } catch (Exception e) {
                //Logger.getLogger(tab)
        }
            idT.setCellValueFactory(new PropertyValueFactory<>("id_code"));
            codeT.setCellValueFactory(new PropertyValueFactory<>("code"));
            pourcentageT.setCellValueFactory(new PropertyValueFactory<>("pourcentage"));
            etatT.setCellValueFactory(new PropertyValueFactory<>("Etat"));
            imageqrT.setCellValueFactory(new PropertyValueFactory<>("ImgQR"));
            tableCode.setItems(data);
            RechercheAV();

    }
    public void RechercheAV(){
                // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<verifcode> filteredData = new FilteredList<>(data, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		recherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(verifc -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (verifc.getCode().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (String.valueOf(verifc.getPourcentage()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<verifcode> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tableCode.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tableCode.setItems(sortedData);
    }
    

    
        public static String projectPath = System.getProperty("user.dir").replace("\\", "/");
    private void QRcode(String CodePromo) throws FileNotFoundException, IOException {
        String contenue = "Code : " + CodePromo+ "\n"; 
        ByteArrayOutputStream out = QRCode.from(contenue).to(net.glxn.qrgen.image.ImageType.JPG).stream();
        File f = new File(projectPath + "\\src\\tn\\esprit\\vromvrom\\resources\\image\\" + CodePromo+ ".jpg");
        System.out.println(f.getPath());
        FileOutputStream fos = new FileOutputStream(f); //creation du fichier de sortie
        fos.write(out.toByteArray()); //ecrire le fichier du sortie converter
        fos.flush(); // creation final
        Image image = new Image(f.toURI().toString());
        codeQR.setImage(image);


     }
     static String RandGeneratedStr(int l)

     {

     // a list of characters to choose from in form of a string

     String AlphaNumericStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz0123456789";

     // creating a StringBuffer size of AlphaNumericStr

     StringBuilder s = new StringBuilder(l);

     int i;

     for ( i=0; i<l; i++) {

       //generating a random number using math.random()

       int ch = (int)(AlphaNumericStr.length() * Math.random());

       //adding Random character one by one at the end of s

       s.append(AlphaNumericStr.charAt(ch));

      }

        return s.toString();

     }
    @FXML
    private void Generate(ActionEvent event) throws IOException {
        
        String randomString = RandGeneratedStr(7);
        QRcode(randomString);
        Codelabel.setText(randomString);
        
        
    }

    @FXML
    private void Supprimer(ActionEvent event) throws SQLException {
        
             tableCode.setItems(data);

             ObservableList<verifcode> allCode,SingleCode ;
             allCode=tableCode.getItems();
             SingleCode=tableCode.getSelectionModel().getSelectedItems();
             verifcode A = SingleCode.get(0);
             sv.delete(A.getId_code());
             SingleCode.forEach(allCode::remove);
             Aff();
            TrayNotification tray = new TrayNotification();
            tray.setTitle("Supprimer");
            tray.setMessage("Supprimé avec succès");
            tray.setNotificationType(NotificationType.WARNING);
            tray.showAndWait();

    }

    
}