/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.lujan.asistencia.database;


import ar.com.lujan.asistencia.modelo.CursoDias;
import java.awt.PageAttributes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author carlos cerrizuela
 */
public class CursoDiaDAO {
    
  private static final String SELECTCURSODIA = 
            "SELECT  ID_CURSO, DIA  FROM CURSO_DIAS";
    
    private static final String INSERTCURSODIA = 
            "INSERT INTO CURSO_DIAS(ID_CURSO_DIA, ID_CURSO, DIA)" + 
            "VALUES(?,?,?)";
    
    private static final String UPDATECURSODIA = 
            "UPDATE CURSO_DIAS " + 
            "SET DIA = ? " +
            "WHERE ID_CURSO = ?";
    
    private static final String DELETECURSODIA = "DELETE FROM CURSO_DIAS WHERE ID_CURSO = ?";

    private static final String MAXCURSODIA = "SELECT ISNULL(MAX(ID_CURSO_DIA),0) + 1 AS MAXID FROM CURSO_DIAS ";

    
    
     public ArrayList<CursoDias> getCursoDia(){


        ArrayList<CursoDias> cursodia = new ArrayList<CursoDias>();

        try {

             Connection con = Conexion.getConexion();
             String query = SELECTCURSODIA;
            PreparedStatement ps;
            ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                CursoDias c = new CursoDias();
                //c.setIdCursoDia(rs.getInt("ID_CURSO_DIA"));
                c.setIdCurso(rs.getInt("ID_CURSO"));
                c.setDia(rs.getInt("DIA"));
                cursodia.add(c);
                 
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CursoDiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cursodia;

    }
    
     public int insertCursodia(CursoDias c){
        int retorno = 0;
        Connection con = null;
        try {
                con = Conexion.getConexion();
                con.setAutoCommit(false);
                int maxID = this.getMaxIDCURSODIA(con);      
                PreparedStatement ps = con.prepareStatement(INSERTCURSODIA);
                ps.setInt(1,maxID);
                ps.setInt(2,c.getIdCurso());
                ps.setInt(3,c.getDia());
                
                retorno = ps.executeUpdate();
                con.commit();
                con.setAutoCommit(true);
                ps.close();
                con.close();

       
        } catch (SQLException ex) {
            try {
                Logger.getLogger(CursoDiaDAO.class.getName()).log(Level.SEVERE, null, ex);
                con.rollback();
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException ex1) {
                Logger.getLogger(CursoDiaDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return retorno;
    }
     
     public int updateCursodia(CursoDias c){
        int retorno = 0;
        Connection con = null;
        try {
                con = Conexion.getConexion();
                PreparedStatement ps = con.prepareStatement(UPDATECURSODIA);
                
                
                ps.setInt(1,c.getDia());
                ps.setInt(2,c.getIdCurso());                
               // ps.setInt(3,c.getIdCursoDia());
                
                retorno = ps.executeUpdate();
                ps.close();
                con.close();

        } catch (SQLException ex) {
            try {
                Logger.getLogger(CursoDiaDAO.class.getName()).log(Level.SEVERE, null, ex);
                con.rollback();
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException ex1) {
                Logger.getLogger(ProfesorDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return retorno;
    }
    
        public int deleteCursoDia(CursoDias c){
        int retorno = -1;
        Connection con = null;
        try {
            con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(DELETECURSODIA);
            ps.setInt(1,c.getIdCurso());
            retorno = ps.executeUpdate();//execute update se usa para DML
            ps.close();
            con.close();
        }
        catch  (SQLException ex) {
            Logger.getLogger(CursoDiaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        
        
        return retorno;
    }
    private int getMaxIDCURSODIA(Connection con) throws SQLException{
        int retorno = -1;
      
        PreparedStatement ps = con.prepareStatement(MAXCURSODIA);
        ResultSet rs = ps.executeQuery();
        rs.next();
        retorno = rs.getInt("MAXID");
        rs.close();
        ps.close();   
        
        return retorno;
    }
}

   
    
    
    
    
    
    
    
    
    
    
    

