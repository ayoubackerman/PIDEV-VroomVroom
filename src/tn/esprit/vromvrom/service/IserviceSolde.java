/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tn.esprit.vromvrom.service;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author user
 */
public interface IserviceSolde<T> {
    void ajouter(T x) throws SQLException;
    void delete(int x) throws SQLException;
    void update(T x) throws SQLException;
    public boolean search(T x) throws SQLException;
    
    public List<T> readAll() throws SQLException;
    
    
}