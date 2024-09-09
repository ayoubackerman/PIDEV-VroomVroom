/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.vromvrom.service;

import java.io.InputStream;
import java.sql.Blob;
import java.util.List;
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
import tn.esprit.vromvrom.Model.User;
import tn.esprit.vromvrom.Model.solde;
import tn.esprit.vromvrom.service.IserviceSolde;

/**
 *
 * @author Bedoui
 */
public class ServiceSolde implements IserviceSolde<solde>{

    Connection cnx;
     public ServiceSolde() {
         cnx = Database.getInstance().getCnx();
    }
    
    @Override
    public void ajouter(solde t) throws SQLException {
try {
          
           String query="INSERT INTO Solde(id_user ,montant  ) values(?,?)";
                PreparedStatement smt = cnx.prepareStatement(query);
                smt.setInt(1, t.getId_user());
                smt.setDouble(2, t.getMontant());
//                smt.setString(3, t.getDate());
                   //sm:t.setInt(4, t.getnom());
                      //smt.setInt(5, t.getprenom());
                smt.executeUpdate();
                System.out.println("ajout avec succee");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
           
       }    }
    public void supprimer(int t) {
   try {
       String query2="delete from Solde where id_user=?";
                PreparedStatement smt = cnx.prepareStatement(query2);
                smt.setInt(1, t);
                smt.executeUpdate();
                System.out.println("suppression avec succee");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
    }
    
    public void modifier(double m,int id) {
    try {
       String query2="update Solde set montant=? where id_paiement =?";
                PreparedStatement smt = cnx.prepareStatement(query2);
                
                smt.setDouble(1, m);
                smt.setInt(2, id);
                smt.executeUpdate();
                System.out.println("modification avec succee");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
             }
    }

    @Override
    public void delete(int t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(solde t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
   
    }

    @Override
    public boolean search(solde t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<solde> readAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public List<solde> find() {
    ArrayList l=new ArrayList(); 
        
        try {
       String query2="select * from solde";
                PreparedStatement smt = cnx.prepareStatement(query2);
                
                solde c;
                ResultSet rs= smt.executeQuery(query2);
                while(rs.next()){
                   c=new solde(rs.getInt("id_user"),rs.getInt("id_paiment"),rs.getDouble("montan"),rs.getString("date"),rs.getString("nom"),rs.getString("prenom"));
                   l.add(c);
                }
               //System.out.println(l);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }

        return l;
    }
    
    public List<solde> find(int i) {
        ArrayList l=new ArrayList(); 
        
        try {
       String query2="select * from solde where id_paiment="+i;
                PreparedStatement smt = cnx.prepareStatement(query2);
                solde c;
                ResultSet rs= smt.executeQuery(query2);
                while(rs.next()){
                   c=new solde(rs.getInt("id_user"),rs.getInt("id_paiment"),rs.getDouble("montan"),rs.getString("date"),rs.getString("nom"),rs.getString("prenom"));
                   l.add(c);
                }
               // System.out.println(l);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }
        //List<int> l = new ArrayList();

        return l;
    }
    
    public ObservableList<solde> find1(int s) {
        ObservableList<solde> l = FXCollections.observableArrayList(); 
        ServiceUser us = new ServiceUser();
        try {
       String query2="SELECT * FROM solde WHERE id_paiement="+s;
                PreparedStatement smt = cnx.prepareStatement(query2);
                // smt.setInt(1, s);
                solde c;
                ResultSet rs= smt.executeQuery(query2);
                while(rs.next()){
                    User u = us.SelectUser(rs.getInt(1));
                  c=new solde(rs.getInt("id_user"),rs.getInt("id_paiement"),rs.getDouble("montant"),rs.getString("date"),u.getNom(),u.getPrenom());
                   l.add(c);
                }
                System.out.println(l);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }
        //List<int> l = new ArrayList();

        return l;
    }
}