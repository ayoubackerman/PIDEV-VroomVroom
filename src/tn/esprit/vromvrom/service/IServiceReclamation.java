/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.vromvrom.service;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author MediaCenter Zaghouan
 * @param <Reclamation>
 */
public interface IServiceReclamation<Reclamation> {
     void ajouter(Reclamation t) throws SQLException;
    boolean delete(Reclamation t) throws SQLException;
    boolean update(Reclamation t) throws SQLException;
    public boolean search(Reclamation t) throws SQLException;
    
    public List<Reclamation> readAll() throws SQLException;
    
}
