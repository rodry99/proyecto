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
 * @author carlos cerrizuela
 */
public class CursoDiaTableModel extends AbstractTableModel {

     private List<CursoDias> cursosdia; //Definir una variable de instancia llamada alumnos que va a tener la lista
    
    private static String[] COLUMNAS = {"ID_CURSO_DIA"
      ,"ID_CURSO"
      ,"DIA"};
    
    
     public CursoDiaTableModel (List<CursoDias> cursosdia){
        super();
        this.cursosdia = cursosdia;
            
    }
    
    public CursoDiaTableModel (){
        super();
    }
    
    
    @Override
    public int getRowCount() {
     return cursosdia.size();    
    }

    @Override
    public int getColumnCount() {
     return COLUMNAS.length; //cantidad de valores del vector COLUMNAS
    
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    Object retorno = null;
            CursoDias cursodia = cursosdia.get(rowIndex);
            switch (columnIndex){
            
            case 0: retorno = cursodia.getIdCursoDia();break;
            case 1: retorno = cursodia.getIdCurso(); break;
            case 2: retorno = cursodia.getDia(); break;
            
            
            }
    
            return retorno;
}
   @Override // Sobreescribir los nombres de columnas
    public String getColumnName(int index){
        return COLUMNAS[index];
        
    }
    

}