/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.lujan.asistencia.service;

import ar.com.lujan.asistencia.database.AlumnoDAO;
import ar.com.lujan.asistencia.modelo.Alumno;
import ar.com.lujan.asistencia.modelo.AlumnoTableModel;
import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 *
 * @author Lucho
 */
public class AlumnoRN {
    
    AlumnoDAO alumnoDAO = new AlumnoDAO();
    
    public AlumnoTableModel cargarGrilla(){
        
       return new AlumnoTableModel(alumnoDAO.getAlumno());
         
    }
    
    public AlumnoTableModel cargarGrillaByFilters(String busqueda,String busquedaEn){
        
       switch (busquedaEn){
           case "Todos" : return new AlumnoTableModel(alumnoDAO.getAlumnoByFilters(busqueda));
               
           case "Apellido" : return new AlumnoTableModel(alumnoDAO.getAlumnoByFilterApellido(busqueda));
               
           case "Nombre" : return new AlumnoTableModel(alumnoDAO.getAlumnoByFilterNombre(busqueda));
               
           case "DNI" : return new AlumnoTableModel(alumnoDAO.getAlumnoByFilterDNI(busqueda));
                    
           case "Genero" : return new AlumnoTableModel(alumnoDAO.getAlumnoByFilterGenero(busqueda));
           
           case "Email" : return new AlumnoTableModel(alumnoDAO.getAlumnoByFilterEmail(busqueda));
                         
           case "Fecha de Nacimiento" : return new AlumnoTableModel(alumnoDAO.getAlumnoByFilterFechaDeNacimiento(busqueda));
                          
       }
        return null;
            
    }
    public AlumnoTableModel cargarGrillaActivos(){
        
       return new AlumnoTableModel(alumnoDAO.getAlumnosActivos());
         
    }   
    
    public AlumnoTableModel cargarGrillaInactivos(){
        
       return new AlumnoTableModel(alumnoDAO.getAlumnosInactivos());
         
    }    
    
    public int cantidadDeCursos (Alumno a){
        int b=alumnoDAO.getCantidadDeCursosByAlumno(a);
        return b;
    }
    
    
    public void borrarAlumno(Alumno a){
        alumnoDAO.deleteAlumno(a);
       
    }
    
    
    public boolean existeDni (Alumno a){
        boolean b = false;
        if (alumnoDAO.isExisteDni(a)==true){
            b=true;
        }    
        return b;
    }
    
    public int agregarEditarAlumno(Alumno a,String operacion){
        int r=0;
        if (operacion.equals("AGREGAR")){
            r=alumnoDAO.insertAlumno(a);  
        }
        if (operacion.equals("EDITAR")){
            r=alumnoDAO.updateAlumno(a);  
        }        
        return r;
    }    
 
    
    public void formatoGrilla(JTable jtAlumnos){
        
        jtAlumnos.getColumnModel().getColumn(0).setMinWidth(0);
        jtAlumnos.getColumnModel().getColumn(0).setMaxWidth(0);
        jtAlumnos.getColumnModel().getColumn(1).setMinWidth(100);
        jtAlumnos.getColumnModel().getColumn(1).setMaxWidth(100);
        jtAlumnos.getColumnModel().getColumn(2).setMinWidth(100);
        jtAlumnos.getColumnModel().getColumn(2).setMaxWidth(100);
        jtAlumnos.getColumnModel().getColumn(3).setMinWidth(100);
        jtAlumnos.getColumnModel().getColumn(3).setMaxWidth(100);
        jtAlumnos.getColumnModel().getColumn(4).setMinWidth(80);
        jtAlumnos.getColumnModel().getColumn(4).setMaxWidth(80);
        jtAlumnos.getColumnModel().getColumn(5).setMinWidth(150);
        jtAlumnos.getColumnModel().getColumn(5).setMaxWidth(150);
        jtAlumnos.getColumnModel().getColumn(6).setMinWidth(200);
        jtAlumnos.getColumnModel().getColumn(6).setMaxWidth(200);
        jtAlumnos.getColumnModel().getColumn(7).setMinWidth(60);
        jtAlumnos.getColumnModel().getColumn(7).setMaxWidth(60);
     
    }

    
    
    
}
