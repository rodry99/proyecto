/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.lujan.asistencia.database;

import ar.com.lujan.asistencia.modelo.Alumno;
import ar.com.lujan.asistencia.modelo.AsistenciaClase;
import ar.com.lujan.asistencia.modelo.Clase;
import ar.com.lujan.asistencia.modelo.Curso;
import ar.com.lujan.asistencia.modelo.CursoAlumno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Papa
 */
public class AsistenciaClaseDAO {

    private static final String LISTAASISTENCIAXCLASE = 
            "SELECT A.ID_ASISTENCIA, A.ID_CURSO_ALUMNO, A.ID_CLASE, A.ASISTENCIA, " +
            "CA.ID_CURSO, CA.ID_ALUMNO, CA.ESTADO AS ESTADOCURSOALUMNO, " +
            "AL.APELLIDO, AL.NOMBRE, AL.DNI, AL.GENERO, AL.FECHA_DE_NACIMIENTO, AL.EMAIL, AL.ESTADO AS ESTADOALUMNO " +
            "FROM ASISTENCIA A " +
            "INNER JOIN CURSO_ALUMNOS CA ON CA.ID_CURSO_ALUMNO = A.ID_CURSO_ALUMNO " +
            "INNER JOIN ALUMNO AL ON AL.ID_ALUMNO = CA.ID_ALUMNO ";

    private static final String WHEREBYASISTENCIA = 
            "WHERE A.ID_CLASE = ? ";
   
    private static final String ORDERBYASISTENCIAS = 
            "ORDER BY AL.APELLIDO, AL.NOMBRE ";
    
    private static final String INSERTARASISTENCIA = 
            "INSERT INTO ASISTENCIA values(?,?,?,?)";
    
    private static final String BORRARASISTENCIA =
            "DELETE FROM MATERIA WHERE ID_ASISTENCIA = ?";
    
    private static final String MAXASITENCIA = 
            "SELECT ISNULL(MAX(ID_ASISTENCIA),0) + 1 AS MAXID FROM ASISTENCIA ";
    
    private static final String ACTUALIZARASISTENCIA = 
            "UPDATE ASISTENCIA SET ASISTENCIA = ? WHERE ID_ASISTENCIA = ?";
    
