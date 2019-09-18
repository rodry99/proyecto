/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.lujan.asistencia.service;

import ar.com.lujan.asistencia.database.ClaseDAO;
import ar.com.lujan.asistencia.modelo.Clase;
import ar.com.lujan.asistencia.modelo.ClaseTableModel;
import ar.com.lujan.asistencia.modelo.Curso;
import ar.com.lujan.asistencia.modelo.Profesor;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Lucho
 */
public class ClaseRN {
    
    ClaseDAO claseDAO = new ClaseDAO();
    ArrayList<Clase> clases = new ArrayList<>();
    
    public ClaseTableModel cargarGrilla(){
       clases=claseDAO.getClase();
       return (new ClaseTableModel (clases));
    }
 
    public ClaseTableModel cargarGrillaByFilter(Profesor p, Curso cu, Date desde, Date hasta, int anio){
       clases=claseDAO.getClaseByFilters(p, cu, desde, hasta, anio);
       return (new ClaseTableModel(clases));
    }
    
    public DefaultComboBoxModel llenarComboProfesor(){
        boolean repetido = false;
        ArrayList<Profesor> profesores = new ArrayList<>(); //crea un array de profesores
        for(Clase cl : clases) { //recorre todas las clases de la grilla
            for (Profesor p: profesores){ //por cada objeto del array
                if (cl.getCurso().getProfesor().getIdProfesor()==p.getIdProfesor()){ //si esta repetido
                    repetido = true; //enciende el flag y no lo agrega
                }
            }//al finalizar la recorrida por todo el array
            if (repetido==false){ //si no estaba repetido
                profesores.add(cl.getCurso().getProfesor());//agrega el objeto al array
            }
            repetido=false;//inicializa la variable, para volver a recorrer con un profesor de otra clase....
        }
        return (new DefaultComboBoxModel(profesores.toArray()));
    }
    
    public DefaultComboBoxModel llenarComboCurso(){
        ArrayList<Curso> cursos = new ArrayList<>(); //crea un array de objetos
        boolean repetido = false;
        for(Clase cl : clases) { //recorre todas las clases de la grilla
            for (Curso cu: cursos){ //por cada objeto del array
                if (cl.getCurso().getIdCurso()==cu.getIdCurso()){ //si esta repetido
                    repetido = true; //enciende el flag
                }
            }//al finalizar la recorrida por todo el array
            if (repetido==false){ //si no estaba repetido
                cursos.add(cl.getCurso());//agrega el objeto al array
            }
            repetido=false;//inicializa la variable, para volver a recorrer con un profesor de otra clase....
        } 
        return (new DefaultComboBoxModel(cursos.toArray()));
    }
    
    public DefaultComboBoxModel llenarComboAnio(){
         boolean repetido = false; //variable flag para saber si el objeto esta repetido cuando recorramos          
        
        ArrayList<Integer> anios; //crea un array de profesores, donde vamos a guardar todos los profe
        anios = new ArrayList<>();
        for(Clase cl : clases) { //recorre todas las clases de la grilla
            for (Integer anio: anios){ //por cada objeto del array
                if (cl.getCurso().getAnioLectivo()==anio){ //si esta repetido
                    repetido = true; //enciende el flag
                }
            }//al finalizar la recorrida por todo el array
            if (repetido==false){ //si no estaba repetido
                anios.add(cl.getCurso().getAnioLectivo());//agrega el objeto al array
            }
            repetido=false;//inicializa la variable, para volver a recorrer con un profesor de otra clase....
        }
        return (new DefaultComboBoxModel(anios.toArray()));
    }
    
    public void borrarClase(Clase a){
        claseDAO.deleteClase(a);
       
    }
    
    
    
    public int agregarEditarClase(Clase a,String operacion){
        int r=0;
        if (operacion.equals("AGREGAR")){
            r=claseDAO.insertClase(a);  
        }
        if (operacion.equals("EDITAR")){
            r=claseDAO.updateClase(a);  
        }        
        return r;
    }    
 
    

    
    
 
    
