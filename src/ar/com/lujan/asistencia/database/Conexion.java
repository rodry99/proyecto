/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.lujan.asistencia.database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author impztz
 */
public class Conexion {
    
  	private  static String DB_DRIVER_CLASS;
	private  static String DB_URL;
	private  static String DB_USERNAME;
	private  static String DB_PASSWORD;
        
        
    static {
        Properties props = new Properties();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("./config/db.properties");
            props.load(fis);
            DB_DRIVER_CLASS = props.getProperty("DB_DRIVER_CLASS");
            DB_URL = props.getProperty("DB_URL");
            DB_USERNAME = props.getProperty("DB_USERNAME");
            DB_PASSWORD = props.getProperty("DB_PASSWORD");
            Class.forName(DB_DRIVER_CLASS);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }




    
    public static Connection getConexion() {
        
        Connection con = null;
        String cadenaConexion = DB_URL;
        
        try {
            con = DriverManager.getConnection(cadenaConexion, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException ex) {
            con = null;
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
     
        return con;
    }
    
    
    
}
