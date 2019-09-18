/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.lujan.asistencia.database;


import ar.com.lujan.asistencia.modelo.Materia;
import ar.com.lujan.asistencia.modelo.Carrera;
import ar.com.lujan.asistencia.modelo.CarreraMateria;
import ar.com.lujan.asistencia.modelo.Curso;
import ar.com.lujan.asistencia.modelo.Clase;
import ar.com.lujan.asistencia.modelo.Profesor;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author usuario
 */
public class ClaseDAO {
    private static final String SELECTCLASE = 
            "SELECT id_clase, id_curso, fecha, tema, estado FROM clase";

    private static final String SELECTCLASEFULL = 
            "SELECT cl.id_clase, cl.id_curso, cl.fecha, cl.tema, cl.estado, " +
            "cu.id_curso, cu.id_carrera_materia, cu.anio_lectivo, cu.cuatrimestre, cu.comision, " + 
            "cu.id_profesor, cu.estado as estado_curso, cu.cantidad_inscriptos, cu.desde, cu.hasta, " + 
            "cm.id_carrera_materia, cm.id_carrera, cm.id_materia, cm.anio_carrera, " + 
            "ca.id_carrera, ca.descripcion AS carrera, m.id_materia, m.descripcion AS materia, " +
            "p.id_profesor, p.apellido, p.nombre, p.dni, p.genero, p.fecha_de_nacimiento, " +
            "p.email, p.estado AS estado_profesor FROM clase CL " + 
            "JOIN curso CU "           + "ON CL.id_curso = CU.id_curso " + 
            "JOIN carrera_materias CM " + "ON CU.id_carrera_materia = CM.id_carrera_materia " + 
            "JOIN carrera CA "         + "ON CM.id_carrera = CA.id_carrera " + 
            "JOIN materia M "          + "ON CM.id_materia = M.id_materia " + 
            "JOIN profesor P "          + "ON CU.id_profesor = P.id_profesor";
    
    private static final String INSERTCLASE = 
            "INSERT INTO clase(id_clase,id_curso, fecha, tema, estado) " + 
            "VALUES(?,?,?,?,?)";
    
    private static final String UPDATECLASE = 
            "UPDATE clase " + 
            "SET id_curso = ?, " +
            "fecha = ?, " +
            "tema = ?, " +
            "estado = ? "+
            "WHERE id_clase = ?";
    
    private static final String DELETECLASE = "DELETE FROM asistencia WHERE id_clase = ?";

    private static final String DELETECLASE2 = "DELETE FROM clase WHERE id_clase = ?";
    
    //private static final String EXISTECLASE = "SELECT count(*) AS existe FROM clase WHERE ID_CURSO=? AND FECHA like '?'";

