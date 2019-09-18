package ar.com.lujan.asistencia.database;

import ar.com.lujan.asistencia.modelo.Profesor;

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
public class ProfesorDAO {
   private static final String SELECTPROFESOR = 
            "SELECT id_profesor, apellido, nombre, dni, genero, fecha_de_nacimiento, email, estado FROM Profesor";
    
    private static final String INSERTPROFESOR = 
            "INSERT INTO profesor(id_profesor,apellido, nombre, dni, genero, fecha_de_nacimiento, email, estado)" + 
            "VALUES(?,?,?,?,?,?,?,?)";
    
    private static final String UPDATEPROFESOR = 
            "UPDATE profesor " + 
            "SET apellido = ?, " +
            "nombre = ?, " +
            "dni = ?, " +
            "genero = ?, "+
            "fecha_de_nacimiento = ?, "+
            "email = ?, "+ 
            "estado = ? "+        
            "WHERE id_profesor = ?";
    
    private static final String DELETEPROFESOR = "DELETE FROM profesor WHERE id_profesor = ?";

    private static final String MAXPROFESOR = "SELECT ISNULL(MAX(ID_PROFESOR),0) + 1 AS MAXID FROM profesor ";


    public ArrayList<Profesor> getProfesor(){


        ArrayList<Profesor> profesor = new ArrayList<Profesor>();

        try {

             Connection con = Conexion.getConexion();
             String query = SELECTPROFESOR;
            PreparedStatement ps;
            ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Profesor p = new Profesor();
                p.setIdProfesor(rs.getInt("ID_PROFESOR"));
                p.setApellido(rs.getString("APELLIDO"));
                p.setNombre(rs.getString("NOMBRE"));
                p.setDni(rs.getInt("DNI"));
                p.setGenero(rs.getString("GENERO"));
                p.setFechaDeNacimiento(rs.getDate("FECHA_DE_NACIMIENTO"));
                p.setEmail(rs.getString("EMAIL"));
                p.setEstado(rs.getBoolean("ESTADO"));
                profesor.add(p);
                 
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProfesorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return profesor;

    }
    
     public int insertProfesor(Profesor p){
        int retorno = 0;
        Connection con = null;
        try {
                con = Conexion.getConexion();
                con.setAutoCommit(false);
                int maxID = this.getMaxIDProfesor(con);      
                PreparedStatement ps = con.prepareStatement(INSERTPROFESOR);
                ps.setInt(1,maxID);
                ps.setString(2,p.getApellido());
                ps.setString(3,p.getNombre());                
                ps.setInt(4,p.getDni());
                ps.setString(5,p.getGenero());
                if (p.getFechaDeNacimiento() != null) ps.setDate(6,(new java.sql.Date(p.getFechaDeNacimiento().getTime()))); else ps.setDate(6,null);
                ps.setString(7,p.getEmail());
                ps.setBoolean(8,p.isEstado());
               
                retorno = ps.executeUpdate();
                con.commit();
                con.setAutoCommit(true);
                ps.close();
                con.close();

       
        } catch (SQLException ex) {
            try {
                Logger.getLogger(ProfesorDAO.class.getName()).log(Level.SEVERE, null, ex);
                con.rollback();
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException ex1) {
                Logger.getLogger(ProfesorDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return retorno;
    }
     
     public int updateProfesor(Profesor p){
        int retorno = 0;
        Connection con = null;
        try {
                con = Conexion.getConexion();
                PreparedStatement ps = con.prepareStatement(UPDATEPROFESOR);
                
                ps.setString(1,p.getApellido());
                ps.setString(2,p.getNombre());                
                ps.setInt(3,p.getDni());
                ps.setString(4,p.getGenero());
                if (p.getFechaDeNacimiento() != null) ps.setDate(6,(new java.sql.Date(p.getFechaDeNacimiento().getTime()))); else ps.setDate(6,null);
                ps.setString(6,p.getEmail());
                ps.setBoolean(7,p.isEstado());
                ps.setInt(8,p.getIdProfesor());
                
                retorno = ps.executeUpdate();
                ps.close();
                con.close();

        } catch (SQLException ex) {
            try {
                Logger.getLogger(ProfesorDAO.class.getName()).log(Level.SEVERE, null, ex);
                con.rollback();
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException ex1) {
                Logger.getLogger(ProfesorDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return retorno;
    }
    
        public int deleteProfesor(Profesor p){
        int retorno = -1;
        Connection con = null;
        try {
            con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(DELETEPROFESOR);
            ps.setInt(1,p.getIdProfesor());
            retorno = ps.executeUpdate();//execute update se usa para DML
            ps.close();
            con.close();
        }
        catch  (SQLException ex) {
            Logger.getLogger(ProfesorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        
        
        return retorno;
    }
    private int getMaxIDProfesor(Connection con) throws SQLException{
        int retorno = -1;
      
        PreparedStatement ps = con.prepareStatement(MAXPROFESOR);
        ResultSet rs = ps.executeQuery();
        rs.next();
        retorno = rs.getInt("MAXID");
        rs.close();
        ps.close();   
        
        return retorno;
    }
}
