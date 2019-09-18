/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.lujan.asistencia.database;

import ar.com.lujan.asistencia.modelo.CarreraMateria;
import ar.com.lujan.asistencia.modelo.Carrera;
import ar.com.lujan.asistencia.modelo.Materia;
import ar.com.lujan.asistencia.modelo.Curso;
import ar.com.lujan.asistencia.modelo.Profesor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lledesma
 */
public class CursoDAO {

        private static final String LISTAR_CURSOS = "SELECT	C.ID_CURSO as ID_CURSO ,cm.ID_CARRERA_MATERIA as ID_CARRERA_MATERIA,CA.ID_CARRERA as ID_CARRERA\n"
            + "		,CA.DESCRIPCION as CARRERA,MA.ID_MATERIA as ID_MATERIA\n"
            + "		,MA.DESCRIPCION AS MATERIA,C.ANIO_LECTIVO ,C.CUATRIMESTRE\n"
            + "		,C.COMISION ,PR.ID_PROFESOR as ID_PROFESOR\n"
            + "		,PR.APELLIDO as APELLIDO_PROFESOR,PR.NOMBRE as NOMBRE_PROFESOR\n"
            + "		,C.ESTADO,C.CANTIDAD_INSCRIPTOS,C.DESDE,C.HASTA\n"
            + "FROM CURSO C\n"
            + "JOIN CARRERA_MATERIAS CM ON C.ID_CARRERA_MATERIA = CM.ID_CARRERA_MATERIA\n"
            + "JOIN CARRERA CA ON CA.ID_CARRERA = CM.ID_CARRERA\n"
            + "JOIN MATERIA MA ON MA.ID_MATERIA = CM.ID_MATERIA\n"
            + "JOIN PROFESOR PR ON PR.ID_PROFESOR = C.ID_PROFESOR ";

    private static final String INSERTAR_CURSO = "INSERT INTO CURSO (ID_CURSO, ID_CARRERA_MATERIA, ANIO_LECTIVO, CUATRIMESTRE, COMISION, ID_PROFESOR, ESTADO, CANTIDAD_INSCRIPTOS, DESDE, HASTA)"
            + "                                        VALUES(?,?,?,?,?,?,?,?,?,?)";

    private static final String BORRAR_CURSO = "DELETE FROM CURSO WHERE ID_CURSO = ?";

    private static final String MODIFICAR_CURSO = "UPDATE CURSO "
                                        + "        SET ID_CARRERA_MATERIA = ? ,ANIO_LECTIVO = ?,CUATRIMESTRE = ?,COMISION = ?,ID_PROFESOR = ?,ESTADO = ? ,CANTIDAD_INSCRIPTOS = ?,DESDE = ?,HASTA = ?"
                                        + "        WHERE ID_CURSO = ?";

    private static final String LISTAR_CURSOS_BY_ID = "WHERE C.ID_CURSO = ?";

    private static final String LISTAR_CURSOS_BY_CARRERA = "WHERE CA.ID_CARRERA = ?";

    private static final String LISTAR_CURSOS_BY_MATERIA = "WHERE MA.ID_MATERIA = ?";

    private static final String LISTAR_CURSOS_BY_CARRERA_MATERIA = "WHERE MA.ID_MATERIA = ? AND CA.ID_CARRERA = ?";

    private static final String LISTAR_CURSOS_BY_CARRERAMATERIA = "WHERE C.ID_CARRERA_MATERIA = ? ";

    private static final String LISTAR_CURSOS_BY_PROFESOR = "WHERE PR.ID_PROFESOR = ?";

    private static final String LISTAR_CURSOS_BY_ESTADO = "WHERE C.ESTADO = '?'";
    
    private static final String EXISTE_CURSO_UQ = "SELECT COUNT(1) as CANTIDAD FROM CURSO WHERE ID_CARRERA_MATERIA = ? AND ANIO_LECTIVO = ? AND CUATRIMESTRE = ? AND COMISION = ?";
    
