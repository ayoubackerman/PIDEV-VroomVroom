/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.vromvrom.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import tn.esprit.vromvrom.Database.Database;
import tn.esprit.vromvrom.Model.Role;
import tn.esprit.vromvrom.Model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author MediaCenter Zaghouan
 */
public class ServiceUser implements IServiceUser<User> {
     
    private Connection cnx;

    public ServiceUser(){
        cnx = Database.getInstance().getCnx();
    }

    
    
    @Override
    public void ajouter(User t) throws SQLException {
        
        Statement ste =  cnx.createStatement();
        
        ServiceRole r = new ServiceRole();
        
        Role rr = new Role();
        rr = r.SelectRole(t.getId_role().getId_role());

//       int id = t.getId_role().getId_role();
//        System.out.println(id);
         String requeteInsert = "INSERT INTO user (id_user, id_role,nom,prenom,mail,nomd,mdp) VALUES (NULL,'" + rr + "','" + t.getNom() + "', '" + t.getPrenom() + "', '" + t.getMail()+"', '" + t.getNomd()+"', '" + t.getMdp()+"');";
//           String j = "INSERT INTO user (id_user, id_role, nom, prenom, mail, nomd, mdp) SELECT NULL,id_role,nom,prenom, mail,nomd, mdp FROM user r JOIN role u ON r.id_role = u.id_role";
    
//Role role = r.SelectRole(t.getId_role().getId_role());
//int id=role.getId_role();
//t.setId_role(r.SelectRole(id));
ste.executeUpdate(requeteInsert);
//t.setId_role(r.SelectRole(id));

    }
    

    @Override
    public boolean delete(User t) throws SQLException {
           if (search(t)==true){
         Statement ste =(Statement) cnx.createStatement();
         String requeteDelete ="DELETE FROM user WHERE id_user="+ t.getId_user();
         ste.executeUpdate(requeteDelete);}
         else{
           System.out.println("L'utulisateur n'existe pas");
        }
         return true;
    }

    @Override
    public boolean update(User t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean search(User t) throws SQLException {
 Statement ste= cnx.createStatement();
    ResultSet rs=ste.executeQuery("select * from user");
    boolean ok=false; 
    while (rs.next()&&(ok==false)) {         
         if (rs.getInt(1)==t.getId_user())
             ok=true;
     }
     return ok;      }

    @Override
    public List<User> readAll() throws SQLException {
       ServiceRole r = new ServiceRole();
   List<User> arr=new ArrayList<>();
    Statement ste= cnx.createStatement();
    ResultSet rs=ste.executeQuery("select * from user");
     while (rs.next()) {                
               int id_user=rs.getInt(1);
               Role role=r.SelectRole(rs.getInt(2));
               String nom=rs.getString(3);
               String prenom=rs.getString(4);
               String mail=rs.getString(5);
               String nomd=rs.getString(6);
               String mdp=rs.getString(7);
               String status=rs.getString(8);
               Long num =rs.getLong(10);
               
               User p=new User(id_user,role,nom,prenom,mail,nomd,mdp,status,num);
     arr.add(p);
     }
    return arr;
    }

    @Override
    public User login(String email, String mdpasse, String numdu) throws SQLException {

        ServiceRole ro =new ServiceRole();
         
      User p=new User();
      Role r=new Role();
        String req=("select * from user WHERE mail=? or Nomd=? and mdp=? ");
        PreparedStatement pre = cnx.prepareStatement(req);
         pre.setString(1, email);
         pre.setString(2, numdu);
         pre.setString(3, encryptThisString(mdpasse));
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            int id = rs.getInt(1);
            Role role = ro.SelectRole(rs.getInt(2));
            String nom = rs.getString(3);
            String prenom = rs.getString(4);
            String mail = rs.getString(5);
            String nomd = rs.getString(6);
            String mdp = rs.getString(7);
            String statuts = rs.getString(8);
            String Image = rs.getString(9);
            int num=rs.getInt(10);
            
            
            
            p = new User(id, role, nom, prenom, mail, nomd, mdp,statuts ,Image ,num);
            User.connecte=new User(id, role, nom, prenom, mail, nomd, mdp,statuts,Image,num);
        }
        return p;
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
     
        public int RecupTotal(){
             
             int total = 0;
    
       java.sql.Connection cnx;
     cnx = Database.getInstance().getCnx();
          String sql = "SELECT COUNT(*) AS total_users FROM user";
    try {
       
        PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);

    ResultSet R = st.executeQuery();
    while (R.next()){
      
     
    total = R.getInt(1);
    
     
      
    }
    }catch (SQLException ex){
    ex.getMessage(); 
    } 
    return total;
    }
        
