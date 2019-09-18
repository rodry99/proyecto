/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.lujan.asistencia.modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author usuario
 */
public class AlumnoTableModel extends AbstractTableModel{ //la clase abstracta no se puede instanciar, por eso creamos una subclase
    
    private List<Alumno> alumnos; //Definir una variable de instancia llamada alumnos que va a tener la lista
    private static String[] COLUMNAS = {"ID","Apellido","Nombre","DNI","Género","Fecha de Nacimiento","E-mail","Estado"};
  
    //Variable de clase (MAYUSCULA)
    
    //Constructor
    public AlumnoTableModel (List<Alumno> alumnos){
        super();
        this.alumnos=alumnos;
            
    }
    
    //Metodos abstractos obligatorios
    @Override
    public int getRowCount() {
        return alumnos.size(); //cantidad de registros de la lista
    }

    @Override
    public int getColumnCount() {
         return COLUMNAS.length; //cantidad de valores del vector COLUMNAS
    }

    @Override //devolver una columna especifica de un registro especifico
    public Object getValueAt(int rowIndex, int columnIndex) {
            Object retorno = null;
            Alumno alumno = alumnos.get(rowIndex);
            switch (columnIndex){
            case 0: retorno = alumno.getID(); break;
            case 1: retorno = alumno.getApellido(); break;
            case 2: retorno = alumno.getNombre(); break;
            case 3: retorno = alumno.getDNI(); break;
            case 4: retorno = this.genero(alumno); break;
            case 5: retorno = this.fecha(alumno); break; 
            case 6: retorno = alumno.getEmail(); break;
            case 7: retorno = this.estado(alumno); break;
            }
            return retorno;    
    }
    
    private String genero(Alumno a){
        String g = new String();
        if (a.getGenero().equals("M")){
            g="Masculino";
        }
        if (a.getGenero().equals("F")){
            g="Femenino";
        }
        return g;
    }
    
    private String estado(Alumno alumno){
        String e = new String();
        if (alumno.isEstado()==true){
            e="Activo";
        }
        if (alumno.isEstado()==false){
            e="Inactivo";
        }
        return e;
    }    
    
    
    
    
    private String fecha(Alumno a){
        String fecha=null;    
        if (a.getFechaDeNacimiento()!=null){
            fecha = (new SimpleDateFormat("dd-MM-yyyy")).format(a.getFechaDeNacimiento());
        }
        return fecha;                
    }
    
    @Override // Sobreescribir los nombres de columnas
    public String getColumnName(int index){
        return COLUMNAS[index];
        
    }
    
            
   
    
    
}
