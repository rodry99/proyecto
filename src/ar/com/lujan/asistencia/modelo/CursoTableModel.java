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
 * @author Andres
 */
public class CursoTableModel extends AbstractTableModel{ //la clase abstracta no se puede instanciar, por eso creamos una subclase
    
    private List<Curso> cursos; //Definir una variable de instancia llamada alumnos que va a tener la lista
    private static String[] COLUMNAS = {"ID_CURSO",
                                        "CARRERA",
                                        "MATERIA",
                                        "LECTIVO",
                                        "CUATRIMESTRE",
                                        "COMISION",
                                        "PROFESOR",
                                        "ESTADO",
                                        "INSCRIPTOS",
                                        "DESDE",
                                        "HASTA"};
                                        //,"CARRERAMATERIA"};
    //Variable de clase (MAYUSCULA)
    
    //Constructor
    public CursoTableModel (List<Curso> cursos){
        super();
        this.cursos=cursos;
            
    }
    
    public CursoTableModel (){
        super();
    }
    
    //Metodos abstractos obligatorios
    @Override
    public int getRowCount() {
        return cursos.size(); //cantidad de registros de la lista
    }

    @Override
    public int getColumnCount() {
         return COLUMNAS.length; //cantidad de valores del vector COLUMNAS
    }

    @Override //devolver una columna especifica de un registro especifico
    public Object getValueAt(int rowIndex, int columnIndex) {
            Object retorno = null;
            Curso curso = cursos.get(rowIndex);
            switch (columnIndex){
                
//            case 0: retorno = curso.getIdCurso(); break;
//            case 1: retorno = curso.getCarreraMaterias().getCarrera().getDescripcion(); break;
//            case 2: retorno = curso.getCarreraMaterias().getMateria().getDescripcion(); break;
//            case 3: retorno = curso.getAnioLectivo(); break;
//            case 4: retorno = curso.getCuatrimestre(); break;
//            case 5: retorno = curso.getComision(); break;            
//            case 6: retorno = curso.getProfesor(); break;
//            case 7: retorno = curso.getEstado(); break;
//            case 8: retorno = curso.getCantidadInscriptos(); break;
//            case 9: retorno = curso.getDesde(); break;
//            case 10: retorno = curso.getHasta(); break;
//            case 11: retorno = curso.getCarreraMaterias(); break;
            
            case 0: retorno = curso.getIdCurso(); break;
            case 1: retorno = curso.getCarreraMaterias().getCarrera().getDescripcion(); break;
            case 2: retorno = curso.getCarreraMaterias().getMateria().getDescripcion(); break;
            case 3: retorno = curso.getAnioLectivo(); break;
            case 4: retorno = curso.getCuatrimestre(); break;
            case 5: retorno = curso.getComision(); break;            
            case 6: retorno = curso.getProfesor(); break;
            case 7: retorno = curso.getEstado(); break;
            case 8: retorno = curso.getCantidadInscriptos(); break;
            case 9: retorno = curso.getDesde(); break;
            case 10: retorno = curso.getHasta(); break;
            case 11: retorno = curso.getCarreraMaterias(); break;
            
            }
            return retorno;    
    }
    @Override // Sobreescribir los nombres de columnas
    public String getColumnName(int index){
        return COLUMNAS[index];
        
    }
    
            
   
    
  
}
