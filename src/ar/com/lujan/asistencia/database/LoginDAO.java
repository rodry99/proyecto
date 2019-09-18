/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.lujan.asistencia.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucho
 */
public class LoginDAO {
    
    
        public int validarUsername(String username){
            int r = 0;
        try {
            Connection con = Conexion.getConexion();
            String query = "IF EXISTS(SELECT password FROM LOGIN WHERE username='" + username + "') SELECT 1 AS RETORNO ELSE SELECT 0 AS RETORNO";
            PreparedStatement ps;
            ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                r=rs.getInt("RETORNO");
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
    
                public String obtenerHash(String username){
            String hash = "";
        try {
            Connection con = Conexion.getConexion();
            String query = "SELECT password FROM login WHERE username like '"+ username + "'";
            PreparedStatement ps;
            ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                hash=rs.getString("password");
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hash;
    }
    
    
    
}
