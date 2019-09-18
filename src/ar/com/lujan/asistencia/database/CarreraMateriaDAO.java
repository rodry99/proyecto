/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.lujan.asistencia.database;

import ar.com.lujan.asistencia.modelo.Carrera;
import ar.com.lujan.asistencia.modelo.CarreraMateria;
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

public class CarreraMateriaDAO {

    private static final String BUSCARIDCARRERAMATERIA = "SELECT ID_CARRERA_MATERIA, ANIO_CARRERA FROM CARRERA_MATERIAS WHERE ID_CARRERA = ? AND ID_MATERIA = ? "; 
    
    
    private static final String LISTACARRERASMATERIAS = 
            "SELECT CM.ID_CARRERA_MATERIA, CM.ID_CARRERA, CM.ID_MATERIA, " +
            "CM.ANIO_CARRERA, C.DESCRIPCION AS DESCRIPCION_CARRERA, M.DESCRIPCION  AS DESCRIPCION_MATERIA " +
            "FROM CARRERA_MATERIAS CM, CARRERA C, MATERIA M " +
            "WHERE CM.ID_CARRERA = C.ID_CARRERA AND CM.ID_MATERIA = M.ID_MATERIA ";
   
    private static final String WHERECARRERASXMATERIA = 
             "AND CM.ID_MATERIA = ? ";
   
    private static final String WHEREMATERIASXCARRERA = 
            "AND CM.ID_CARRERA = ? ";
   
    private static final String WHERECARRERAMATERIA = 
            "AND CM.ID_CARRERA = ? AND CM.ID_MATERIA = ? ";
   
    private static final String ORDERBYCARRERAMATERIA = 
            "ORDER BY C.DESCRIPCION, CM.ANIO_CARRERA, M.DESCRIPCION";
   
    private static final String INSERTARCARRERAMATERIAS =
            "INSERT INTO CARRERA_MATERIAS values(?,?,?,?)";
    
    private static final String BORRARCARRERAMATERIAS =
            "DELETE FROM CARRERA_MATERIAS WHERE ID_CARRERA_MATERIA = ?";
    
    private static final String MAXCARRERAMATERIAS = "SELECT ISNULL(MAX(ID_CARRERA_MATERIA),0) + 1 AS MAXID FROM CARRERA_MATERIAS ";
    
    private static final String ACTUALIZARCARRERAMATERIAS = "UPDATE CARRERA_MATERIAS SET ANIO_CARRERA = ? WHERE ID_CARRERA_MATERIA = ?";
    
    private static final String LISTAMATERIASNOCARRERA = 
            "SELECT M.ID_MATERIA, M.DESCRIPCION " +
            "FROM MATERIA M " +
            "WHERE M.ID_MATERIA NOT IN (SELECT CM.ID_MATERIA FROM CARRERA_MATERIAS CM WHERE CM.ID_CARRERA = ?)";


