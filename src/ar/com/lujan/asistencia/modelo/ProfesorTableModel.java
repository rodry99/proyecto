/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.lujan.asistencia.modelo;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author usuario
 */
public class ProfesorTableModel extends AbstractTableModel{ //la clase abstracta no se puede instanciar, por eso creamos una subclase
    
    private List<Profesor> profesor; //Definir una variable de instancia llamada alumnos que va a tener la lista
    private static String[] COLUMNAS = {"ID","Apellido","Nombre","DNI","Género","Fecha de Nacimiento","E-mail","Estado"};
  
    //Variable de clase (MAYUSCULA)
    
    //Constructor
    public ProfesorTableModel (List<Profesor> profesor){
        super();
        this.profesor=profesor;
            
    }
    
    //Metodos abstractos obligatorios
    @Override
    public int getRowCount() {
        return profesor.size(); //cantidad de registros de la lista
    }

    @Override
    public int getColumnCount() {
         return COLUMNAS.length; //cantidad de valores del vector COLUMNAS
    }

    @Override //devolver una columna especifica de un registro especifico
    public Object getValueAt(int rowIndex, int columnIndex) {
            Object retorno = null;
            Profesor profesor = this.profesor.get(rowIndex);
            switch (columnIndex){
            case 0: retorno = profesor.getIdProfesor(); break;
            case 1: retorno = profesor.getApellido(); break;
            case 2: retorno = profesor.getNombre(); break;
            case 3: retorno = profesor.getDni(); break;
            case 4: retorno = profesor.getGenero(); break;
            case 5: retorno = profesor.getFechaDeNacimiento(); break;
            case 6: retorno = profesor.getEmail(); break;
            case 7: retorno = profesor.isEstado(); break;
            }
            return retorno;    
    }
    @Override // Sobreescribir los nombres de columnas
    public String getColumnName(int index){
        return COLUMNAS[index];
        
    }
    
            
   
    
    
}
