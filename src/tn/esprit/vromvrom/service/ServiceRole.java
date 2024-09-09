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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tn.esprit.vromvrom.Database.Database;
import tn.esprit.vromvrom.Model.Role;

/**
 *
 * @author MediaCenter Zaghouan
 */
public class ServiceRole implements IServiceRole<Role> {
      private Connection cnx;

    public ServiceRole(){
        cnx = Database.getInstance().getCnx();
    }

    @Override
    public void ajouter(Role t) throws SQLException {
     Statement ste =  cnx.createStatement();
        
         String requeteInsert = "INSERT INTO `role` (id_role, `role`) VALUES (NULL, '" + t.getRole()+"');";
        ste.executeUpdate(requeteInsert);    }

    @Override
    public boolean delete(Role t) throws SQLException {
         Statement ste =  cnx.createStatement();
 if (search(t)==true){
         ste = cnx.createStatement();
         String requeteDelete ="DELETE FROM role WHERE id_role="+ t.getId_role()+"";
         ste.executeUpdate(requeteDelete);}
         else{
           System.out.println("Le Role n'existe pas");
        }
         return true;  
    }

    @Override
    public boolean update(Role t) throws SQLException {
 if (search(t)==true){
        PreparedStatement pre=cnx.prepareStatement("UPDATE `role` SET `id_role` ='"+ t.getId_role() +"' , role = '"+ t.getRole() +"' WHERE `id_role`= '" + t.getId_role() );
        pre.setInt(1,t.getId_role());
        pre.setString(2,t.getRole());
        pre.executeUpdate();
        return true;
 }
          else{
           System.out.println("L'utulisateur n'existe pas");
           return true;
        }    }

    @Override
    public boolean search(Role t) throws SQLException {
  Statement ste= cnx.createStatement();
    ResultSet rs=ste.executeQuery("select * from role");
    boolean ok=false; 
    while (rs.next()&&(ok==false)) {         
         if (rs.getInt(1)==t.getId_role())
             ok=true;
     }
     return ok;    }

    @Override
    public List<Role> readAll() throws SQLException {
        List<Role> arr=new ArrayList<>();
        Statement ste = cnx.createStatement();
        ResultSet rs=ste.executeQuery("select * from role");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String role=rs.getString(2);
              
               
               Role r=new Role(id,role);
     arr.add(r);
     }
    return arr;
    }
    
    public static ObservableList<Role> RecupBase(){
             
    ObservableList<Role> list = FXCollections.observableArrayList();
    
       java.sql.Connection cnx;
     cnx = Database.getInstance().getCnx();
          String sql = "select *from role";
    try {
       
        PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);

    ResultSet R = st.executeQuery();
    while (R.next()){
      Role r =new Role();
     r.setId_role((R.getInt(1)));
     r.setRole(R.getString(2));
    
     
      list.add(r);
    }
    }catch (SQLException ex){
    ex.getMessage(); 
    } 
    return list;
    }
    
    
     public Role SelectRole(int id){
        Role r = new Role();
        String req = "SELECT * FROM role where id_role ="+id+"";
        
        try {
            PreparedStatement ps = cnx.prepareStatement(req);

        ResultSet rs = ps.executeQuery(req);
            
            while(rs.next()){           
                 
                r = new Role(rs.getInt("id_role"), rs.getString("role"));

            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceRole .class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
    
    
}
