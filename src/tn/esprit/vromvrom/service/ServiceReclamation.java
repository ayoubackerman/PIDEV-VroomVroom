/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.vromvrom.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.esprit.vromvrom.Database.Database;
import tn.esprit.vromvrom.Model.Reclamation;
import tn.esprit.vromvrom.Model.User;


/**
 *
 * @author MediaCenter Zaghouan
 */
public class ServiceReclamation implements IServiceReclamation<Reclamation>{
    
     private Connection cnx;

    public ServiceReclamation(){
        cnx = Database.getInstance().getCnx();
    }

    @Override
    public void ajouter(Reclamation t) throws SQLException {
       Statement ste =  cnx.createStatement();

        String requeteInsert = "INSERT INTO `reclamation`( `id_user`,`reclamation`,`type_reclamation`) VALUES ('" + t.getId_user().getId_user()+"' , '" + t.getReclamation()+"'  ,'" + t.getType_reclamation()+"' );";
        ste.executeUpdate(requeteInsert);
    }

    @Override
    public boolean delete(Reclamation t) throws SQLException {
         Statement ste =  cnx.createStatement();
 if (search(t)==true){
         ste = cnx.createStatement();
         String requeteDelete ="DELETE FROM reclamation WHERE id_reclamation="+ t.getId_reclamation()+"";
         ste.executeUpdate(requeteDelete);}
         else{
           System.out.println("La reclamation n'existe pas");
        }
         return true;  
    }

     @Override
  public boolean search(Reclamation t) throws SQLException {
  Statement ste= cnx.createStatement();
    ResultSet rs=ste.executeQuery("select * from reclamation");
    boolean ok=false; 
    while (rs.next()&&(ok==false)) {         
         if (rs.getInt(1)==t.getId_reclamation())
             ok=true;
     }
     return ok;    }

    @Override
 public boolean update(Reclamation t) throws SQLException {
    try {
        String req = "UPDATE `reclamation` SET `reclamation`='"
                + t.getReclamation()+ "', `type_reclamation`='"
                + t.getType_reclamation()+ "' WHERE temps='" + t.getTime() + "'";

                
        Statement ps = cnx.createStatement();
        ps.executeUpdate(req);
        return true;
    } catch (SQLException ex) {
        Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        return false;
    }
}

    @Override
    public List<Reclamation> readAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     public Reclamation SelectReclamation(int id){
          
        Reclamation r = new Reclamation();
        
        ServiceUser ro =new ServiceUser();
        String req = "SELECT * FROM reclamation where id_reclamation ='"+ id +"'";
        
        try {
            PreparedStatement ps = cnx.prepareStatement(req);

        ResultSet rs = ps.executeQuery(req);
            
            while(rs.next()){           
               User user = new User(rs.getInt("id_user"));
               r = new Reclamation(rs.getInt("id_reclamation"),user ,rs.getString("reclamation"),rs.getString("resolution"),rs.getString("type_reclamation"),rs.getString("temps"));

            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
    
}
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package tn.esprit.vromvrom.service;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import tn.esprit.vromvrom.Database.Database;
//import tn.esprit.vromvrom.Model.Role;
//import tn.esprit.vromvrom.Model.User;
//import tn.esprit.vromvrom.Model.Voiture;
//
///**
// *
// * @author mehdi
// */
//public class ServiceVoiture implements IServiceVoiture{
// private Connection cnx;
//
//    public ServiceVoiture(){
//        cnx = Database.getInstance().getCnx();
//    }
//
//    @Override
//    public void ajouter(Voiture t) throws SQLException {
//        Statement ste =  cnx.createStatement();
//        String requeteInsert = "INSERT INTO Voiture (id_voiture,`id_user`, modele,`marque`,`matricule`,`image`) VALUES (NULL, '" + t.getUser().getId()+"' , '" + t.getModele()+"' ,'" + t.getMarque()+"' ,'" + t.getMatricule()+"' ,'" + t.getImg()+"');";
//        ste.executeUpdate(requeteInsert);   
//    }
//
//    @Override
//    public boolean delete(Voiture t) throws SQLException {
//        if (search(t) == true){
//            Statement ste = cnx.createStatement();
//            String requeteDelete ="DELETE FROM voiture WHERE id_voiture="+ t.getId()+"";
//            ste.executeUpdate(requeteDelete);}
//        else{
//           System.out.println("L'utulisateur n'existe pas");
//        }
//         return true;
//    }
//
//    @Override
//    public boolean update(Voiture t) throws SQLException {
//        if (search(t)==true){
//            PreparedStatement pre=cnx.prepareStatement("UPDATE voiture SET id_user = ?, modele = ?, marque = ?, matricule = ?, image = ? WHERE `id`=? ;");
//            pre.setInt(1,t.getUser().getId());
//            pre.setString(1,t.getModele());
//            pre.setString(2,t.getMarque());
//            pre.setInt(2,t.getMatricule());
//            pre.setString(3,t.getImg());
//            pre.setInt(3,t.getId());
//            pre.executeUpdate();
//            return true;
//        }
//        else{
//           System.out.println("L'utulisateur n'existe pas");
//           return true;
//        } 
//    }
//
//    @Override
//    public boolean search(Voiture t) throws SQLException {
//        PreparedStatement pre= cnx.prepareStatement("select * from voiture where id_voiture = ?");
//        pre.setInt(1, t.getId());
//        return pre.executeQuery().first();   
//    }
//
//    @Override
//    public ObservableList<Voiture> readAll() throws SQLException {
//        ObservableList<Voiture> temp = FXCollections.observableArrayList();
//
// 
//        PreparedStatement ps = cnx.prepareStatement("SELECT voiture.id_voiture, voiture.modele, voiture.marque, voiture.matricule, voiture.image, user.id_user  as id_user, "
//                                                    + "user.nom, user.prenom, user.mail, user.Nomd, user.mdp, user.statuts, role.id_role as id_role, role  FROM voiture "
//                                                    + "join user on voiture.id_user = user.id_user "
//                                                    + "join role on user.id_role = role.id_role");
//        ResultSet rs = ps.executeQuery();
//
//        while (rs.next()){
//
//            Voiture v = new Voiture();
//            User user = new User();
//            Role role = new Role();
//            
//            role.setId(rs.getInt("id_role"));
//            role.setRole(rs.getString("role"));
//            user.setRole(role);
//            user.setId(rs.getInt("id_user"));
//            user.setMail(rs.getString("mail"));
//            user.setMdp(rs.getString("mdp"));
//            user.setNom(rs.getString("nom"));
//            user.setNomd(rs.getString("Nomd"));
//            user.setPrenom(rs.getString("prenom"));
//            user.setStatus(rs.getString("statuts"));
//            v.setUser(user);
//            v.setId(rs.getInt("id_voiture"));
//            v.setModele(rs.getString("modele"));
//            v.setMarque(rs.getString("marque"));
//            v.setMatricule(rs.getInt("matricule"));
//            v.setImg(rs.getString("image"));
//            
//
//            temp.add(v);
//
//        }
//
//
//        return temp;
//    }
//    
//}