          public int RecupTotalChauf(){
             
             int total = 0;
    
       java.sql.Connection cnx;
     cnx = Database.getInstance().getCnx();
          String sql = "SELECT COUNT(*) AS total_users FROM user where id_role= 2";
    try {
       
        PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);

    ResultSet R = st.executeQuery();
    while (R.next()){
      
     
    total = R.getInt(1);
    
     
      
    }
    }catch (SQLException ex){
    ex.getMessage(); 
    } 
    return total;
    }
  public int RecupTotalClient(){
             
             int total = 0;
    
       java.sql.Connection cnx;
     cnx = Database.getInstance().getCnx();
          String sql = "SELECT COUNT(*) AS total_users FROM user where id_role = 3";
    try {
       
        PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);

    ResultSet R = st.executeQuery();
    while (R.next()){
      
     
    total = R.getInt(1);
    
     
      
    }
    }catch (SQLException ex){
    ex.getMessage(); 
    } 
    return total;
    }

     
       public static ObservableList<String> RecupCombo(){
             
             
    ObservableList<String> list = FXCollections.observableArrayList();
    
       java.sql.Connection cnx;
     cnx = Database.getInstance().getCnx();
          String sql = "SELECT role FROM `role`";
    try {
       
        PreparedStatement st = (PreparedStatement) cnx.prepareStatement(sql);

    ResultSet R = st.executeQuery();
    while (R.next()){
      
     
   String r = R.getString(1);
        System.out.println(r);
    
     
      list.add(r);
    }
    }catch (SQLException ex){
    ex.getMessage(); 
    } 
    return list;
    }
    
    
      public User SelectUser(int id){
          
        User r = new User();
        
        ServiceRole ro =new ServiceRole();
        String req = "SELECT * FROM `User` where id_user ="+id;
        
        try {
            PreparedStatement ps = cnx.prepareStatement(req);

        ResultSet rs = ps.executeQuery(req);
            
            while(rs.next()){           
                 
               r = new User(rs.getInt("id_user"),ro.SelectRole(rs.getInt("id_role")) ,rs.getString("nom"),rs.getString("prenom"),rs.getString("mail"),rs.getString("nomd"),rs.getString("mdp"),rs.getString("statuts"),rs.getLong("num"));

            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceRole .class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
            public  boolean VerifEmail(String email) 
    { 
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
                              
        Pattern pat = Pattern.compile(emailRegex); 
        if (email == null) 
            return false; 
        return pat.matcher(email).matches(); 
    } 
 //Verifier le nom d'utilisateur s'il existe ou pas 

            
            
  
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
       
         public boolean UNBAN(User t) throws SQLException {
           String s ="Actif" ;
        if (search(t)==true){
            PreparedStatement pre=cnx.prepareStatement("UPDATE user SET statuts ='"+s+"'  WHERE `id_user`='"+ t.getId_user() +"' ");
            
           
            pre.executeUpdate();
            return true;
        }
        else{
           System.out.println("L'ABRARS n'existe pas");
           return true;
        } 
        
        
    }
       
       public boolean BAN(User t) throws SQLException {
           String s ="Banned" ;
        if (search(t)==true){
            PreparedStatement pre=cnx.prepareStatement("UPDATE user SET statuts ='"+s+"'  WHERE `id_user`='"+ t.getId_user() +"' ");
            
           
            pre.executeUpdate();
            return true;
        }
        else{
           System.out.println("L'ABRARS n'existe pas");
           return true;
        } 
        
        
    }
     
    
    
}
