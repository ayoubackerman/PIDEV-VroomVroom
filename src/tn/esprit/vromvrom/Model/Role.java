/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.vromvrom.Model;

/**
 *
 * @author MediaCenter Zaghouan
 */
public class Role {
    
       private int id_role;
    private String role;

    public Role(int id_role, String role) {
        this.id_role = id_role;
        this.role = role;
    }
    

    public Role(String role) {
        this.role = role;
    }

    public Role() {
    }

    public int getId_role() {
        return id_role;
    }

    public void setId_role(int id_role) {
        this.id_role = id_role;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "Role{" + "id_role=" + id_role + ", role=" + role + '}';
    }

    public void setRole(String role) {
        this.role = role;
    }

   
    
}
