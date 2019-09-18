/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.lujan.asistencia.database;

import ar.com.lujan.asistencia.modelo.Alumno;
import ar.com.lujan.asistencia.modelo.Curso;
import ar.com.lujan.asistencia.modelo.CursoAlumno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author geragonzalez
 */
public class CursoAlumnosDAO {
    
    private static final String SELECTBYIDCURSO =     
              "SELECT ID_CURSO_ALUMNO, ID_CURSO, CA.ESTADO, "
            + " A.ID_ALUMNO, A.APELLIDO, A.NOMBRE, A.DNI, A.GENERO, A.FECHA_DE_NACIMIENTO, A.EMAIL, A.ESTADO AS EALUMNO "
            + "FROM CURSO_ALUMNOS CA "
            + " INNER JOIN ALUMNO A ON CA.ID_ALUMNO = A.ID_ALUMNO "
            + "WHERE ID_CURSO = ?";
    
    private static final String SELECTALUNOSNOESTANENCURSO =
              "SELECT C.ID_CURSO,  "
            + " A.ID_ALUMNO, A.APELLIDO, A.NOMBRE, A.DNI, A.GENERO, A.FECHA_DE_NACIMIENTO, A.EMAIL, A.ESTADO AS EALUMNO "
            + " FROM CURSO C, ALUMNO A  "
            + " WHERE ID_CURSO = ? "        
            + " AND NOT EXISTS(SELECT 1 FROM CURSO_ALUMNOS CA "
            + " WHERE  CA.ID_ALUMNO = A.ID_ALUMNO AND ID_CURSO = ?)";
    
    private static final String MAXCURSOALUMNOS = "SELECT ISNULL(MAX(ID_CURSO_ALUMNO),0) + 1 AS MAXID FROM CURSO_ALUMNOS ";
    
    private static final String INSERCURSOALUMNO= "INSERT INTO CURSO_ALUMNOS(ID_CURSO_ALUMNO, ID_CURSO, ID_ALUMNO, ESTADO) VALUES (?,?,?,?)";
   
    private static final String DELETECURSOALUMNO = "DELETE FROM ASISTENCIA WHERE ID_CURSO_ALUMNO = ? ";
    private static final String DELETECURSOALUMNO2 = "DELETE FROM CURSO_ALUMNOS WHERE ID_CURSO = ? AND ID_ALUMNO = ? ";
    
    private static final String ACTULIZARINSCRIPTOS = "UPDATE CURSO SET CANTIDAD_INSCRIPTOS = "
                                                      + "(SELECT COUNT(*) FROM CURSO_ALUMNOS WHERE ID_CURSO= ?)" 
                                                      + " WHERE ID_CURSO = ?";
    
    
    private static final String EXISTEASISTENCIA = "SELECT COUNT(*) AS CLASES FROM ASISTENCIA A JOIN CURSO_ALUMNOS CA ON A.ID_CURSO_ALUMNO= CA.ID_CURSO_ALUMNO WHERE CA.ID_ALUMNO=? AND ID_CURSO=?";
    private static final String GETIDCURSOALUMNO = "SELECT ID_CURSO_ALUMNO FROM CURSO_ALUMNOS WHERE ID_ALUMNO=? AND ID_CURSO=?";
            
