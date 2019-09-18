/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.lujan.asistencia.service;

import ar.com.lujan.asistencia.database.ProfesorDAO;
import ar.com.lujan.asistencia.modelo.Profesor;
import javax.swing.JOptionPane;

/**
 *
 * @author Lucho
 */
public class ProfesorRN {
    
    ProfesorDAO profesorDAO = new ProfesorDAO();
    
    
    public void borrarProfesor(Profesor p){
        
        int confirmDialog=JOptionPane.showConfirmDialog(null,"¿Confirma borrado: " +
            p.getIdProfesor() + " - " + p.getNombre() + " " + p.getApellido()+ "?","Borrar Profesor",JOptionPane.YES_NO_OPTION);
            if(confirmDialog == JOptionPane.YES_OPTION){
                 profesorDAO.deleteProfesor(p);
            }
        
    }
    
    public int agregarEditarProfesor(Profesor p,String operacion){
        int r=0;
        if (operacion.equals("AGREGAR")){
            r=profesorDAO.insertProfesor(p);  
        }
        if (operacion.equals("EDITAR")){
            r=profesorDAO.updateProfesor(p);  
        }        return r;
    }   
    
}
       
    

    
    
    
    
    
    
    

