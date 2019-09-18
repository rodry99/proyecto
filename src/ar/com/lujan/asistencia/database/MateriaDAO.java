/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.lujan.asistencia.database;

import ar.com.lujan.asistencia.modelo.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author impztz
 */
public class MateriaDAO {
    
    private static final String LISTAMATERIAS = 
            "SELECT M.ID_MATERIA, M.DESCRIPCION " +
            "FROM MATERIA M ";

    private static final String WHEREBYDESCRIPCIONDISTINTOID = 
            "WHERE M.ID_MATERIA <> ? AND UPPER(M.DESCRIPCION) = UPPER(?) ";
   
    private static final String ORDERBYMATERIAS = 
            "ORDER BY M.DESCRIPCION ";
    
    private static final String NOSENSITIVO = 
            "COLLATE Traditional_Spanish_ci_ai";
    
    private static final String INSERTARMATERIA = 
            "INSERT INTO MATERIA values(?,?)";
    
    private static final String BORRARMATERIA =
            "DELETE FROM MATERIA WHERE ID_MATERIA = ?";
    
    private static final String MAXMATERIA = "SELECT ISNULL(MAX(ID_MATERIA),0) + 1 AS MAXID FROM MATERIA ";
    
    private static final String ACTUALIZARMATERIA = "UPDATE MATERIA SET DESCRIPCION = ? WHERE ID_MATERIA = ?";
    

    public ArrayList<Materia> getMaterias(){
        
        ArrayList<Materia> materias = new ArrayList<Materia>();
        try {
            
            Connection con = Conexion.getConexion();
            
            PreparedStatement ps = con.prepareStatement(LISTAMATERIAS + ORDERBYMATERIAS);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Materia m = new Materia();
                m.setIdMateria( rs.getInt("ID_MATERIA") );
                m.setDescripcion(rs.getString("DESCRIPCION"));
                materias.add(m);
            }
            
            rs.close();
            ps.close();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(MateriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return materias;
    }

    public ArrayList<Materia> getMateriasByDescripcion(Materia ma){
        
        ArrayList<Materia> materias = new ArrayList<Materia>();
        try {
            
            Connection con = Conexion.getConexion();
            
            PreparedStatement ps = con.prepareStatement(LISTAMATERIAS  + WHEREBYDESCRIPCIONDISTINTOID + NOSENSITIVO);
            ps.setInt(1, ma.getIdMateria());
            ps.setString(2, ma.getDescripcion());
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Materia m = new Materia();
                m.setIdMateria( rs.getInt("ID_MATERIA") );
                m.setDescripcion(rs.getString("DESCRIPCION"));
                materias.add(m);
            }
            
            rs.close();
            ps.close();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(MateriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return materias;
    }

    public int insertarMateria(Materia m){
        int retorno = 0;
        Connection con = null;
        try {
                con = Conexion.getConexion();
                con.setAutoCommit(false);
                int maxID = this.getMaxIdMateria(con);
                      
                PreparedStatement ps = con.prepareStatement(INSERTARMATERIA );
                ps.setInt(1,maxID );
                ps.setString(2, m.getDescripcion());
                
                retorno = ps.executeUpdate();
                con.commit();
                con.setAutoCommit(true);
                ps.close();
                con.close();

        } catch (SQLException ex) {
            try {
                Logger.getLogger(MateriaDAO.class.getName()).log(Level.SEVERE, null, ex);
                con.rollback();
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException ex1) {
                Logger.getLogger(MateriaDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return retorno;
    }


    private int getMaxIdMateria(Connection con) throws SQLException{
        int retorno = -1;
      
        PreparedStatement ps = con.prepareStatement(MAXMATERIA );
        ResultSet rs = ps.executeQuery();
        rs.next();
        retorno = rs.getInt("MAXID");
        rs.close();
        ps.close();   
        
        return retorno;
    }
    
    
    public int BorrarMateria(Materia m){
        int retorno = 0;
        Connection con = null;

        try {
                con = Conexion.getConexion();
                con.setAutoCommit(false);
                      
                PreparedStatement ps = con.prepareStatement(BORRARMATERIA );
                ps.setInt(1,m.getIdMateria());
                retorno = ps.executeUpdate();
                con.commit();
                con.setAutoCommit(true);
                ps.close();
                con.close();

        } catch (SQLException ex) {
            try {
                Logger.getLogger(MateriaDAO.class.getName()).log(Level.SEVERE, null, ex);
                con.rollback();
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException ex1) {
                Logger.getLogger(MateriaDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }

        return retorno;
    }
    
    
    public int ActualizarMateria(Materia m){
        int retorno = 0;
        Connection con = null;

        try {
                con = Conexion.getConexion();
                con.setAutoCommit(false);
                      
                PreparedStatement ps = con.prepareStatement(ACTUALIZARMATERIA );
                ps.setString(1,m.getDescripcion());
                ps.setInt(2,m.getIdMateria());
                retorno = ps.executeUpdate();
                con.commit();
                con.setAutoCommit(true);
                ps.close();
                con.close();

        } catch (SQLException ex) {
            try {
                Logger.getLogger(MateriaDAO.class.getName()).log(Level.SEVERE, null, ex);
                con.rollback();
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException ex1) {
                Logger.getLogger(MateriaDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        
        
        return retorno;
        }
    
}