        public void formatoGrilla(JTable jtClases){
        jtClases.getColumnModel().getColumn(0).setMinWidth(0); 
        jtClases.getColumnModel().getColumn(0).setMaxWidth(0);
        jtClases.getColumnModel().getColumn(1).setMinWidth(0);
        jtClases.getColumnModel().getColumn(1).setMaxWidth(0);
        jtClases.getColumnModel().getColumn(2).setMinWidth(75);
        jtClases.getColumnModel().getColumn(3).setMinWidth(240);
        jtClases.getColumnModel().getColumn(4).setMinWidth(0);
        jtClases.getColumnModel().getColumn(4).setMaxWidth(0);
        jtClases.getColumnModel().getColumn(5).setMinWidth(0);
        jtClases.getColumnModel().getColumn(5).setMaxWidth(0);
        jtClases.getColumnModel().getColumn(6).setMinWidth(0);
        jtClases.getColumnModel().getColumn(6).setMaxWidth(0);
        jtClases.getColumnModel().getColumn(7).setMinWidth(0);
        jtClases.getColumnModel().getColumn(7).setMaxWidth(0);
        jtClases.getColumnModel().getColumn(8).setMinWidth(0);
        jtClases.getColumnModel().getColumn(8).setMaxWidth(0);
        jtClases.getColumnModel().getColumn(9).setMinWidth(0);
        jtClases.getColumnModel().getColumn(9).setMaxWidth(0);
        jtClases.getColumnModel().getColumn(10).setMinWidth(0);
        jtClases.getColumnModel().getColumn(10).setMaxWidth(0);
        jtClases.getColumnModel().getColumn(11).setMinWidth(0);
        jtClases.getColumnModel().getColumn(11).setMaxWidth(0);
        jtClases.getColumnModel().getColumn(12).setMinWidth(0);
        jtClases.getColumnModel().getColumn(12).setMaxWidth(0);
        jtClases.getColumnModel().getColumn(13).setMinWidth(0);
        jtClases.getColumnModel().getColumn(13).setMaxWidth(0);
        jtClases.getColumnModel().getColumn(14).setMinWidth(0);
        jtClases.getColumnModel().getColumn(14).setMaxWidth(0);
        jtClases.getColumnModel().getColumn(15).setMinWidth(0);
        jtClases.getColumnModel().getColumn(15).setMaxWidth(0);
        jtClases.getColumnModel().getColumn(16).setMinWidth(0);
        jtClases.getColumnModel().getColumn(16).setMaxWidth(0);
        jtClases.getColumnModel().getColumn(17).setMinWidth(0);
        jtClases.getColumnModel().getColumn(17).setMaxWidth(0);
        jtClases.getColumnModel().getColumn(18).setMinWidth(0);
        jtClases.getColumnModel().getColumn(18).setMaxWidth(0);
        jtClases.getColumnModel().getColumn(19).setMinWidth(0);
        jtClases.getColumnModel().getColumn(19).setMaxWidth(0);
        jtClases.getColumnModel().getColumn(20).setMinWidth(130);
        jtClases.getColumnModel().getColumn(21).setMinWidth(0);
        jtClases.getColumnModel().getColumn(21).setMaxWidth(0);
        jtClases.getColumnModel().getColumn(22).setMinWidth(150);
        jtClases.getColumnModel().getColumn(23).setMinWidth(0);
        jtClases.getColumnModel().getColumn(23).setMaxWidth(0);
        jtClases.getColumnModel().getColumn(24).setMinWidth(0);
        jtClases.getColumnModel().getColumn(24).setMaxWidth(0);
        jtClases.getColumnModel().getColumn(25).setMinWidth(0);
        jtClases.getColumnModel().getColumn(25).setMaxWidth(0);
        jtClases.getColumnModel().getColumn(26).setMinWidth(0);
        jtClases.getColumnModel().getColumn(26).setMaxWidth(0);
        jtClases.getColumnModel().getColumn(27).setMinWidth(0);
        jtClases.getColumnModel().getColumn(27).setMaxWidth(0);
        jtClases.getColumnModel().getColumn(28).setMinWidth(0);
        jtClases.getColumnModel().getColumn(28).setMaxWidth(0);
        jtClases.getColumnModel().getColumn(29).setMinWidth(0);
        jtClases.getColumnModel().getColumn(29).setMaxWidth(0);
        jtClases.getColumnModel().getColumn(30).setMinWidth(0);
        jtClases.getColumnModel().getColumn(30).setMaxWidth(0);
        jtClases.getColumnModel().getColumn(31).setMinWidth(130);
        jtClases.getColumnModel().getColumn(32).setMinWidth(50);
        jtClases.setRowSorter((new TableRowSorter<>(jtClases.getModel()))); //habilitar el ordenador
    }
 
        
        
    
}
