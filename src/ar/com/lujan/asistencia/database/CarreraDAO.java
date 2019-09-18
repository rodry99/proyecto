/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.lujan.asistencia.database;

import ar.com.lujan.asistencia.modelo.Carrera;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author impztz
 */
public class CarreraDAO {
    
    private static final String LISTACARRERAS = 
            "SELECT M.ID_CARRERA, M.DESCRIPCION " +
            "FROM CARRERA M ";
   
    private static final String WHEREBYDESCRIPCIONDISTINTOID = 
            "WHERE M.ID_CARRERA <> ? AND UPPER(M.DESCRIPCION) = UPPER(?) ";
   
    private static final String ORDERBYCARRERAS = 
            "ORDER BY M.DESCRIPCION";
    
    private static final String NOSENSITIVO = 
            "COLLATE Traditional_Spanish_ci_ai";
    
    private static final String INSERTARCARRERA = 
            "INSERT INTO CARRERA values(?,?)";
    
    private static final String BORRARCARRERA =
            "DELETE FROM CARRERA WHERE ID_CARRERA = ?";
    
    private static final String MAXCARRERA = "SELECT ISNULL(MAX(ID_CARRERA),0) + 1 AS MAXID FROM CARRERA ";
    
    private static final String ACTUALIZARCARRERA = "UPDATE CARRERA SET DESCRIPCION = ? WHERE ID_CARRERA = ?";
    

    public ArrayList<Carrera> getCarreras(){
        
        ArrayList<Carrera> carreras = new ArrayList<Carrera>();
        try {
            
            Connection con = Conexion.getConexion();
            
            PreparedStatement ps = con.prepareStatement(LISTACARRERAS + ORDERBYCARRERAS);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Carrera m = new Carrera();
                m.setIdCarrera( rs.getInt("ID_CARRERA") );
                m.setDescripcion(rs.getString("DESCRIPCION"));
                carreras.add(m);
            }
            
            rs.close();
            ps.close();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(CarreraDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return carreras;
    }

    
    public ArrayList<Carrera> getCarrerasByDescripcionDistintoId(Carrera c){
        
        ArrayList<Carrera> carreras = new ArrayList<Carrera>();
        try {
            
            Connection con = Conexion.getConexion();
            
            PreparedStatement ps = con.prepareStatement(LISTACARRERAS + WHEREBYDESCRIPCIONDISTINTOID + NOSENSITIVO);
            ps.setInt(1, c.getIdCarrera());
            ps.setString(2, c.getDescripcion());
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Carrera m = new Carrera();
                m.setIdCarrera( rs.getInt("ID_CARRERA") );
                m.setDescripcion(rs.getString("DESCRIPCION"));
                carreras.add(m);
            }
            
            rs.close();
            ps.close();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(CarreraDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return carreras;
    }

    
    public int insertarCarrera(Carrera c){
        int retorno = 0;
        Connection con = null;
        try {
                con = Conexion.getConexion();
                con.setAutoCommit(false);
                int maxID = this.getMaxIdCarrera(con);
                      
                PreparedStatement ps = con.prepareStatement(INSERTARCARRERA );
                ps.setInt(1,maxID );
                ps.setString(2, c.getDescripcion());
                
                retorno = ps.executeUpdate();
                con.commit();
                con.setAutoCommit(true);
                ps.close();
                con.close();

        } catch (SQLException ex) {
            try {
                Logger.getLogger(CarreraDAO.class.getName()).log(Level.SEVERE, null, ex);
                con.rollback();
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException ex1) {
                Logger.getLogger(CarreraDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return retorno;
    }


    private int getMaxIdCarrera(Connection con) throws SQLException{
        int retorno = -1;
      
        PreparedStatement ps = con.prepareStatement(MAXCARRERA );
        ResultSet rs = ps.executeQuery();
        rs.next();
        retorno = rs.getInt("MAXID");
        rs.close();
        ps.close();   
        
        return retorno;
    }
    
    
    public int BorrarCarrera(Carrera c){
        int retorno = 0;
        Connection con = null;

        try {
                con = Conexion.getConexion();
                con.setAutoCommit(false);
                      
                PreparedStatement ps = con.prepareStatement(BORRARCARRERA );
                ps.setInt(1,c.getIdCarrera());
                retorno = ps.executeUpdate();
                con.commit();
                con.setAutoCommit(true);
                ps.close();
                con.close();

        } catch (SQLException ex) {
            try {
                Logger.getLogger(CarreraDAO.class.getName()).log(Level.SEVERE, null, ex);
                con.rollback();
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException ex1) {
                Logger.getLogger(CarreraDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }

        return retorno;
    }
    
    
    public int ActualizarCarrera(Carrera c){
        int retorno = 0;
        Connection con = null;

        try {
                con = Conexion.getConexion();
                con.setAutoCommit(false);
                      
                PreparedStatement ps = con.prepareStatement(ACTUALIZARCARRERA );
                ps.setString(1,c.getDescripcion());
                ps.setInt(2,c.getIdCarrera());
                retorno = ps.executeUpdate();
                con.commit();
                con.setAutoCommit(true);
                ps.close();
                con.close();

        } catch (SQLException ex) {
            try {
                Logger.getLogger(CarreraDAO.class.getName()).log(Level.SEVERE, null, ex);
                con.rollback();
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException ex1) {
                Logger.getLogger(CarreraDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        
        
        return retorno;
        }
    
}
