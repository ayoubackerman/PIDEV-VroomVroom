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
 */
public interface IServiceRole<Role> {
     void ajouter(Role t) throws SQLException;
    boolean delete(Role t) throws SQLException;
    boolean update(Role t) throws SQLException;
    public boolean search(Role t) throws SQLException;
    
    public List<Role> readAll() throws SQLException;
    
}