    private static final String MAXCLASE = "SELECT ISNULL(MAX(ID_CLASE),0) + 1 AS MAXID FROM clase ";
    
    
    private ArrayList<Clase> armarObjetoClase(ResultSet rs){
        ArrayList<Clase> clases = new ArrayList<>();
        try {
            while (rs.next()){
                   Materia m = new Materia();
                   m.setIdMateria(rs.getInt("ID_MATERIA"));
                   m.setDescripcion(rs.getString("MATERIA"));

                   Carrera ca = new Carrera();
                   ca.setIdCarrera(rs.getInt("ID_CARRERA"));
                   ca.setDescripcion(rs.getString("CARRERA"));

                   CarreraMateria cm = new CarreraMateria();
                   cm.setIdCarreraMateria(rs.getInt("ID_CARRERA_MATERIA"));
                   cm.setMateria(m);
                   cm.setCarrera(ca);
                   cm.setAnioCarrera(rs.getInt("ANIO_CARRERA"));

                   Profesor p = new Profesor();
                   p.setIdProfesor(rs.getInt("ID_PROFESOR"));
                   p.setApellido(rs.getString("APELLIDO"));
                   p.setNombre(rs.getString("NOMBRE"));
                   p.setDni(rs.getInt("DNI"));
                   p.setGenero(rs.getString("GENERO"));
                   p.setFechaDeNacimiento(rs.getDate("FECHA_DE_NACIMIENTO"));
                   p.setEmail(rs.getString("EMAIL"));
                   p.setEstado(rs.getBoolean("ESTADO_PROFESOR"));

                   Curso cu = new Curso();
                   cu.setIdCurso(rs.getInt("ID_CURSO"));
                   cu.setCarreraMaterias(cm);
                   cu.setAnioLectivo(rs.getInt("ANIO_LECTIVO"));
                   cu.setCuatrimestre(rs.getString("CUATRIMESTRE"));
                   cu.setComision(rs.getString("COMISION"));
                   cu.setProfesor(p);
                   cu.setEstado(rs.getString("ESTADO_CURSO"));
                   cu.setCantidadInscriptos(rs.getInt("CANTIDAD_INSCRIPTOS"));
                   cu.setDesde(rs.getDate("DESDE"));
                   cu.setHasta(rs.getDate("HASTA"));

                   Clase cl = new Clase();
                   cl.setIdClase(rs.getInt("ID_CLASE"));
                   cl.setCurso(cu);
                   cl.setFecha(rs.getDate("FECHA"));
                   cl.setTema(rs.getString("TEMA"));
                   cl.setEstado(rs.getString("ESTADO"));
                   clases.add(cl);

               }

        } catch (SQLException ex) {
            Logger.getLogger(ClaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clases;
    }
    public  ArrayList<Clase> getClase(){


        ArrayList<Clase> clases = new ArrayList<>();

        try {

            Connection con = Conexion.getConexion();
            String query = SELECTCLASEFULL + " ORDER BY CL.FECHA";
            PreparedStatement ps;
            ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            clases=armarObjetoClase(rs);
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return clases;

    }
    

    public ArrayList<Clase> getClaseByFilters(Profesor profesor, Curso curso,Date desde, Date hasta, int anio){


        ArrayList<Clase> clases = new ArrayList<>();

        try {

            Connection con = Conexion.getConexion();
            String query = SELECTCLASEFULL + " WHERE ";
            
            if (profesor!=null){
                query=query + "cu.ID_PROFESOR = "+ profesor.getIdProfesor();
                if (curso!=null || desde!=null || hasta!=null || anio!=0){
                    query=query + " AND ";
                }
            }     
            if (curso!=null){
                query=query + "cl.ID_CURSO = "+ curso.getIdCurso();
                if (desde!=null || hasta!=null || anio!=0){
                    query=query + " AND ";
                }
            }
            if (desde!=null){
                query=query + "cl.FECHA >= '" + this.fechaSQL(desde) + "'";
                if (hasta!=null || anio!=0){
                    query=query + " AND ";
                }
            }
            if (hasta!=null){
                query=query + "cl.FECHA <= '" + this.fechaSQL(hasta) + "'";
                if (anio!=0){
                    query=query + " AND ";
                }
            }
            if (anio!=0){
                query=query + "cu.ANIO_LECTIVO = "+ anio;
            }
            query = query + " ORDER BY CL.FECHA";
            PreparedStatement ps;
            ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            clases=armarObjetoClase(rs);
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return clases;

    }


    
     public int insertClase(Clase cl){
        int retorno = 0;
        Connection con = null;
        try {
                con = Conexion.getConexion();
                con.setAutoCommit(false);
                int maxID = this.getMaxIDClase(con);      
                PreparedStatement ps = con.prepareStatement(INSERTCLASE);
                ps.setInt(1,maxID);
                ps.setInt(2,cl.getCurso().getIdCurso());
                ps.setDate(3,(new java.sql.Date(cl.getFecha().getTime())));                
                ps.setString(4,cl.getTema());
                ps.setString(5,cl.getEstado());
               
                retorno = ps.executeUpdate();
                con.commit();
                con.setAutoCommit(true);
                ps.close();
                con.close();

        } catch (SQLException ex) {
            try {
                Logger.getLogger(ClaseDAO.class.getName()).log(Level.SEVERE, null, ex);
                con.rollback();
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException ex1) {
                Logger.getLogger(ClaseDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return retorno;
    }
     
     public int updateClase(Clase cl){
        int retorno = 0;
        Connection con = null;
        try {
                con = Conexion.getConexion();
                PreparedStatement ps = con.prepareStatement(UPDATECLASE);
                

                ps.setInt(1,cl.getCurso().getIdCurso());
                ps.setDate(2,(new java.sql.Date(cl.getFecha().getTime())));                
                ps.setString(3,cl.getTema());
                ps.setString(4,cl.getEstado());
                ps.setInt(5,cl.getIdClase());
                
                retorno = ps.executeUpdate();
                ps.close();
                con.close();

        } catch (SQLException ex) {
            try {
                Logger.getLogger(ClaseDAO.class.getName()).log(Level.SEVERE, null, ex);
                con.rollback();
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException ex1) {
                Logger.getLogger(ClaseDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return retorno;
    }
    
        public int deleteClase(Clase cl){
        int retorno = -1;
        Connection con = null;
        try {
            con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(DELETECLASE);
            ps.setInt(1,cl.getIdClase());
            ps.executeUpdate();//execute update se usa para DML
            ps.close();
            ps=con.prepareStatement(DELETECLASE2);
            ps.setInt(1,cl.getIdClase());
            ps.executeUpdate();//execute update se usa para DML
            ps.close();
            con.close();
        }
        catch  (SQLException ex) {
            Logger.getLogger(ClaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        
        
        return retorno;
    }
        
        
        
    private int getMaxIDClase(Connection con) throws SQLException{
        int retorno = -1;
      
        PreparedStatement ps = con.prepareStatement(MAXCLASE);
        ResultSet rs = ps.executeQuery();
        rs.next();
        retorno = rs.getInt("MAXID");
        rs.close();
        ps.close();   
        
        return retorno;
    }
    /*
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
            Logger.getLogger(ClaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        
        
        return retorno;
    }
*/


        
    private String fechaSQL(Date d){
        String fecha=null;    
        if (d!=null){
            fecha = (new SimpleDateFormat("dd-MM-yyyy")).format(d);
        }
        return fecha;                
    }
}
