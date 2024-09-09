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
import tn.esprit.vromvrom.Model.verifcode;

/**
 *
 * @author user
 */
public class ServiceVerifcode implements IserviceSolde<verifcode>{

       
    Connection cnx;    
    private Statement ste;
    private PreparedStatement pst ;

     public ServiceVerifcode() {
         cnx = Database.getInstance().getCnx();
    }

    @Override
    public void ajouter(verifcode x) throws SQLException {
    try {
          
           String query="INSERT INTO verifcode(code ,pourcentage,etat  ) values(?,?,?)";
                PreparedStatement smt = cnx.prepareStatement(query);
                smt.setString(1, x.getCode());
                smt.setFloat(2, x.getPourcentage());
                smt.setString(3, x.getEtat());
                smt.executeUpdate();
                System.out.println("ajout avec succee");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
           
       }    
    }

    @Override
    public void delete(int x) throws SQLException {
   try {
       String query2="delete from verifcode where id_code=?";
                PreparedStatement smt = cnx.prepareStatement(query2);
                smt.setInt(1, x);
                smt.executeUpdate();
                System.out.println("suppression avec succee");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
    }

    @Override
    public void update(verifcode x) throws SQLException {
        try {
            String requete = " update verifcode set etat=? where id_code='"+x.getId_code()+"'"  ;
            pst = cnx.prepareStatement(requete);
            pst.setString(1,x.getEtat());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ServiceVerifcode.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean search(verifcode x) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<verifcode> readAll() throws SQLException {
        List<verifcode> arr=new ArrayList<>();
        ste=cnx.createStatement();
        ResultSet rs=ste.executeQuery("select * from verifcode");
         while (rs.next()) {                
                   int id=rs.getInt(1);
                   String code=rs.getString("code");
                   float pourcentage=rs.getFloat("pourcentage");
                   String etat=rs.getString("etat");

                   verifcode p=new verifcode(id, code, pourcentage, etat);
         arr.add(p);
         }
        return arr;  
    }
    
       public verifcode VerifyTheCode(String code) throws SQLException {
          verifcode a = null;
         String requete = " select* from verifcode  where code='"+code+"' " ;
         System.out.println(requete);
        try {
                   PreparedStatement ps = cnx.prepareStatement(requete);
                   ResultSet rs = ps.executeQuery() ;

            if (rs.next())
            {
                   int id=rs.getInt(1);
                   String codee=rs.getString("code");
                   float pourcentage=rs.getFloat("pourcentage");
                   String etat=rs.getString("etat");

                   a=new verifcode(id, codee, pourcentage, etat);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
    }

    
    
}