    public List<CursoAlumno> getCursoAlumnos(Curso curso){
        ArrayList<CursoAlumno> cursoAlumnos = new ArrayList<>();
        Connection con = null;
        try {
            con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(SELECTBYIDCURSO);
            ps.setInt(1, curso.getIdCurso());
            ResultSet rs = ps.executeQuery(); 
            while (rs.next()) {
                CursoAlumno cursoAlumno = new CursoAlumno();
                cursoAlumno.setIdCursoAlumno(rs.getInt("ID_CURSO_ALUMNO"));
                cursoAlumno.setCurso(curso);
                cursoAlumno.setEstado(rs.getString("ESTADO"));
                /* CREO UNA INSTANCIA DEL ALUMNO y lo seteo al Curso*/
                Alumno alumno = new Alumno();
                alumno.setID(rs.getInt("ID_ALUMNO"));
                alumno.setApellido(rs.getString("APELLIDO"));
                alumno.setNombre(rs.getString("NOMBRE"));
                alumno.setDNI(rs.getInt("DNI"));
                alumno.setGenero(rs.getString("GENERO"));
                alumno.setFechaDeNacimiento(rs.getDate("FECHA_DE_NACIMIENTO"));
                alumno.setEmail(rs.getString("EMAIL"));
                alumno.setEstado(rs.getBoolean("EALUMNO"));
                cursoAlumno.setAlumno(alumno);
                /*--------------------------------------------------*/
                cursoAlumnos.add(cursoAlumno);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CursoAlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cursoAlumnos;
    }
    
    public List<CursoAlumno> getAlumnoNoEstanEnCurso( Curso curso){
        ArrayList<CursoAlumno> cursoAlumnos = new ArrayList<>();
        Connection con = null;
        try {
            
            con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(SELECTALUNOSNOESTANENCURSO);
            ps.setInt(1, curso.getIdCurso());
            ps.setInt(2, curso.getIdCurso());
            ResultSet rs = ps.executeQuery(); 
            while (rs.next()) {
                CursoAlumno cursoAlumno = new CursoAlumno();
                cursoAlumno.setCurso(curso);
                /* CREO UNA INSTANCIA DEL ALUMNO y lo seteo al Curso*/
                Alumno alumno = new Alumno();
                alumno.setID(rs.getInt("ID_ALUMNO"));
                alumno.setApellido(rs.getString("APELLIDO"));
                alumno.setNombre(rs.getString("NOMBRE"));
                alumno.setDNI(rs.getInt("DNI"));
                alumno.setGenero(rs.getString("GENERO"));
                alumno.setFechaDeNacimiento(rs.getDate("FECHA_DE_NACIMIENTO"));
                alumno.setEmail(rs.getString("EMAIL"));
                alumno.setEstado(rs.getBoolean("EALUMNO"));
                cursoAlumno.setAlumno(alumno);
                /*--------------------------------------------------*/
                cursoAlumnos.add(cursoAlumno);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CursoAlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cursoAlumnos;
    }    
    
        
    
    public int insertCursoAlumnos(CursoAlumno cursoAlumno){
        int retorno = 0;
        Connection con = Conexion.getConexion(); 
        
        try {
                con.setAutoCommit(false);
                int maxID = this.getMaxIDCursoAlumno(con);      
                PreparedStatement ps = con.prepareStatement(INSERCURSOALUMNO);
                ps.setInt(1,maxID);
                ps.setInt(2, cursoAlumno.getCurso().getIdCurso());
                ps.setInt(3, cursoAlumno.getAlumno().getID());
                ps.setString(4, "R");
               
                retorno = ps.executeUpdate();
                con.commit();
                con.setAutoCommit(true);
                ps.close();
                con.close();

        } catch (SQLException ex) {
            try {
                Logger.getLogger(CursoAlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
                con.rollback();
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException ex1) {
                Logger.getLogger(CursoAlumnosDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return retorno;
    }
    
    
    private int getMaxIDCursoAlumno(Connection con) throws SQLException{
        int retorno;
      
        PreparedStatement ps;
        ps = con.prepareStatement(MAXCURSOALUMNOS);
        ResultSet rs = ps.executeQuery();
        rs.next();
        retorno = rs.getInt("MAXID");
        rs.close();
        ps.close();   
        
        return retorno;
    }    
    
    public int DeleteCursoAlumnos(CursoAlumno cursoAlumno){
        int retorno = -1;
        Connection con = null;
        try {
            con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(DELETECURSOALUMNO);
            ps.setInt(1,cursoAlumno.getIdCursoAlumno());
            ps.executeUpdate();//execute update se usa para DML
            ps.close();
            ps=con.prepareStatement(DELETECURSOALUMNO2);
            ps.setInt(1, cursoAlumno.getCurso().getIdCurso());
            ps.setInt(2, cursoAlumno.getAlumno().getID());
            ps.executeUpdate();//execute update se usa para DML
            ps.close();
            con.close();        
        } catch (SQLException ex) {
            Logger.getLogger(ClaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public int ActualizarInscriptosAlCuros(Curso curso){
        int retorno = 0;
        Connection con = Conexion.getConexion(); 
        
        try {
                con.setAutoCommit(false);
                PreparedStatement ps = con.prepareStatement(ACTULIZARINSCRIPTOS);

                ps.setInt(1, curso.getIdCurso());
                ps.setInt(2, curso.getIdCurso());
                retorno = ps.executeUpdate();
                con.commit();
                con.setAutoCommit(true);
                ps.close();
                con.close();

        } catch (SQLException ex) {
            try {
                Logger.getLogger(CursoAlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
                con.rollback();
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException ex1) {
                Logger.getLogger(CursoAlumnosDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return retorno;
    }    
    
    public int existeAsistencia(CursoAlumno cursoAlumno){
        Connection con;
        int r = 0;
        try {
            con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(EXISTEASISTENCIA);
            ps.setInt(1, cursoAlumno.getAlumno().getID());
            ps.setInt(2, cursoAlumno.getCurso().getIdCurso());
            ResultSet rs = ps.executeQuery(); 
            rs.next();
            r= rs.getInt("CLASES");
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CursoAlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
    
    
    public int getIdCursoAlumno(CursoAlumno cursoAlumno){
        Connection con;
        int r=0;
        try {
            con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(GETIDCURSOALUMNO);
            ps.setInt(1, cursoAlumno.getAlumno().getID());
            ps.setInt(2, cursoAlumno.getCurso().getIdCurso());
            ResultSet rs = ps.executeQuery(); 
            rs.next();
            r= rs.getInt("ID_CURSO_ALUMNO");
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CursoAlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
    
}
