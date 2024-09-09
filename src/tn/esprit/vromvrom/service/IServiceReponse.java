/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.vromvrom.service;

import java.sql.SQLException;
import java.util.List;
import tn.esprit.vromvrom.Model.Reponse;


/**
 *
 * @author ASUS
 */
public interface IServiceReponse {
   void ajouter(Reponse t) throws SQLException;
    boolean delete(Reponse t) throws SQLException;
    boolean update(Reponse t) throws SQLException;
    public boolean search(Reponse t) throws SQLException;
    public List<Reponse> readAll() throws SQLException;
   
}
