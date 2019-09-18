/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.lujan.asistencia.service;

import ar.com.lujan.asistencia.database.CursoDiaDAO;
import ar.com.lujan.asistencia.modelo.CursoDias;
import static java.awt.PageAttributes.MediaType.C;
import javax.swing.JOptionPane;

/**
 *
 * @author carlos cerrizuela
 */
public class CursoDiaRN {
    
    CursoDiaDAO cursoDiaDAO = new CursoDiaDAO();
    
    
    public void borrarCursoDIa(CursoDias c){
        
        int confirmDialog=JOptionPane.showConfirmDialog(null,"¿Confirma borrado: " +
            "ID:" + c.getIdCurso() + " -- " + "?","Borrar Curso dia",JOptionPane.YES_NO_OPTION);
            if(confirmDialog == JOptionPane.YES_OPTION){
                 cursoDiaDAO.deleteCursoDia(c);
            }
        
    }
    
    public int agregarEditarCursoDia(CursoDias c,String operacion){
        int r=0;
        if (operacion.equals("AGREGAR")){
            r=cursoDiaDAO.insertCursodia(c);  
        }
        if (operacion.equals("EDITAR")){
            r=cursoDiaDAO.updateCursodia(c);  
        }        return r;
    }   
    
    
    
    
    
    
    
    
    
    
    
    
    
}