    public ArrayList<CarreraMateria> getCarreraMateria(){
        ArrayList<CarreraMateria> carreraMateria = new ArrayList<>();
        try (   Connection con = Conexion.getConexion(); 
                PreparedStatement ps = con.prepareStatement(LISTACARRERASMATERIAS + ORDERBYCARRERAMATERIA);
                ResultSet rs = ps.executeQuery()) 
        {
                while(rs.next()){
                    CarreraMateria cm = new CarreraMateria();
                    Materia m = new Materia();
                    Carrera c = new Carrera();
                    
                    cm.setIdCarreraMateria(rs.getInt("ID_CARRERA_MATERIA"));
                    c.setIdCarrera(rs.getInt("ID_CARRERA"));
                    c.setDescripcion(rs.getString("DESCRIPCION_CARRERA"));
                    m.setIdMateria(rs.getInt("ID_MATERIA"));
                    m.setDescripcion(rs.getString("DESCRIPCION_MATERIA"));
                    cm.setCarrera(c);
                    cm.setMateria(m);
                    cm.setAnioCarrera(rs.getInt("ANIO_CARRERA"));
                    
                    carreraMateria.add(cm);
                }

        } catch (SQLException ex) {
            Logger.getLogger(CarreraMateriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return carreraMateria;
    }
    
    public ArrayList<CarreraMateria> getCarrerasByMateria(Materia materia){
        ArrayList<CarreraMateria> carreraMateria = new ArrayList<>();
       
        try {
            Connection con = Conexion.getConexion();
         
                PreparedStatement ps = con.prepareStatement(LISTACARRERASMATERIAS + WHERECARRERASXMATERIA +ORDERBYCARRERAMATERIA); 
                ps.setInt(1,materia.getIdMateria() );
                ResultSet rs = ps.executeQuery(); 
        
                while(rs.next()){
                    CarreraMateria cm = new CarreraMateria();
                    Carrera c = new Carrera();
                    Materia m = new Materia();

                    cm.setIdCarreraMateria(rs.getInt("ID_CARRERA_MATERIA"));
                    c.setIdCarrera(rs.getInt("ID_CARRERA"));
                    c.setDescripcion(rs.getString("DESCRIPCION_CARRERA"));
                    m.setIdMateria(rs.getInt("ID_MATERIA"));
                    m.setDescripcion(rs.getString("DESCRIPCION_MATERIA"));
                    cm.setCarrera(c);
                    cm.setMateria(m);
                    cm.setAnioCarrera(rs.getInt("ANIO_CARRERA"));
                    carreraMateria.add(cm);
                }
                rs.close();
                ps.close();
                con.close();

        } catch (SQLException ex) {
            Logger.getLogger(CarreraMateriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return carreraMateria;
    }
    
    
    public ArrayList<CarreraMateria> getMateriasByCarrera(Carrera carrera){
        ArrayList<CarreraMateria> carreraMateria = new ArrayList<>();
       
        try {
            Connection con = Conexion.getConexion();
         
                PreparedStatement ps = con.prepareStatement(LISTACARRERASMATERIAS + WHEREMATERIASXCARRERA + ORDERBYCARRERAMATERIA);
                ps.setInt(1,carrera.getIdCarrera());
                ResultSet rs = ps.executeQuery(); 
        
                while(rs.next()){
                    CarreraMateria cm = new CarreraMateria();
                    Carrera c = new Carrera();
                    Materia m = new Materia();

                    cm.setIdCarreraMateria(rs.getInt("ID_CARRERA_MATERIA"));
                    m.setIdMateria(rs.getInt("ID_MATERIA"));
                    m.setDescripcion(rs.getString("DESCRIPCION_MATERIA"));
                    
                    c.setIdCarrera(rs.getInt("ID_CARRERA"));
                    c.setDescripcion(rs.getString("DESCRIPCION_CARRERA"));
                    cm.setCarrera(c);
                    cm.setMateria(m);
                    cm.setAnioCarrera(rs.getInt("ANIO_CARRERA"));
                    carreraMateria.add(cm);
                }
                rs.close();
                ps.close();
                con.close();

        } catch (SQLException ex) {
            Logger.getLogger(CarreraMateriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return carreraMateria;
    }
    
    
    public ArrayList<CarreraMateria> getCarreraMateria(Carrera carrera, Materia materia){
        ArrayList<CarreraMateria> carreraMateria = new ArrayList<>();
       
        try {
            Connection con = Conexion.getConexion();
         
                PreparedStatement ps = con.prepareStatement(LISTACARRERASMATERIAS + WHERECARRERAMATERIA + ORDERBYCARRERAMATERIA);
                ps.setInt(1,carrera.getIdCarrera());
                ps.setInt(2,materia.getIdMateria());
                ResultSet rs = ps.executeQuery(); 
        
                while(rs.next()){
                    CarreraMateria cm = new CarreraMateria();
                    Carrera c = new Carrera();
                    Materia m = new Materia();

                    cm.setIdCarreraMateria(rs.getInt("ID_CARRERA_MATERIA"));
                    c.setIdCarrera(rs.getInt("ID_CARRERA"));
                    c.setDescripcion(rs.getString("DESCRIPCION_CARRERA"));
                    m.setIdMateria(rs.getInt("ID_MATERIA"));
                    m.setDescripcion(rs.getString("DESCRIPCION_MATERIA"));
                    cm.setCarrera(c);
                    cm.setMateria(m);
                    cm.setAnioCarrera(rs.getInt("ANIO_CARRERA"));
                    carreraMateria.add(cm);
                }
                rs.close();
                ps.close();
                con.close();

        } catch (SQLException ex) {
            Logger.getLogger(CarreraMateriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return carreraMateria;
    }
    
    
    public ArrayList<Materia> getMateriasNoCarrera(Carrera c){
        
        ArrayList<Materia> materias = new ArrayList<Materia>();
        try {
            
            Connection con = Conexion.getConexion();
            
            PreparedStatement ps = con.prepareStatement(LISTAMATERIASNOCARRERA);
            ps.setInt(1,c.getIdCarrera());
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

    
    public int insertarCarreraMateria(CarreraMateria cm){
        int retorno = 0;
        Connection con = null;
        try {
                con = Conexion.getConexion();
                con.setAutoCommit(false);
                int maxID = this.getMaxIdCarreraMaterias(con);
                      
                PreparedStatement ps = con.prepareStatement(INSERTARCARRERAMATERIAS );
                ps.setInt(1,maxID );
                ps.setInt(2, cm.getCarrera().getIdCarrera());
                ps.setInt(3, cm.getMateria().getIdMateria());
                ps.setInt(4, cm.getAnioCarrera());
                
                retorno = ps.executeUpdate();
                con.commit();
                con.setAutoCommit(true);
                ps.close();
                con.close();

        } catch (SQLException ex) {
            try {
                Logger.getLogger(CarreraMateriaDAO.class.getName()).log(Level.SEVERE, null, ex);
                con.rollback();
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException ex1) {
                Logger.getLogger(CarreraMateriaDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return retorno;
    }

    
    private int getMaxIdCarreraMaterias(Connection con) throws SQLException{
        int retorno = -1;
      
        PreparedStatement ps = con.prepareStatement(MAXCARRERAMATERIAS );
        ResultSet rs = ps.executeQuery();
        rs.next();
        retorno = rs.getInt("MAXID");
        rs.close();
        ps.close();   
        
        return retorno;
    }
    
    
    public int BorrarCarreraMateria(CarreraMateria cm){
        int retorno = 0;
        Connection con = null;

        try {
                con = Conexion.getConexion();
                con.setAutoCommit(false);
                      
                PreparedStatement ps = con.prepareStatement(BORRARCARRERAMATERIAS);
                ps.setInt(1,cm.getIdCarreraMateria());
                retorno = ps.executeUpdate();
                con.commit();
                con.setAutoCommit(true);
                ps.close();
                con.close();

        } catch (SQLException ex) {
            try {
                Logger.getLogger(CarreraMateriaDAO.class.getName()).log(Level.SEVERE, null, ex);
                con.rollback();
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException ex1) {
                Logger.getLogger(CarreraMateriaDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }

        return retorno;
    }
    
    
    public int ActualizarCarreraMateria(CarreraMateria cm){
        int retorno = 0;
        Connection con = null;

        try {
                con = Conexion.getConexion();
                con.setAutoCommit(false);
                      
                PreparedStatement ps = con.prepareStatement(ACTUALIZARCARRERAMATERIAS);
                ps.setInt(1, cm.getAnioCarrera());
                ps.setInt(2,cm.getIdCarreraMateria());
                retorno = ps.executeUpdate();
                con.commit();
                con.setAutoCommit(true);
                ps.close();
                con.close();

        } catch (SQLException ex) {
            try {
                Logger.getLogger(CarreraMateriaDAO.class.getName()).log(Level.SEVERE, null, ex);
                con.rollback();
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException ex1) {
                Logger.getLogger(CarreraMateriaDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        
        return retorno;
        }
    
 public CarreraMateria buscarIdCarreraMateria(Carrera carrera,Materia materia) {
        CarreraMateria retorno = new CarreraMateria();
        Connection con = null;

        try {
                con = Conexion.getConexion();
                con.setAutoCommit(false);
                      
                PreparedStatement ps = con.prepareStatement(BUSCARIDCARRERAMATERIA);
                ps.setInt(1,carrera.getIdCarrera());
                ps.setInt(2,materia.getIdMateria());                
               // retorno = ps.executeUpdate();
                
                ResultSet rs = ps.executeQuery();
                rs.next();
                retorno.setIdCarreraMateria(rs.getInt("ID_CARRERA_MATERIA"));
                retorno.setCarrera(carrera);
                retorno.setMateria(materia);
                retorno.setAnioCarrera(rs.getInt("ANIO_CARRERA"));
                con.commit();
                con.setAutoCommit(true);
                ps.close();
                con.close();

        } catch (SQLException ex) {
            try {
                Logger.getLogger(CarreraMateriaDAO.class.getName()).log(Level.SEVERE, null, ex);
                con.rollback();
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException ex1) {
                Logger.getLogger(CarreraMateriaDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }

        return retorno;
    }
        
    
}
