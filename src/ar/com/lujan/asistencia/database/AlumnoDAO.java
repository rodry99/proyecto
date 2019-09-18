/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.lujan.asistencia.database;

import ar.com.lujan.asistencia.modelo.Alumno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author usuario
 */
public class AlumnoDAO {
    private static final String SELECTALUMNO = 
            "SELECT id_alumno, apellido, nombre, dni, genero, fecha_de_nacimiento, email, estado FROM Alumno";
    
    private static final String INSERTALUMNO = 
            "INSERT INTO alumno(id_alumno,apellido, nombre, dni, genero, fecha_de_nacimiento, email, estado)" + 
            "VALUES(?,?,?,?,?,?,?,?)";
    
    private static final String UPDATEALUMNO = 
            "UPDATE alumno " + 
            "SET apellido = ?, " +
            "nombre = ?, " +
            "dni = ?, " +
            "genero = ?, "+
            "fecha_de_nacimiento = ?, "+
            "email = ?, "+ 
            "estado = ? "+        
            "WHERE id_alumno = ?";
    
    private static final String DELETEALUMNO = "DELETE FROM alumno WHERE id_alumno = ?";

    private static final String MAXALUMNO = "SELECT ISNULL(MAX(ID_ALUMNO),0) + 1 AS MAXID FROM alumno ";

    private static final String ALUMNODNI = "SELECT count(*) AS Cantidad_de_dni FROM alumno WHERE DNI=?";
    
    private static final String ALUMNOCURSO = "SELECT dbo.FN_ALUMNO_CURSO(?) AS Cantidad_de_cursos";
    

