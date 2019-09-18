/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.lujan.asistencia.modelo;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author usuario
 */
public class ClaseTableModel extends AbstractTableModel{ //la clase abstracta no se puede instanciar, por eso creamos una subclase
    
    private List<Clase> clases; //Definir una variable de instancia llamada alumnos que va a tener la lista
    private static String[] COLUMNAS = {"ID", "cl.id_curso", "Fecha", "Tema", "cl.estado", "cu.id_curso", 
        "cu.id_carrera_materia", "cu.anio_lectivo", "cu.cuatrimestre", "cu.comision", "cu.id_profesor", "cu.estado",
        "cu.cantidad_inscriptos", "cu.desde", "cu.hasta", "cm.id_carrera_materia", "cm.id_carrera", "cm.id_materia",
        "cm.anio_carrera", "ca.id_carrera", "Carrera", "m.id_materia", "Materia", "p.id_profesor",
        "p.apellido", "p.nombre", "p.dni", "p.genero", "p.fecha_de_nacimiento", "p.email", "p.estado","Profesor", "Año" };
  
    //Variable de clase (MAYUSCULA)
    
    //Constructor
    public ClaseTableModel (List<Clase> clases){
        super();
        this.clases=clases;
            
    }
    
    //Metodos abstractos obligatorios
    @Override
    public int getRowCount() {
        return clases.size(); //cantidad de registros de la lista
    }

    @Override
    public int getColumnCount() {
         return COLUMNAS.length; //cantidad de valores del vector COLUMNAS
    }

    @Override //devolver una columna especifica de un registro especifico
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object retorno = null;
        Clase clase = clases.get(rowIndex);
        
        switch (columnIndex){
            case 0: retorno = clase.getIdClase(); break;
            case 1: retorno = clase.getCurso().getIdCurso(); break;
            case 2: retorno = this.fecha(clase); break;
            case 3: retorno = clase.getTema(); break;
            case 4: retorno = clase.getEstado(); break;
            case 5: retorno = clase.getCurso().getIdCurso(); break;
            case 6: retorno = clase.getCurso().getCarreraMaterias().getIdCarreraMateria(); break;
            case 7: retorno = clase.getCurso().getAnioLectivo(); break;
            case 8: retorno = clase.getCurso().getCuatrimestre(); break;
            case 9: retorno = clase.getCurso().getComision(); break;
            case 10: retorno = clase.getCurso().getProfesor().getIdProfesor(); break;
            case 11: retorno = clase.getCurso().getEstado(); break;
            case 12: retorno = clase.getCurso().getCantidadInscriptos(); break;
            case 13: retorno = clase.getCurso().getDesde(); break;
            case 14: retorno = clase.getCurso().getHasta(); break;
            case 15: retorno = clase.getCurso().getCarreraMaterias().getIdCarreraMateria(); break;
            case 16: retorno = clase.getCurso().getCarreraMaterias().getMateria().getIdMateria(); break;
            case 17: retorno = clase.getCurso().getCarreraMaterias().getMateria().getIdMateria(); break;
            case 18: retorno = clase.getCurso().getCarreraMaterias().getAnioCarrera(); break;
            case 19: retorno = clase.getCurso().getCarreraMaterias().getCarrera().getIdCarrera(); break;
            case 20: retorno = clase.getCurso().getCarreraMaterias().getCarrera().getDescripcion(); break;
            case 21: retorno = clase.getCurso().getCarreraMaterias().getMateria().getIdMateria(); break;
            case 22: retorno = clase.getCurso().getCarreraMaterias().getMateria().getDescripcion(); break;
            case 23: retorno = clase.getCurso().getProfesor().getIdProfesor(); break;
            case 24: retorno = clase.getCurso().getProfesor().getApellido(); break;
            case 25: retorno = clase.getCurso().getProfesor().getNombre(); break;
            case 26: retorno = clase.getCurso().getProfesor().getDni(); break;
            case 27: retorno = clase.getCurso().getProfesor().getGenero(); break;
            case 28: retorno = clase.getCurso().getProfesor().getFechaDeNacimiento(); break;
            case 29: retorno = clase.getCurso().getProfesor().getEmail(); break;
            case 30: retorno = clase.getCurso().getProfesor().isEstado(); break;
            case 31: retorno = clase.getCurso().getProfesor().toString(); break;
            case 32: retorno = clase.getCurso().getAnioLectivo(); break;
        }
        return retorno;    
    }
    
    
     
    
    
    
    @Override // Sobreescribir los nombres de columnas
    public String getColumnName(int index){
        return COLUMNAS[index];
        
    }
    
            
       private String fecha(Clase cl){
        String fecha=null;    
        if (cl.getFecha()!=null){
            fecha = (new SimpleDateFormat("dd-MM-yyyy")).format(cl.getFecha());
        }
        return fecha;                
    }
    
    
}