    private static final String EXISTE_FK= "SELECT COUNT(1) AS CANTIDAD FROM CURSO_ALUMNOS CAL LEFT JOIN CURSO_DIAS CDI ON CAL.ID_CURSO = CDI.ID_CURSO WHERE CAL.ID_CURSO = ?";
    
    private int getMaxIdCurso(Connection con) throws SQLException {
        int retorno = -1;

        PreparedStatement ps = con.prepareStatement("SELECT ISNULL(MAX(ID_CURSO),0) + 1 AS MAXID FROM CURSO");
        ResultSet rs = ps.executeQuery();
        rs.next();
        retorno = rs.getInt("MAXID");
        rs.close();
        ps.close();

        return retorno;
    }

    /**
     * *****************************************FIN MAX
     * ID**************************************************
     */
    /**
     * *****************************************INSERT**************************************************
     */
    public int insertarCurso(Curso c) {
        int retorno = 0;
        Connection con = null;
        try {
            con = Conexion.getConexion();
            con.setAutoCommit(false);
            int maxID = this.getMaxIdCurso(con);

            PreparedStatement ps = con.prepareStatement(INSERTAR_CURSO);
            ps.setInt(1, maxID);//primer parametro
            ps.setInt(2, c.getCarreraMaterias().getIdCarreraMateria());// obtiene el id_carreraMateria a travez del combo seleccionado en la pantalla
            ps.setInt(3, c.getAnioLectivo());
            ps.setString(4, c.getCuatrimestre());
            ps.setString(5, c.getComision());
            ps.setInt(6, c.getProfesor().getIdProfesor());
            ps.setString(7, c.getEstado());
            ps.setInt(8, 0);// la cantidad de inscriptos se setea en 0
            ps.setDate(9, c.getDesde());
            ps.setDate(10, c.getHasta());

            //curso.getCarreraMaterias().getMateria().getId()
            retorno = ps.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
            ps.close();
            con.close();

        } catch (SQLException ex) {
            try {
                Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
                con.rollback();
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException ex1) {
                Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return retorno;
    }

    /**
     * **********************************FIN INSERT*************************************************
     */
    /**
    /**
     * *****************************DELETE*******************************************
     */
    public int BorrarCurso(Curso c) {
        int retorno = 0;
        Connection con = null;
        try {
            con = Conexion.getConexion();
            con.setAutoCommit(false);

            PreparedStatement ps = con.prepareStatement(BORRAR_CURSO);
            ps.setInt(1, c.getIdCurso());

            retorno = ps.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
            ps.close();
            con.close();

        } catch (SQLException ex) {
            try {
                Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
                con.rollback();
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException ex1) {
                Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return retorno;
    }

    /**
     * ************************FIN DELETE**************************************
     */
    /**
     * ***************************UPDATE***************************************
     */
    public int ActualizarCurso(Curso c) {
        int retorno = 0;
        Connection con = null;
        try {
            con = Conexion.getConexion();
            con.setAutoCommit(false);

            PreparedStatement ps = con.prepareStatement(MODIFICAR_CURSO);

            ps.setInt(1, c.getCarreraMaterias().getIdCarreraMateria());// obtiene el id_carreraMateria a travez del combo seleccionado en la pantalla
            ps.setInt(2, c.getAnioLectivo());
            ps.setString(3, c.getCuatrimestre());
            ps.setString(4, c.getComision());
            ps.setInt(5, c.getProfesor().getIdProfesor());
            ps.setString(6, c.getEstado());
            ps.setInt(7, 0);// la cantidad de inscriptos se setea en 0
            ps.setDate(8, c.getDesde());
            ps.setDate(9, c.getHasta());
            ps.setInt(10, c.getIdCurso());

            retorno = ps.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
            ps.close();
            con.close();

        } catch (SQLException ex) {
            try {
                Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
                con.rollback();
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException ex1) {
                Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return retorno;
    }

    /**
     * *****************************FIN UPDATE***************************************
     */
    /**
     * *******************************LISTAR CURSO***********************************
     */
    public ArrayList<Curso> getCursos() {
        ArrayList<Curso> cursos = new ArrayList<>();
        try (Connection con = Conexion.getConexion();
                PreparedStatement ps = con.prepareStatement(LISTAR_CURSOS);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {

                Curso c = null;
                for (Curso curso : cursos) {
                    if (curso.getIdCurso() == rs.getInt("ID_CURSO")) {
                        c = curso;
                        break;
                    }
                }
                if (c == null) {
                    c = new Curso();
                }

                //agregar una materia
                CarreraMateria cm = new CarreraMateria();
                Carrera ca = new Carrera();
                Materia ma = new Materia();
                Profesor pr = new Profesor();

                //c.setCarreraMaterias();                    
                //hace la relacion entre las clases
                cm.setIdCarreraMateria(rs.getInt("ID_CARRERA_MATERIA"));
                cm.setCarrera(ca);
                cm.setMateria(ma);
                c.setCarreraMaterias(cm);
                c.setProfesor(pr);
                //

                c.setIdCurso(rs.getInt("ID_CURSO"));
              
                ca.setIdCarrera(rs.getInt("ID_CARRERA"));

                ca.setDescripcion(rs.getString("CARRERA"));
                ma.setIdMateria(rs.getInt("ID_MATERIA"));

                ma.setDescripcion(rs.getString("MATERIA"));
                c.setAnioLectivo(rs.getInt("ANIO_LECTIVO"));
                c.setCuatrimestre(rs.getString("CUATRIMESTRE"));
                c.setComision(rs.getString("COMISION"));
                pr.setIdProfesor(rs.getInt("ID_PROFESOR"));
                pr.setApellido(rs.getString("APELLIDO_PROFESOR"));
                pr.setNombre(rs.getString("NOMBRE_PROFESOR"));
                c.setEstado(rs.getString("ESTADO"));
                c.setCantidadInscriptos(rs.getInt("CANTIDAD_INSCRIPTOS"));
                c.setDesde(rs.getDate("DESDE"));
                c.setHasta(rs.getDate("HASTA"));

                //c.setId_curso(c);
                cursos.add(c);
            }

            rs.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cursos;
    }

    /**
     * ***************************FIN LISTAR CURSO*****************************************
     */
    /**
     * ****************************LISTAR CURSO POR ID************************************
     */
    public ArrayList<Curso> getCursosById() {
        ArrayList<Curso> cursos = new ArrayList<>();
        Curso c = new Curso();
        try {
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(LISTAR_CURSOS + LISTAR_CURSOS_BY_ID);
            ps.setInt(1, c.getIdCurso());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                //Relacionar 
                //Curso c = new Curso();
                CarreraMateria cm = new CarreraMateria();
                Carrera ca = new Carrera();
                Materia ma = new Materia();
                Profesor pr = new Profesor();

                //c.setCarreraMaterias();                    
                //hace la relacion entre las clases
                cm.setCarrera(ca);
                cm.setMateria(ma);
                c.setCarreraMaterias(cm);
                c.setProfesor(pr);
                //

                c.setIdCurso(rs.getInt("ID_CURSO"));
                ca.setIdCarrera(rs.getInt("ID_CARRERA"));

                ca.setDescripcion(rs.getString("CARRERA"));
                ma.setIdMateria(rs.getInt("ID_MATERIA"));

                ma.setDescripcion(rs.getString("MATERIA"));
                c.setAnioLectivo(rs.getInt("ANIO_LECTIVO"));
                c.setCuatrimestre(rs.getString("CUATRIMESTRE"));
                c.setComision(rs.getString("COMISION"));
                pr.setIdProfesor(rs.getInt("ID_PROFESOR"));
                pr.setApellido(rs.getString("APELLIDO_PROFESOR"));
                pr.setNombre(rs.getString("NOMBRE_PROFESOR"));
                c.setEstado(rs.getString("ESTADO"));
                c.setCantidadInscriptos(rs.getInt("CANTIDAD_INSCRIPTOS"));
                c.setDesde(rs.getDate("DESDE"));
                c.setHasta(rs.getDate("HASTA"));

                //c.setId_curso(c);
                cursos.add(c);
            }

            rs.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cursos;
    }

    /**********************FIN LISTAR CURSO BY ID*************************/
    
    /********************LISTAR CURSO BY CARRERA*********************/
    public ArrayList<Curso> getCursosByCarrera(Carrera carrera) {
        ArrayList<Curso> cursos = new ArrayList<>();

        try {
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(LISTAR_CURSOS + LISTAR_CURSOS_BY_CARRERA);
            ps.setInt(1, carrera.getIdCarrera());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Curso c = new Curso();
                CarreraMateria cm = new CarreraMateria();
                Carrera ca = new Carrera();
                Materia ma = new Materia();
                Profesor pr = new Profesor();

                //hace la relacion entre las clases
                cm.setCarrera(ca);
                cm.setMateria(ma);
                c.setCarreraMaterias(cm);
                c.setProfesor(pr);
                //

                c.setIdCurso(rs.getInt("ID_CURSO"));
                ca.setIdCarrera(rs.getInt("ID_CARRERA"));
                ca.setDescripcion(rs.getString("CARRERA"));
                ma.setIdMateria(rs.getInt("ID_MATERIA"));
                ma.setDescripcion(rs.getString("MATERIA"));
                c.setAnioLectivo(rs.getInt("ANIO_LECTIVO"));
                c.setCuatrimestre(rs.getString("CUATRIMESTRE"));
                c.setComision(rs.getString("COMISION"));
                pr.setIdProfesor(rs.getInt("ID_PROFESOR"));
                pr.setApellido(rs.getString("APELLIDO_PROFESOR"));
                pr.setNombre(rs.getString("NOMBRE_PROFESOR"));
                c.setEstado(rs.getString("ESTADO"));
                c.setCantidadInscriptos(rs.getInt("CANTIDAD_INSCRIPTOS"));
                c.setDesde(rs.getDate("DESDE"));
                c.setHasta(rs.getDate("HASTA"));

                //c.setId_curso(c);
                cursos.add(c);
            }

            rs.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cursos;
    }

    /*************************FIN LISTAR CURSO BY CARRERA*******************/
    
    /********************LISTAR CURSO BY MATERIA***************************/
    public ArrayList<Curso> getCursosByMateria(Materia materia) throws SQLException {
        ArrayList<Curso> cursos = new ArrayList<>();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(LISTAR_CURSOS + LISTAR_CURSOS_BY_MATERIA);
            ps.setInt(1, materia.getIdMateria());
            rs = ps.executeQuery();

            while (rs.next()) {
                Curso c = new Curso();
                CarreraMateria cm = new CarreraMateria();
                Carrera ca = new Carrera();
                Materia ma = new Materia();
                Profesor pr = new Profesor();

                //hace la relacion entre las clases
                cm.setCarrera(ca);
                cm.setMateria(ma);
                c.setCarreraMaterias(cm);
                c.setProfesor(pr);
                //

                c.setIdCurso(rs.getInt("ID_CURSO"));
                ca.setIdCarrera(rs.getInt("ID_CARRERA"));
                ca.setDescripcion(rs.getString("CARRERA"));
                ma.setIdMateria(rs.getInt("ID_MATERIA"));
                ma.setDescripcion(rs.getString("MATERIA"));
                c.setAnioLectivo(rs.getInt("ANIO_LECTIVO"));
                c.setCuatrimestre(rs.getString("CUATRIMESTRE"));
                c.setComision(rs.getString("COMISION"));
                pr.setIdProfesor(rs.getInt("ID_PROFESOR"));
                pr.setApellido(rs.getString("APELLIDO_PROFESOR"));
                pr.setNombre(rs.getString("NOMBRE_PROFESOR"));
                c.setEstado(rs.getString("ESTADO"));
                c.setCantidadInscriptos(rs.getInt("CANTIDAD_INSCRIPTOS"));
                c.setDesde(rs.getDate("DESDE"));
                c.setHasta(rs.getDate("HASTA"));

                //c.setId_curso(c);
                cursos.add(c);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return cursos;
    }

    /******************FIN LISTAR CURSO BY MATERIA*************************/
    /*******************LISTAR CURSO BY CARRERA MATERIA************************/
    public ArrayList<Curso> getCursosByCarreraMateria(Carrera carrera, Materia materia) {
        ArrayList<Curso> cursos = new ArrayList<>();

        try {
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(LISTAR_CURSOS + LISTAR_CURSOS_BY_CARRERA_MATERIA);
            ps.setInt(1, carrera.getIdCarrera());
            ps.setInt(2, materia.getIdMateria());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Curso c = new Curso();
                CarreraMateria cm = new CarreraMateria();
                Carrera ca = new Carrera();
                Materia ma = new Materia();
                Profesor pr = new Profesor();

                //hace la relacion entre las clases
                cm.setCarrera(ca);
                cm.setMateria(ma);
                c.setCarreraMaterias(cm);
                c.setProfesor(pr);
                //

                c.setIdCurso(rs.getInt("ID_CURSO"));
                ca.setIdCarrera(rs.getInt("ID_CARRERA"));
                ca.setDescripcion(rs.getString("CARRERA"));
                ma.setIdMateria(rs.getInt("ID_MATERIA"));
                ma.setDescripcion(rs.getString("MATERIA"));
                c.setAnioLectivo(rs.getInt("ANIO_LECTIVO"));
                c.setCuatrimestre(rs.getString("CUATRIMESTRE"));
                c.setComision(rs.getString("COMISION"));
                pr.setIdProfesor(rs.getInt("ID_PROFESOR"));
                pr.setApellido(rs.getString("APELLIDO_PROFESOR"));
                pr.setNombre(rs.getString("NOMBRE_PROFESOR"));
                c.setEstado(rs.getString("ESTADO"));
                c.setCantidadInscriptos(rs.getInt("CANTIDAD_INSCRIPTOS"));
                c.setDesde(rs.getDate("DESDE"));
                c.setHasta(rs.getDate("HASTA"));
                cursos.add(c);
            }

            rs.close();
            ps.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cursos;
    }

    /***********FIN LISTAR CURSO BY CARRERA MATERIA*************************/
    
    /****************INICIO LISTAR CURSO BY PROFESOR********************/
    public ArrayList<Curso> getCursosByProfesor(Profesor profesor) throws SQLException {
        ArrayList<Curso> cursos = new ArrayList<>();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(LISTAR_CURSOS + LISTAR_CURSOS_BY_PROFESOR);
            ps.setInt(1, profesor.getIdProfesor());            
            rs = ps.executeQuery();

            while (rs.next()) {
                Curso c = new Curso();
                CarreraMateria cm = new CarreraMateria();
                Carrera ca = new Carrera();
                Materia ma = new Materia();
                Profesor pr = new Profesor();

                //hace la relacion entre las clases
                cm.setCarrera(ca);
                cm.setMateria(ma);
                c.setCarreraMaterias(cm);
                c.setProfesor(pr);
                //

                c.setIdCurso(rs.getInt("ID_CURSO"));
                ca.setIdCarrera(rs.getInt("ID_CARRERA"));
                ca.setDescripcion(rs.getString("CARRERA"));
                ma.setIdMateria(rs.getInt("ID_MATERIA"));
                ma.setDescripcion(rs.getString("MATERIA"));
                c.setAnioLectivo(rs.getInt("ANIO_LECTIVO"));
                c.setCuatrimestre(rs.getString("CUATRIMESTRE"));
                c.setComision(rs.getString("COMISION"));
                pr.setIdProfesor(rs.getInt("ID_PROFESOR"));
                pr.setApellido(rs.getString("APELLIDO_PROFESOR"));
                pr.setNombre(rs.getString("NOMBRE_PROFESOR"));
                c.setEstado(rs.getString("ESTADO"));
                c.setCantidadInscriptos(rs.getInt("CANTIDAD_INSCRIPTOS"));
                c.setDesde(rs.getDate("DESDE"));
                c.setHasta(rs.getDate("HASTA"));

                //c.setId_curso(c);
                cursos.add(c);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return cursos;
    }

    
    /******************FIN LISTAR CURSO BY PROFESOR *******************************/
    
       
public ArrayList<Curso> getCursosByCarreraMateria( CarreraMateria carreraMateria) {
        ArrayList<Curso> cursos = new ArrayList<>();
        try {
            
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(LISTAR_CURSOS + LISTAR_CURSOS_BY_CARRERAMATERIA);              
            ps.setInt(1, carreraMateria.getIdCarreraMateria());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
                Curso c=null;
                for (Curso curso : cursos) {
                    if(curso.getIdCurso() == rs.getInt("ID_CURSO")){
                        c=curso;
                        break;
                    }
                }
                if(c==null){
                 c = new Curso();   
                }
                
                //agregar una materia
                
                 
                CarreraMateria cm = new CarreraMateria();
                Carrera ca = new Carrera();
                Materia ma = new Materia();
                Profesor pr = new Profesor();

                //c.setCarreraMaterias();                    
                //hace la relacion entre las clases
                cm.setCarrera(ca);
                cm.setMateria(ma);
                c.setCarreraMaterias(cm);
                c.setProfesor(pr);
                //

                c.setIdCurso(rs.getInt("ID_CURSO"));
                ca.setIdCarrera(rs.getInt("ID_CARRERA"));

                ca.setDescripcion(rs.getString("CARRERA"));
                ma.setIdMateria(rs.getInt("ID_MATERIA"));

                ma.setDescripcion(rs.getString("MATERIA"));
                c.setAnioLectivo(rs.getInt("ANIO_LECTIVO"));
                c.setCuatrimestre(rs.getString("CUATRIMESTRE"));
                c.setComision(rs.getString("COMISION"));                
                pr.setIdProfesor(rs.getInt("ID_PROFESOR"));
                pr.setApellido(rs.getString("APELLIDO_PROFESOR"));
                pr.setNombre(rs.getString("NOMBRE_PROFESOR"));
                c.setEstado(rs.getString("ESTADO"));
                c.setCantidadInscriptos(rs.getInt("CANTIDAD_INSCRIPTOS"));
                c.setDesde(rs.getDate("DESDE"));
                c.setHasta(rs.getDate("HASTA"));

                
                //c.setId_curso(c);
                cursos.add(c);
            }

            rs.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cursos;
    }    

    /*******************LISTAR EXISTE CURSO************************/
    public int ExisteCurso(Curso cu) {
        int retorno = 0;
        try {
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(EXISTE_CURSO_UQ);
            ps.setInt(1, cu.getCarreraMaterias().getIdCarreraMateria());
            ps.setInt(2, cu.getAnioLectivo());
            ps.setString(3, cu.getCuatrimestre());
            ps.setString(4, cu.getComision());
            ResultSet rs = ps.executeQuery();

            
            rs.next();
            retorno = rs.getInt("CANTIDAD");
            rs.close();
            ps.close();

        

        } catch (SQLException ex) {
            Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
        };
        return retorno;
    }

    /***********FIN EXISTE CURSO*************************/
    
    /*******************LISTAR FK A CURSO************************/
    public int ExisteFkACurso(Curso cu) {
        int retorno = 0;
        try {
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(EXISTE_FK);
            ps.setInt(1, cu.getIdCurso());
            ResultSet rs = ps.executeQuery();

            
            rs.next();
            retorno = rs.getInt("CANTIDAD");
            rs.close();
            ps.close();

        

        } catch (SQLException ex) {
            Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
        };
        return retorno;
    }

    /***********FIN EXISTE FK A CURSO*************************/
    
}