    public ArrayList<Alumno> getAlumno(){


        ArrayList<Alumno> alumnos = new ArrayList<Alumno>();

        try {

            Connection con = Conexion.getConexion();
            String query = SELECTALUMNO;
            PreparedStatement ps;
            ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Alumno a = new Alumno();
                a.setID(rs.getInt("ID_ALUMNO"));
                a.setApellido(rs.getString("APELLIDO"));
                a.setNombre(rs.getString("NOMBRE"));
                a.setDNI(rs.getInt("DNI"));
                a.setGenero(rs.getString("GENERO"));
                a.setFechaDeNacimiento(rs.getDate("FECHA_DE_NACIMIENTO"));
                a.setEmail(rs.getString("EMAIL"));
                a.setEstado(rs.getBoolean("ESTADO"));
                alumnos.add(a);
                 
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return alumnos;

    }
    
     public int insertAlumno(Alumno a){
        int retorno = 0;
        Connection con = null;
        try {
                con = Conexion.getConexion();
                con.setAutoCommit(false);
                int maxID = this.getMaxIDAlumno(con);      
                PreparedStatement ps = con.prepareStatement(INSERTALUMNO);
                ps.setInt(1,maxID);
                ps.setString(2,a.getApellido());
                ps.setString(3,a.getNombre());                
                ps.setInt(4,a.getDNI());
                ps.setString(5,a.getGenero());
                if (a.getFechaDeNacimiento() != null) ps.setDate(6,(new java.sql.Date(a.getFechaDeNacimiento().getTime()))); else ps.setDate(6,null);
                ps.setString(7,a.getEmail());
                ps.setBoolean(8,a.isEstado());
               
                retorno = ps.executeUpdate();
                con.commit();
                con.setAutoCommit(true);
                ps.close();
                con.close();

        } catch (SQLException ex) {
            try {
                Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
                con.rollback();
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException ex1) {
                Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return retorno;
    }
     
     public int updateAlumno(Alumno a){
        int retorno = 0;
        Connection con = null;
        try {
                con = Conexion.getConexion();
                PreparedStatement ps = con.prepareStatement(UPDATEALUMNO);
                
                ps.setString(1,a.getApellido());
                ps.setString(2,a.getNombre());                
                ps.setInt(3,a.getDNI());
                ps.setString(4,a.getGenero());
                if (a.getFechaDeNacimiento() != null) ps.setDate(5,(new java.sql.Date(a.getFechaDeNacimiento().getTime()))); else ps.setDate(5,null);
                ps.setString(6,a.getEmail());
                ps.setBoolean(7,a.isEstado());
                ps.setInt(8,a.getID());
                
                retorno = ps.executeUpdate();
                ps.close();
                con.close();

        } catch (SQLException ex) {
            try {
                Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
                con.rollback();
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException ex1) {
                Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return retorno;
    }
    
        public int deleteAlumno(Alumno a){
        int retorno = -1;
        Connection con = null;
        try {
            con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(DELETEALUMNO);
            ps.setInt(1,a.getID());
            retorno = ps.executeUpdate();//execute update se usa para DML
            ps.close();
            con.close();
        }
        catch  (SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        
        
        return retorno;
    }
        
        
        
    private int getMaxIDAlumno(Connection con) throws SQLException{
        int retorno = -1;
      
        PreparedStatement ps = con.prepareStatement(MAXALUMNO);
        ResultSet rs = ps.executeQuery();
        rs.next();
        retorno = rs.getInt("MAXID");
        rs.close();
        ps.close();   
        
        return retorno;
    }
    
    public int getCantidadDeCursosByAlumno(Alumno a){
        int retorno = -1;
        Connection con = null;
        try {
            con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(ALUMNOCURSO);
            ps.setInt(1,a.getID());
            ResultSet rs = ps.executeQuery();
            rs.next();
            retorno=rs.getInt("CANTIDAD_DE_CURSOS");
            rs.close();
            ps.close();
            con.close();
        }
        catch  (SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        
        
        return retorno;
    }
    
    
    public boolean isExisteDni(Alumno a){
        int cantidadDni = -1;
        boolean retorno = false;
        Connection con = null;
        try {
            con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(ALUMNODNI);
            if (a.getID()==0) {
                ps.setInt(1,a.getDNI());

            }
            else
            {
                ps = con.prepareStatement(ALUMNODNI + " AND ID_ALUMNO!=?");
                ps.setInt(1,a.getDNI());
                ps.setInt(2,a.getID());
            }    
            ResultSet rs = ps.executeQuery();
            rs.next();
            cantidadDni=rs.getInt("Cantidad_de_dni");

            if (cantidadDni==1) 
                retorno=true;
            else
                retorno=false;
            rs.close();
            ps.close();
            con.close();
        }
        catch  (SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        
        
        return retorno;
    }    


    public ArrayList<Alumno> getAlumnoByFilters(String busqueda) {
        
        ArrayList<Alumno> alumnosbyfilters = new ArrayList<Alumno>();
        try 
        {   
            String query = SELECTALUMNO + " WHERE CONVERT (varchar (50), ID_ALUMNO) LIKE '%" + busqueda + "%'"
                        + "OR APELLIDO LIKE '%" + busqueda + "%'"
                        + "OR NOMBRE LIKE '%" + busqueda + "%'"
                        + "OR CONVERT (varchar (50), DNI) LIKE '%" + busqueda + "%'"
                        + "OR GENERO LIKE '%" + busqueda + "%'"
                        + "OR FECHA_DE_NACIMIENTO LIKE '%" + busqueda + "%'"
                        + "OR EMAIL LIKE '%" + busqueda + "%'";
            Connection con = Conexion.getConexion();
            
            PreparedStatement ps = con.prepareStatement(query); 

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Alumno a = new Alumno();
                a.setID(rs.getInt("ID_ALUMNO"));
                a.setApellido(rs.getString("APELLIDO"));
                a.setNombre(rs.getString("NOMBRE"));
                a.setDNI(rs.getInt("DNI"));
                a.setGenero(rs.getString("GENERO"));
                a.setFechaDeNacimiento(rs.getDate("FECHA_DE_NACIMIENTO"));
                a.setEmail(rs.getString("EMAIL"));
                a.setEstado(rs.getBoolean("ESTADO"));
                alumnosbyfilters.add(a);
                 
            }
            rs.close();
            ps.close();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return alumnosbyfilters;

    }

    public ArrayList<Alumno> getAlumnoByFilterApellido(String busqueda) {

        ArrayList<Alumno> alumnosbyfilters = new ArrayList<Alumno>();
        try 
        {   
            String query = SELECTALUMNO + " WHERE APELLIDO LIKE '%" + busqueda + "%'";
            Connection con = Conexion.getConexion();
            
            PreparedStatement ps = con.prepareStatement(query); 

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Alumno a = new Alumno();
                a.setID(rs.getInt("ID_ALUMNO"));
                a.setApellido(rs.getString("APELLIDO"));
                a.setNombre(rs.getString("NOMBRE"));
                a.setDNI(rs.getInt("DNI"));
                a.setGenero(rs.getString("GENERO"));
                a.setFechaDeNacimiento(rs.getDate("FECHA_DE_NACIMIENTO"));
                a.setEmail(rs.getString("EMAIL"));
                a.setEstado(rs.getBoolean("ESTADO"));
                alumnosbyfilters.add(a);
                 
            }
            rs.close();
            ps.close();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return alumnosbyfilters;

    }

    public ArrayList<Alumno> getAlumnoByFilterNombre(String busqueda) {

        ArrayList<Alumno> alumnosbyfilters = new ArrayList<Alumno>();
        try 
        {   
            String query = SELECTALUMNO + " WHERE NOMBRE LIKE '%" + busqueda + "%'";
            Connection con = Conexion.getConexion();
            
            PreparedStatement ps = con.prepareStatement(query); 

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Alumno a = new Alumno();
                a.setID(rs.getInt("ID_ALUMNO"));
                a.setApellido(rs.getString("APELLIDO"));
                a.setNombre(rs.getString("NOMBRE"));
                a.setDNI(rs.getInt("DNI"));
                a.setGenero(rs.getString("GENERO"));
                a.setFechaDeNacimiento(rs.getDate("FECHA_DE_NACIMIENTO"));
                a.setEmail(rs.getString("EMAIL"));
                a.setEstado(rs.getBoolean("ESTADO"));
                alumnosbyfilters.add(a);
                 
            }
            rs.close();
            ps.close();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return alumnosbyfilters;

    }

    public ArrayList<Alumno> getAlumnoByFilterDNI(String busqueda) {

        ArrayList<Alumno> alumnosbyfilters = new ArrayList<Alumno>();
        try 
        {   
            String query = SELECTALUMNO + " WHERE DNI LIKE '%" + busqueda + "%'";
            Connection con = Conexion.getConexion();
            
            PreparedStatement ps = con.prepareStatement(query); 

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Alumno a = new Alumno();
                a.setID(rs.getInt("ID_ALUMNO"));
                a.setApellido(rs.getString("APELLIDO"));
                a.setNombre(rs.getString("NOMBRE"));
                a.setDNI(rs.getInt("DNI"));
                a.setGenero(rs.getString("GENERO"));
                a.setFechaDeNacimiento(rs.getDate("FECHA_DE_NACIMIENTO"));
                a.setEmail(rs.getString("EMAIL"));
                a.setEstado(rs.getBoolean("ESTADO"));
                alumnosbyfilters.add(a);
                 
            }
            rs.close();
            ps.close();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return alumnosbyfilters;
    }

    public ArrayList<Alumno> getAlumnoByFilterGenero(String busqueda) {

        ArrayList<Alumno> alumnosbyfilters = new ArrayList<Alumno>();
        try 
        {   
            String query = SELECTALUMNO + " WHERE GENERO LIKE '%" + busqueda + "%'";
            Connection con = Conexion.getConexion();
            
            PreparedStatement ps = con.prepareStatement(query); 

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Alumno a = new Alumno();
                a.setID(rs.getInt("ID_ALUMNO"));
                a.setApellido(rs.getString("APELLIDO"));
                a.setNombre(rs.getString("NOMBRE"));
                a.setDNI(rs.getInt("DNI"));
                a.setGenero(rs.getString("GENERO"));
                a.setFechaDeNacimiento(rs.getDate("FECHA_DE_NACIMIENTO"));
                a.setEmail(rs.getString("EMAIL"));
                a.setEstado(rs.getBoolean("ESTADO"));
                alumnosbyfilters.add(a);
                 
            }
            rs.close();
            ps.close();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return alumnosbyfilters;
    }
    
    public ArrayList<Alumno> getAlumnoByFilterEmail(String busqueda) {
        ArrayList<Alumno> alumnosbyfilters = new ArrayList<Alumno>();
        try 
        {   
            String query = SELECTALUMNO + " WHERE EMAIL LIKE '%" + busqueda + "%'";
            Connection con = Conexion.getConexion();
            
            PreparedStatement ps = con.prepareStatement(query); 

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Alumno a = new Alumno();
                a.setID(rs.getInt("ID_ALUMNO"));
                a.setApellido(rs.getString("APELLIDO"));
                a.setNombre(rs.getString("NOMBRE"));
                a.setDNI(rs.getInt("DNI"));
                a.setGenero(rs.getString("GENERO"));
                a.setFechaDeNacimiento(rs.getDate("FECHA_DE_NACIMIENTO"));
                a.setEmail(rs.getString("EMAIL"));
                a.setEstado(rs.getBoolean("ESTADO"));
                alumnosbyfilters.add(a);
                 
            }
            rs.close();
            ps.close();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return alumnosbyfilters;
    }

    public ArrayList<Alumno> getAlumnoByFilterFechaDeNacimiento(String busqueda) {
 
        ArrayList<Alumno> alumnosbyfilters = new ArrayList<Alumno>();
        try 
        {   
            String query = SELECTALUMNO + " WHERE FECHA_DE_NACIMIENTO LIKE '%" + busqueda + "%'";
            Connection con = Conexion.getConexion();
            
            PreparedStatement ps = con.prepareStatement(query); 

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Alumno a = new Alumno();
                a.setID(rs.getInt("ID_ALUMNO"));
                a.setApellido(rs.getString("APELLIDO"));
                a.setNombre(rs.getString("NOMBRE"));
                a.setDNI(rs.getInt("DNI"));
                a.setGenero(rs.getString("GENERO"));
                a.setFechaDeNacimiento(rs.getDate("FECHA_DE_NACIMIENTO"));
                a.setEmail(rs.getString("EMAIL"));
                a.setEstado(rs.getBoolean("ESTADO"));
                alumnosbyfilters.add(a);
                 
            }
            rs.close();
            ps.close();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return alumnosbyfilters;

    }
// en el caso del estado , debo verificar la consulta a realizar para un campo de tipo bit
    public ArrayList<Alumno> getAlumnosActivos(){


        ArrayList<Alumno> alumnos = new ArrayList<Alumno>();

        try {

            Connection con = Conexion.getConexion();
            String query = SELECTALUMNO + " WHERE ESTADO = 1 ";
            PreparedStatement ps;
            ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Alumno a = new Alumno();
                a.setID(rs.getInt("ID_ALUMNO"));
                a.setApellido(rs.getString("APELLIDO"));
                a.setNombre(rs.getString("NOMBRE"));
                a.setDNI(rs.getInt("DNI"));
                a.setGenero(rs.getString("GENERO"));
                a.setFechaDeNacimiento(rs.getDate("FECHA_DE_NACIMIENTO"));
                a.setEmail(rs.getString("EMAIL"));
                a.setEstado(rs.getBoolean("ESTADO"));
                alumnos.add(a);
                 
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return alumnos;

    }
 
    public ArrayList<Alumno> getAlumnosInactivos(){


        ArrayList<Alumno> alumnos = new ArrayList<Alumno>();

        try {

            Connection con = Conexion.getConexion();
            String query = SELECTALUMNO + " WHERE ESTADO = 0 ";
            PreparedStatement ps;
            ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Alumno a = new Alumno();
                a.setID(rs.getInt("ID_ALUMNO"));
                a.setApellido(rs.getString("APELLIDO"));
                a.setNombre(rs.getString("NOMBRE"));
                a.setDNI(rs.getInt("DNI"));
                a.setGenero(rs.getString("GENERO"));
                a.setFechaDeNacimiento(rs.getDate("FECHA_DE_NACIMIENTO"));
                a.setEmail(rs.getString("EMAIL"));
                a.setEstado(rs.getBoolean("ESTADO"));
                alumnos.add(a);
                 
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return alumnos;

    }

    

    

    
    
    
}