    private static final String GENERARASISTENCIA = 
            "EXEC [dbo].[SP_GENERAR_ASISTENCIA] ? ";
    
    
    public ArrayList<AsistenciaClase> getAsistenciasByClase(Clase c){
        
        ArrayList<AsistenciaClase> asistencias = new ArrayList<AsistenciaClase>();
        try {
            
            Connection con = Conexion.getConexion();
            
            PreparedStatement ps = con.prepareStatement(LISTAASISTENCIAXCLASE + WHEREBYASISTENCIA + ORDERBYASISTENCIAS);
            ps.setInt(1,c.getIdClase());
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                AsistenciaClase a = new AsistenciaClase();
                CursoAlumno ca = new CursoAlumno();
                Alumno al = new Alumno();
                // Alumno
                al.setID(rs.getInt("ID_ALUMNO"));
                al.setApellido(rs.getString("APELLIDO"));
                al.setNombre(rs.getString("NOMBRE"));
                al.setDNI(rs.getInt("DNI"));
                al.setGenero(rs.getString("GENERO"));
                al.setFechaDeNacimiento(rs.getDate("FECHA_DE_NACIMIENTO"));
                al.setEmail(rs.getString("EMAIL"));
                al.setEstado(rs.getBoolean("ESTADOALUMNO"));
                // Curso Alumno
                ca.setIdCursoAlumno(rs.getInt("ID_CURSO_ALUMNO"));
                ca.setCurso(c.getCurso());
                ca.setAlumno(al);
                ca.setEstado(rs.getString("ESTADOCURSOALUMNO"));
                // Asistencia
                a.setIdAsistencia(rs.getInt("ID_ASISTENCIA") );
                a.setCursoAlumno(ca);
                a.setClase(c);
                a.setAsistencia(rs.getBoolean("ASISTENCIA"));
                asistencias.add(a);
            }
            
            rs.close();
            ps.close();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(AsistenciaClaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return asistencias;
    }

    public int insertarAsistencia(AsistenciaClase a){
        int retorno = 0;
        Connection con = null;
        try {
                con = Conexion.getConexion();
                con.setAutoCommit(false);
                int maxID = this.getMaxIdAsistencia(con);
                      
                PreparedStatement ps = con.prepareStatement(INSERTARASISTENCIA );
                ps.setInt(1,maxID );
                ps.setInt(2,a.getCursoAlumno().getIdCursoAlumno());
                ps.setInt(3,a.getCursoAlumno().getIdCursoAlumno());
                ps.setBoolean(4, a.getAsistencia());
                
                retorno = ps.executeUpdate();
                con.commit();
                con.setAutoCommit(true);
                ps.close();
                con.close();

        } catch (SQLException ex) {
            try {
                Logger.getLogger(AsistenciaClaseDAO.class.getName()).log(Level.SEVERE, null, ex);
                con.rollback();
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException ex1) {
                Logger.getLogger(AsistenciaClaseDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return retorno;
    }


    private int getMaxIdAsistencia(Connection con) throws SQLException{
        int retorno = -1;
      
        PreparedStatement ps = con.prepareStatement(MAXASITENCIA );
        ResultSet rs = ps.executeQuery();
        rs.next();
        retorno = rs.getInt("MAXID");
        rs.close();
        ps.close();   
        
        return retorno;
    }
    
    
    public int BorrarAsistencia(AsistenciaClase a){
        int retorno = 0;
        Connection con = null;

        try {
                con = Conexion.getConexion();
                con.setAutoCommit(false);
                      
                PreparedStatement ps = con.prepareStatement(BORRARASISTENCIA );
                ps.setInt(1,a.getIdAsistencia());
                retorno = ps.executeUpdate();
                con.commit();
                con.setAutoCommit(true);
                ps.close();
                con.close();

        } catch (SQLException ex) {
            try {
                Logger.getLogger(AsistenciaClaseDAO.class.getName()).log(Level.SEVERE, null, ex);
                con.rollback();
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException ex1) {
                Logger.getLogger(AsistenciaClaseDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }

        return retorno;
    }
    
    
    public int ActualizarAsistencia(AsistenciaClase a){
        int retorno = 0;
        Connection con = null;

        try {
                con = Conexion.getConexion();
                con.setAutoCommit(false);
                      
                PreparedStatement ps = con.prepareStatement(ACTUALIZARASISTENCIA );
                ps.setBoolean(1,a.getAsistencia());
                ps.setInt(2,a.getIdAsistencia());
                retorno = ps.executeUpdate();
                con.commit();
                con.setAutoCommit(true);
                ps.close();
                con.close();

        } catch (SQLException ex) {
            try {
                Logger.getLogger(AsistenciaClaseDAO.class.getName()).log(Level.SEVERE, null, ex);
                con.rollback();
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException ex1) {
                Logger.getLogger(AsistenciaClaseDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        
        
        return retorno;
        }
    
    
        public int generarAsistencia (Curso curso){
        int retorno = 0;
        Connection con = null;

        try {
                con = Conexion.getConexion();
        
                      
                PreparedStatement ps = con.prepareStatement(GENERARASISTENCIA);
                ps.setInt(1,curso.getIdCurso());
                retorno = ps.executeUpdate();
               
                ps.close();
                con.close();

        } catch (SQLException ex) {
            try {
                Logger.getLogger(AsistenciaClaseDAO.class.getName()).log(Level.SEVERE, null, ex);

                con.close();
            } catch (SQLException ex1) {
                Logger.getLogger(AsistenciaClaseDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }

        return retorno;
        }
    
}
