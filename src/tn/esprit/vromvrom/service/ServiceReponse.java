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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.esprit.vromvrom.Database.Database;
import tn.esprit.vromvrom.Model.Reclamation;
import tn.esprit.vromvrom.Model.Reponse;
import tn.esprit.vromvrom.Model.Role;
import tn.esprit.vromvrom.Model.User;

/**
 *
 * @author ASUS
 */
public class ServiceReponse implements IServiceReponse{
      private Connection cnx;

    public ServiceReponse(){
        cnx = Database.getInstance().getCnx();
    }

    @Override
public void ajouter(Reponse t) throws SQLException {
    // Check if the id_reclamation value exists in the reclamation table
    String countQuery = "SELECT COUNT(*) FROM reclamation WHERE id_reclamation = ?";
    try (PreparedStatement countStmt = cnx.prepareStatement(countQuery)) {
        countStmt.setInt(1, t.getId_reclamation().getId_reclamation());
        ResultSet rs = countStmt.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        if (count == 0) {
            // Insert a new row into the reclamation table with the id_reclamation value
            String insertQuery = "INSERT INTO reclamation (id_reclamation) VALUES (?)";
            try (PreparedStatement insertStmt = cnx.prepareStatement(insertQuery)) {
                insertStmt.setInt(1, t.getId_reclamation().getId_reclamation());
                insertStmt.executeUpdate();
            }
        }

        // Insert the row into the reponse table
        String insertQuery = "INSERT INTO reponse (id_reclamation, id_user) VALUES (?, ?)";
        try (PreparedStatement insertStmt = cnx.prepareStatement(insertQuery)) {
            insertStmt.setInt(1, t.getId_reclamation().getId_reclamation());
            insertStmt.setInt(2, t.getId_user().getId_user());
            insertStmt.executeUpdate();
        }
    }
}






    
    


    @Override
public boolean delete(Reponse t) throws SQLException {
    Statement ste = cnx.createStatement();
    if (search(t) == true) {
        String requeteDelete = "DELETE  FROM reponse  WHERE id_reclamation='" + t.getId_reclamation().getId_reclamation() + "'";
        ste.executeUpdate(requeteDelete);
        return true;
    } else {
        System.out.println("La reclamation n'existe pas");
        return false;
    }
}

   

    @Override
    public boolean update(Reponse t) throws SQLException {
          try {
        String req = "UPDATE `reponse` SET `reponse`='"
                + t.getReponse()+"' WHERE id_reclamation='" + t.getId_reclamation().getId_reclamation() + "'";

                
        Statement ps = cnx.createStatement();
        ps.executeUpdate(req);
        return true;
    } catch (SQLException ex) {
        Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        return false;
    }

}
    
    
     public void ResolutionREC(Reponse t) {
        try {
            String req = "Update reclamation set resolution = 'bien resolu' WHERE id_reclamation='" + t.getId_reclamation() + "'";
            PreparedStatement ps = cnx.prepareStatement(req);
            int value_update = ps.executeUpdate();
            if (value_update > 0) {
                System.out.println(" la suppresion de l reponse   a ete effectuer avec sucess");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceReponse.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    @Override
    public boolean search(Reponse t) throws SQLException {
      Statement ste= cnx.createStatement();
    ResultSet rs=ste.executeQuery("select * from reponse");
    boolean ok=false; 
    while (rs.next()&&(ok==false)) {         
        if (t.getId_reclamation().getId_reclamation() == rs.getInt(2))
             ok=true;
     }
     return ok;    }

    @Override
    public List<Reponse> readAll() throws SQLException {
          return null;
//        ServiceReponse r = new ServiceReponse();
//   List<Reponse> arr=new ArrayList<>();
//    Statement ste= cnx.createStatement();
//    ResultSet rs=ste.executeQuery("select * from reponse");
//     while (rs.next()) {                
//               int id_reponse=rs.getInt(1);
//               int id_reclamation=rs.getInt(2);
//             User id_user=rs.getInt("id_user");
//               String reponse=rs.getString(4);
//               String temps=rs.getString(5);
//               
//               Reponse p =new Reponse(id_reponse,id_reclamation,reponse);
//     arr.add(p);
//     }
//    return arr;
    }

    public Reponse SelectReponse(int id) {
         Reponse r = new Reponse();
        
        ServiceReponse ro =new ServiceReponse();
        String req = "SELECT * FROM reponse where id_reclamation='"+id+"'";
        
        try {
            PreparedStatement ps = cnx.prepareStatement(req);

        ResultSet rs = ps.executeQuery(req);
            
            while(rs.next()){           
                 User user = new User(rs.getInt("id_user"));
                 Reclamation reclamation =new Reclamation(rs.getInt("id_reclamation"));
               r = new Reponse(rs.getInt("id_reponse"),reclamation,user,rs.getString("reponse"),rs.getString("temps"));

            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReponse .class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

   
   

   
}
